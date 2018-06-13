package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.Department;
import com.liqinxu.crm.domain.Employee;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IDepartmentService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @RequiresPermissions(value = {"department:view", "部门查看"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view() {
        return "/department/view";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(QueryObject qo) {
        PageResult result = departmentService.query(qo);
        return result;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return departmentService.selectAll();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            departmentService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"department:saveOrUpdate", "添加或更新部门"}, logical = Logical.OR)
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Department department) {
        JSONResult jsonResult = new JSONResult();
        try {
            departmentService.saveOrUpdate(department);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

}
