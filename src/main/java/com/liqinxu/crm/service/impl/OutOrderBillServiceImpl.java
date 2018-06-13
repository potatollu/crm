package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.*;
import com.liqinxu.crm.mapper.*;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IOutOrderBillService;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class OutOrderBillServiceImpl implements IOutOrderBillService {
    @Autowired
    private OutOrderBillMapper outOrderBillMapper;
    @Autowired
    private OutOrderBillItemMapper outOrderBillItemMapper;
    @Autowired
    private OrderBillMapper orderBillMapper;
    @Autowired
    private DepotMapper depotMapper;
    @Autowired
    private StockTakingMapper stockTakingMapper;
    @Autowired
    private ProductDirMapper productDirMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        outOrderBillMapper.deleteByPrimaryKey(id);
        outOrderBillItemMapper.deleteByBillId(id);
        return 0;
    }

    @Override
    public int insert(OutOrderBill bill) {
        //封装订单的录入人和录入时间
        bill.setInputUser((Employee) SecurityUtils.getSubject().getPrincipal());
        bill.setInputTime(new Date());
        int random=(int) (Math.random()*100);
        //在保存订单的时候,无论任何情况,状态值都应该为0
        bill.setStatus(OutOrderBill.STAUTS_NORMAL);

           List<OutOrderBillItem> items = bill.getItems();
           //设置明细相关的信息
           BigDecimal totalAmount = BigDecimal.ZERO;
           BigDecimal totalNumber = BigDecimal.ZERO;
           //计算单价的总额和总数量
           for (OutOrderBillItem item :items) {
               totalNumber = totalNumber.add(item.getNumber());
               totalAmount = totalAmount.add(item.getCostPrice().multiply(item.getNumber()));
           }
           bill.setTotalAmount(totalAmount);
           bill.setTotalNumber(totalNumber);
        //先保存订单,可以获取到订单的编号
        outOrderBillMapper.insert(bill);
        //====================================================

        for (OutOrderBillItem item : items) {
            //设置明细所属的订单编号
            item.setBillId(bill.getId());
            //计算明细的小计
            BigDecimal amount = item.getCostPrice().multiply(item.getNumber());
            item.setAmount(amount);
            outOrderBillItemMapper.insert(item);
        }
        return 0;
    }

    @Override
    public OutOrderBill selectByPrimaryKey(Long id) {
        return outOrderBillMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OutOrderBill> selectAll() {
        return outOrderBillMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(OutOrderBill bill) {
        if(bill.getStatus()==OutOrderBill.STAUTS_NORMAL) {
            //删除旧的明细
            outOrderBillItemMapper.deleteByBillId(bill.getId());

            //设置明细相关的信息
            List<OutOrderBillItem> items = bill.getItems();
            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal totalNumber = BigDecimal.ZERO;
            //计算单价的总额和总数量
            for (OutOrderBillItem item : items) {
                totalAmount = totalAmount.add(item.getCostPrice().multiply(item.getNumber()));
                totalNumber = totalNumber.add(item.getNumber());
                //设置明细所属的订单编号
                item.setBillId(bill.getId());
                //计算明细的小计
                BigDecimal amount = item.getCostPrice().multiply(item.getNumber());
                item.setAmount(amount);
                outOrderBillItemMapper.insert(item);

            }
            bill.setTotalAmount(totalAmount);
            bill.setTotalNumber(totalNumber);
            //先保存订单,可以获取到订单的编号
            outOrderBillMapper.updateByPrimaryKey(bill);
        }
        return 0;
    }

    @Override
    public PageResult query(QueryObject queryObject) {
        int count = outOrderBillMapper.queryForCount(queryObject);
        if (count == 0) {
            return new PageResult(count, Collections.EMPTY_LIST);
        }
        return new PageResult(count, outOrderBillMapper.queryForList(queryObject));
    }

    @Override
    public void audit(Long id) {
        //修改状态值
        //修改审核人
        //修改审核时间
        //查询出要审核的单据

        OutOrderBill old = outOrderBillMapper.selectByPrimaryKey(id);
        if(old.getStatus()==OutOrderBill.STAUTS_NORMAL){
            old.setStatus(OutOrderBill.STAUTS_AUDIT);
            old.setAuditor((Employee) SecurityUtils.getSubject().getPrincipal());
            old.setAuditTime(new Date());
            outOrderBillMapper.audit(old);
            //改库存、
            String supplierName = old.getSupplier().getName();
            String depotName = old.getDepot().getName();
            String productName = null;
            String productSn = null;
            Long productId = null;
            for (OutOrderBillItem orderBillItem : old.getItems()) {
                productId = orderBillItem.getProduct().getId();
                productName = orderBillItem.getProduct().getName();
                productSn = orderBillItem.getProduct().getSn();
                StockTaking stockTaking = stockTakingMapper.changStockTaking(productName,depotName);
                if(stockTaking != null){
                    BigDecimal totalNumber = stockTaking.getTotalNumber();
                    BigDecimal totalNumber1 = old.getTotalNumber();
                    stockTaking.setTotalNumber(totalNumber.subtract(totalNumber1));
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
