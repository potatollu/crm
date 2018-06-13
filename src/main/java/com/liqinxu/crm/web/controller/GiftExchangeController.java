package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IGiftExchangeService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("giftExchange")
public class GiftExchangeController {

    @Autowired
    private IGiftExchangeService giftExchangeService;

    @RequiresPermissions(value = {"giftExchange:view", "兑换记录查看"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view() {
        return "/giftExchange/view";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(QueryObject qo) {
        PageResult result = giftExchangeService.query(qo);
        return result;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return giftExchangeService.selectAll();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            giftExchangeService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }
}
