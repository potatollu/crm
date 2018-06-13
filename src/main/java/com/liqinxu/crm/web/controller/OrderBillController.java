package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.OrderBill;
import com.liqinxu.crm.mapper.OrderBillItemMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IOrderBillService;
import com.liqinxu.crm.service.IProductService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("orderBill")
public class OrderBillController {

    @Autowired
    private IOrderBillService orderBillService;
    @Autowired
    private IProductService productService;
    @Autowired
    private OrderBillItemMapper orderBillItemMapper;

    @RequiresPermissions(value = {"orderBill:view", "订单查看"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view() {
        return "/orderBill/view";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(QueryObject qo) {
        PageResult result = orderBillService.query(qo);
        return result;
    }

    @RequestMapping("listAll")
    @ResponseBody
    public Object listAll() {
        return orderBillService.selectAll();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            orderBillService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"orderBill:saveOrUpdate", "添加或更新订单"}, logical = Logical.OR)
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JSONResult saveOrUpdate(OrderBill orderBill) {
        JSONResult jsonResult = new JSONResult();
        jsonResult.setMsg("保存成功");
        try {

            if (orderBill.getId() != null) {
                orderBillService.updateByPrimaryKey(orderBill);
            } else {
                orderBillService.insert(orderBill);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

    @RequestMapping("auditor")
    @ResponseBody
    public JSONResult audit(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            if (id != null) {
                orderBillService.audit(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("审核失败");
        }
        return jsonResult;
    }

}
