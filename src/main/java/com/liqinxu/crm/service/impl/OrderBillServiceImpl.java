package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.*;
import com.liqinxu.crm.mapper.*;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IOrderBillService;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class OrderBillServiceImpl implements IOrderBillService {
    @Autowired
    private OrderBillMapper orderBillMapper;
    @Autowired
    private OrderBillItemMapper orderBillItemMapper;
    
    @Autowired
    private StockTakingMapper stockTakingMapper;
    @Autowired
    private ProductDirMapper productDirMapper;
    @Autowired
    private DepotMapper depotMapper;


    @Override
    public int deleteByPrimaryKey(Long id) {
        orderBillMapper.deleteByPrimaryKey(id);
        orderBillItemMapper.deleteByBillId(id);
        return 0;
    }

    @Override
    public int insert(OrderBill bill) {
        //封装订单的录入人和录入时间
        bill.setInputUser((Employee) SecurityUtils.getSubject().getPrincipal());
        bill.setInputTime(new Date());
        //生成订单
        String sn="";
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        String temp = sf.format(new Date());
        int random=(int) (Math.random()*100);
        sn=temp+random;

        //=========================
        bill.setSn(sn);

        //在保存订单的时候,无论任何情况,状态值都应该为0
        bill.setStatus(OrderBill.STAUTS_NORMAL);

           List<OrderBillItem> items = bill.getItems();
           //设置明细相关的信息
           BigDecimal totalAmount = BigDecimal.ZERO;
           BigDecimal totalNumber = BigDecimal.ZERO;
           //计算单价的总额和总数量
           for (OrderBillItem item :items) {
               totalNumber = totalNumber.add(item.getNumber());
               totalAmount = totalAmount.add(item.getCostPrice().multiply(item.getNumber()));
           }
           bill.setTotalAmount(totalAmount);
           bill.setTotalNumber(totalNumber);
        //先保存订单,可以获取到订单的编号
        orderBillMapper.insert(bill);
        //====================================================

        for (OrderBillItem item : items) {
            //设置明细所属的订单编号
            item.setBillId(bill.getId());
            //计算明细的小计
            BigDecimal amount = item.getCostPrice().multiply(item.getNumber());
            item.setAmount(amount);
            orderBillItemMapper.insert(item);
        }
        return 0;
    }

    @Override
    public OrderBill selectByPrimaryKey(Long id) {
        return orderBillMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OrderBill> selectAll() {
        return orderBillMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(OrderBill bill) {
        if(bill.getStatus()==OrderBill.STAUTS_NORMAL) {
            //删除旧的明细
            orderBillItemMapper.deleteByBillId(bill.getId());

            //设置明细相关的信息
            List<OrderBillItem> items = bill.getItems();
            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal totalNumber = BigDecimal.ZERO;
            //计算单价的总额和总数量
            for (OrderBillItem item : items) {
                totalAmount = totalAmount.add(item.getCostPrice().multiply(item.getNumber()));
                totalNumber = totalNumber.add(item.getNumber());
                //设置明细所属的订单编号
                item.setBillId(bill.getId());
                //计算明细的小计
                BigDecimal amount = item.getCostPrice().multiply(item.getNumber());
                item.setAmount(amount);
                orderBillItemMapper.insert(item);

            }
            bill.setTotalAmount(totalAmount);
            bill.setTotalNumber(totalNumber);
            //先保存订单,可以获取到订单的编号
            orderBillMapper.updateByPrimaryKey(bill);

        }
        return 0;
    }

    @Override
    public PageResult query(QueryObject queryObject) {
        int count = orderBillMapper.queryForCount(queryObject);
        if (count == 0) {
            return new PageResult(count, Collections.EMPTY_LIST);
        }
        return new PageResult(count, orderBillMapper.queryForList(queryObject));
    }

    @Override
    public void audit(Long id) {
        //修改状态值
        //修改审核人
        //修改审核时间
        //查询出要审核的单据

        OrderBill old = orderBillMapper.selectByPrimaryKey(id);
        if(old.getStatus()==OrderBill.STAUTS_NORMAL){
            old.setStatus(OrderBill.STAUTS_AUDIT);
            old.setAuditor((Employee) SecurityUtils.getSubject().getPrincipal());
            old.setAuditTime(new Date());
            orderBillMapper.audit(old);

            String supplierName = old.getSupplier().getName();
            String depotName = old.getDepot().getName();
            String productName = null;
            String productSn = null;
            Long productId = null;
            for (OrderBillItem orderBillItem : old.getItems()) {
                productId = orderBillItem.getProduct().getId();
                productName = orderBillItem.getProduct().getName();
                productSn = orderBillItem.getProduct().getSn();
                StockTaking stockTaking = stockTakingMapper.changStockTaking(productName,depotName);
                if(stockTaking != null){
                    BigDecimal totalNumber = stockTaking.getTotalNumber();
                    BigDecimal totalNumber1 = old.getTotalNumber();
                    stockTaking.setTotalNumber(totalNumber.add(totalNumber1));
                    stockTaking.setEmployeeName(null);
                    stockTaking.setTakeTime(null);
                    stockTakingMapper.updateByPrimaryKey(stockTaking);
                }else {
                    StockTaking stock = new StockTaking();
                    String dirName = productDirMapper.selectDivNameByPid(productId);
                    stock.setProductSn(productSn);
                    stock.setProductName(productName);
                    stock.setDepotName(depotName);
                    stock.setDirName(dirName);
                    stock.setSupplierName(supplierName);
                    stock.setTotalNumber(old.getTotalNumber());
                    stockTakingMapper.insert(stock);
                }
            }
        }
    }
    
}
