package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.Permission;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IPermissionService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("view")
    public String view() {
        return "/permission/view";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(QueryObject qo) {
        PageResult result = permissionService.query(qo);
        return result;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return permissionService.selectAll();
    }


    @RequestMapping("reload")
    @ResponseBody
    public Object reload() {
        JSONResult jsonResult = new JSONResult();
        try {
            permissionService.reload();
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("加载失败");
        }
        return jsonResult;
    }

    @RequestMapping("deleteByPrimaryKey")
    @ResponseBody
    public Object deleteByPrimaryKey(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            permissionService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequestMapping("selectByRoleId")
    @ResponseBody
    public Object selectByRoleId(Long id) {
        return permissionService.selectByRoleId(id);
    }

}
