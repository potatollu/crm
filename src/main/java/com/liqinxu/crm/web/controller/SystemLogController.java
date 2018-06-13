package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.SystemLog;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.ISystemLogService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("systemLog")
public class SystemLogController {

    @Autowired
    private ISystemLogService systemLogService;

    @RequiresPermissions(value = {"systemLog:view", "日志查看"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view() {
        return "/systemLog/view";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(QueryObject qo) {
        PageResult result = systemLogService.query(qo);
        return result;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return systemLogService.selectAll();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            systemLogService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"systemLog:saveOrUpdate", "添加或更新部门"}, logical = Logical.OR)
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(SystemLog systemLog) {
        JSONResult jsonResult = new JSONResult();
        try {
            systemLogService.saveOrUpdate(systemLog);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

}
