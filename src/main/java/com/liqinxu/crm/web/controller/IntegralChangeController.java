package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.IntegralChange;
import com.liqinxu.crm.qo.IntegralChangeQuery;
import com.liqinxu.crm.service.IIntegralChangeService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("integralChange")
public class IntegralChangeController {

    @Autowired
    private IIntegralChangeService integralChangeService;

    /**
     *积分变动管理页面
     * @return 页面地址
     */
    @RequestMapping("view")
    public String integralChange(){
        return "integralChange/view";
    }

    /**
     * 分页查询积分变动信息
     * @param query 高级查询IntegralChangeQuery类的对象
     * @return 每页显示的积分变动信息
     */
    @RequestMapping("list")
    @ResponseBody
    public PageResult list(IntegralChangeQuery query) {
        PageResult result = integralChangeService.query(query);
        return result;
    }

    /**
     * 添加积分变动信息
     * @param integralChange 积分变动对象
     * @return JSONResult对象
     */
    @RequestMapping("save")
    @ResponseBody
    public JSONResult save(IntegralChange integralChange) {
        try {
            integralChangeService.insert(integralChange);
            return new JSONResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONResult().mark("操作失败");
        }
    }

    /**
     * 删除积分变动
     * @param id 积分变动对象的id
     * @return JSONResult对象
     */
    @RequestMapping("delete")
    @ResponseBody
    public JSONResult delete(Long id) {
        integralChangeService.deleteByPrimaryKey(id);
        return new JSONResult();
    }
}
