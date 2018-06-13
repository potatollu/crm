package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.Serve;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IServeService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("serve")
public class ServeController {

    @Autowired
    private IServeService serveService;

    @RequiresPermissions(value = {"serve:view", "部门查看"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view() {
        return "/serve/view";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(QueryObject qo) {
        PageResult result = serveService.query(qo);
        return result;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return serveService.selectAll();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            serveService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"serve:saveOrUpdate", "添加或更新部门"}, logical = Logical.OR)
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Serve serve) {
        JSONResult jsonResult = new JSONResult();
        try {
            serveService.saveOrUpdate(serve);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

}
