package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.Dicitionary;
import com.liqinxu.crm.domain.DicitionaryItem;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IDicitionaryItemService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("dicitionaryItem")
public class DicitionaryItemController {

    @Autowired
    private IDicitionaryItemService dicitionaryItemService;

    @RequestMapping("view")
    public String view() {
        return "/dicitionaryItem/view";
    }

    /*@RequestMapping("list")
    @ResponseBody
    public Object list(QueryObject qo) {
        PageResult result = dicitionaryItemService.query(qo);
        return result;
    }*/

    @RequestMapping("listAll")
    @ResponseBody
    public Object selectAll() {
        return dicitionaryItemService.selectAll();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long itemId) {
        JSONResult jsonResult = new JSONResult();
        try {
            dicitionaryItemService.deleteByPrimaryKey(itemId);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(DicitionaryItem dicitionaryItem) {
        JSONResult jsonResult = new JSONResult();
        try {
            dicitionaryItemService.saveOrUpdate(dicitionaryItem);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }
    //根据编码sn查字典明细
    @RequestMapping("listItem")
    @ResponseBody
    public Object listItem(String sn) {
        List<DicitionaryItem> dicitionaryItem = dicitionaryItemService.selectBySn(sn);

        return dicitionaryItem;
    }

}
