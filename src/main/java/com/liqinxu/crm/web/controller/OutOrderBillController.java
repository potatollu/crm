package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.OutOrderBill;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IOutOrderBillService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("outOrderBill")
public class OutOrderBillController {

    @Autowired
    private IOutOrderBillService outOrderBillService;

    @RequiresPermissions(value = {"outOrderBill:view", "订单查看"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view() {
        return "/outOrderBill/view";
    }

    @RequestMapping("outOrderBill")
    @ResponseBody
    public Object outOrderBill(QueryObject queryObject) {
        PageResult pageResult = outOrderBillService.query(queryObject);
        return pageResult;
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(QueryObject qo) {
        PageResult result = outOrderBillService.query(qo);
        return result;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return outOrderBillService.selectAll();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            outOrderBillService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"outOrderBill:saveOrUpdate", "添加或更新订单"}, logical = Logical.OR)
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JSONResult saveOrUpdate(OutOrderBill outOrderBill) {
        JSONResult jsonResult = new JSONResult();
        try {

            if (outOrderBill.getId() != null) {
                outOrderBillService.updateByPrimaryKey(outOrderBill);
            } else {
                outOrderBillService.insert(outOrderBill);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

    @RequestMapping("changeAudit")
    @ResponseBody
    public JSONResult audit(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            if (id != null) {
                outOrderBillService.audit(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("审核失败");
        }
        return jsonResult;
    }
    @RequestMapping("listAll")
    @ResponseBody
    public Object listAll() {
        return outOrderBillService.selectAll();
    }
}
