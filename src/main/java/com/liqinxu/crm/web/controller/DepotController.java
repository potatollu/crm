package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.Depot;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IDepotService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("depot")
public class DepotController {

    @Autowired
    private IDepotService depotService;

    @RequiresPermissions(value = {"depot:view", "供应商查看"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view() {
        return "/depot/view";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(QueryObject qo) {
        PageResult result = depotService.query(qo);
        return result;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            depotService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"depot:saveOrUpdate", "添加或更新供应商"}, logical = Logical.OR)
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Depot depot) {
        JSONResult jsonResult = new JSONResult();
        try {
            depotService.saveOrUpdate(depot);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }
    @RequestMapping("orderByDepot")
    @ResponseBody
    public Object orderByDepot() {
        return   depotService.selectAll();
    }
}
