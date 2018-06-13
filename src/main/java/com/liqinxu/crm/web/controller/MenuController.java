package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.Menu;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IMenuService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    @RequestMapping("view")
    public String view() {
        return "/menu/view";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(QueryObject qo) {
        PageResult result = menuService.query(qo);
        return result;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return menuService.selectAll();
    }

    @RequestMapping("selectRootMenu")
    @ResponseBody
    public Object selectRootMenu() {
        Session session = SecurityUtils.getSubject().getSession();
        Object menus = session.getAttribute("MENUS");
        //查看session中是否有缓存起来的菜单，如果有就直接返回，没有就去查询，再放入session中
        if (menus == null) {
            menus = menuService.selectRootMenu();
            //先做权限过滤，在返回给页面
            menuService.checkPermiassion((List<Menu>) menus);
            session.setAttribute("MENUS", menus);
        }
        return menus;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            menuService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"menu:saveOrUpdate", "添加或更新菜单"}, logical = Logical.OR)
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Menu menu) {
        JSONResult jsonResult = new JSONResult();
        try {
            menuService.saveOrUpdate(menu);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

}
