package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.PayMode;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IPayModeService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("paymode")
public class PayModeController {

    @Autowired
    private IPayModeService payModeService;

    @RequiresPermissions(value = {"payMode:view", "部门查看"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view() {
        return "/payMode/view";
    }


    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return payModeService.selectAll();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            payModeService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"payMode:saveOrUpdate", "添加或更新部门"}, logical = Logical.OR)
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(PayMode payMode) {
        JSONResult jsonResult = new JSONResult();
        try {
            payModeService.saveOrUpdate(payMode);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

}
