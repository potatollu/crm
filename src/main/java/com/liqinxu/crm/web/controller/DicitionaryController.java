package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.Dicitionary;
import com.liqinxu.crm.service.IDicitionaryService;
import com.liqinxu.crm.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("dicitionary")
public class DicitionaryController {

    @Autowired
    private IDicitionaryService dicitionaryService;

    @RequestMapping("view")
    public String view() {
        return "/dicitionary/view";
    }

    /*@RequestMapping("list")
    @ResponseBody
    public Object list() {
        return dicitionaryService.selectAll();
    }*/

    @RequestMapping("listAll")
    @ResponseBody
    public Object selectAll() {
        return dicitionaryService.selectAll();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            dicitionaryService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Dicitionary dicitionary) {
        JSONResult jsonResult = new JSONResult();
        try {
            dicitionaryService.saveOrUpdate(dicitionary);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }



}
