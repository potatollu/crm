package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.ProductDir;
import com.liqinxu.crm.qo.ProductDirQueryObject;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IProductDirService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import jdk.nashorn.internal.ir.ReturnNode;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("productDir")
public class ProductDirController {

    @Autowired
    private IProductDirService productDirService;

    @RequiresPermissions(value = {"productDir:view", "商品分类查看"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view() {
        return "/productDir/view";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(QueryObject qo) {
        PageResult result = productDirService.query(qo);
        return result;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return productDirService.selectAll();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            productDirService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"productDir:saveOrUpdate", "添加或更新商品分类"}, logical = Logical.OR)
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(ProductDir productDir) {
        JSONResult jsonResult = new JSONResult();
        try {
            productDirService.saveOrUpdate(productDir);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

    @RequestMapping("selectProductDir")
    @ResponseBody
    public List<ProductDir> selectProductDir() {
        return productDirService.selectProductDir();
    }

    @RequestMapping("selectChildren")
    @ResponseBody
    public List<ProductDir> selectChildren(Long parentId) {
        return productDirService.selectChildren(parentId);
    }

    @RequestMapping("selectByParentId")
    @ResponseBody
    public List<ProductDir> selectByParentId(ProductDirQueryObject qo) {
        List<ProductDir> p = productDirService.selectByParentId(qo);
        return  p;
    }

}
