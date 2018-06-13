package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.Supplier;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.ISupplierService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;

    @RequiresPermissions(value = {"supplier:view", "供应商查看"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view() {
        return "/supplier/view";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(QueryObject qo) {
        PageResult result = supplierService.query(qo);
        return result;
    }
    
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            supplierService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"supplier:saveOrUpdate", "添加或更新供应商"}, logical = Logical.OR)
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Supplier supplier) {
        JSONResult jsonResult = new JSONResult();
        try {
            supplierService.saveOrUpdate(supplier);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

    @RequestMapping("orderBySupplier")
    @ResponseBody
    public Object orderBySupplier() {
        return supplierService.selectAll();
    }

}
