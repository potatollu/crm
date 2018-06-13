package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.Recharge;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IRechargeService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("recharge")
public class RechargeController {

    @Autowired
    private IRechargeService rechargeService;

    @RequiresPermissions(value = {"recharge:view", "充值查看"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view() {
        return "/recharge/view";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(QueryObject qo) {
        PageResult result = rechargeService.query(qo);
        return result;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return rechargeService.selectAll();
    }

    //查询累计消费
    @RequestMapping("selectTotalCost")
    @ResponseBody
    public Object selectTotalCost(String clientNumber) {
        return rechargeService.selectTotalCost(clientNumber);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            rechargeService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Recharge recharge) {
        JSONResult jsonResult = new JSONResult();
        try {
            rechargeService.saveOrUpdate(recharge);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

}
