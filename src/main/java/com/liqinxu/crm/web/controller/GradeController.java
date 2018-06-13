package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.service.IGradeService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("grade")
public class GradeController {

    @Autowired
    private IGradeService gradeService;

    @RequiresPermissions(value = {"grade:view", "部门查看"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view() {
        return "/grade/view";
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return gradeService.selectAll();
    }

}
