package com.liqinxu.crm.web.controller;

import com.alibaba.fastjson.JSON;
import com.liqinxu.crm.domain.OutComeBillItem;
import com.liqinxu.crm.qo.OutComeBillItemQueryObject;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IOutComeBillItemService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("outComeBillItem")
public class OutComeBillItemController {

    @Autowired
    private IOutComeBillItemService outComeBillItemService;

    @RequiresPermissions(value = {"outComeBillItem:view", "员工列表"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view() {
        return "/outComeBillItem/view";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(OutComeBillItemQueryObject qo) {
        PageResult result = outComeBillItemService.query(qo);
        return result;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            outComeBillItemService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(){
        return outComeBillItemService.selectAll();
    }

    @RequiresPermissions(value = {"outComeBillItem:saveOrUpdate", "添加或更新员工"}, logical = Logical.OR)
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(OutComeBillItem outComeBillItem) {
        JSONResult jsonResult = new JSONResult();
        try {
            if(outComeBillItem.getId()!=null){
                outComeBillItemService.updateByPrimaryKey(outComeBillItem);
            }else{
                outComeBillItemService.insert(outComeBillItem);
            }
            return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

    @RequestMapping("selectOutChart")
    public String selectOutChart(Model model){
        //查询出页面需要的信息
        List<Map<String, Object>> charts = outComeBillItemService.selectOutChart();
        List<String> OcName = new ArrayList<>();//存放分组类型
        List<Object> saleAmount = new ArrayList<>();//存放销售总额
        List<Map<String,Object>> datas = new ArrayList<>();//页面需要的数据格式的一个集合
        for (Map<String,Object> chart : charts) {
            //分组类型
            OcName.add(chart.get("OcName").toString());
            System.out.println(OcName);
            //每组的数据
            saleAmount.add(chart.get("totalAmount").toString());
            Map<String,Object> data = new HashMap<>();//页面的数据格式是一个map的集合
            data.put("value",chart.get("totalAmount"));
            data.put("name",chart.get("OcName"));
            datas.add(data);
        }
        //转换成JSON格式的字符串共享到页面中
        model.addAttribute("totalAmount", JSON.toJSONString(OcName));
        model.addAttribute("totalAmounts",JSON.toJSONString(saleAmount));
        model.addAttribute("datas",JSON.toJSONString(datas));
        return "outComeBillItem/outChartByBar";
    }

}
