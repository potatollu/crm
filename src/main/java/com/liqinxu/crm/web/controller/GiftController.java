package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.Gift;
import com.liqinxu.crm.domain.TakeGiftItem;
import com.liqinxu.crm.qo.GiftQuery;
import com.liqinxu.crm.service.IGiftService;
import com.liqinxu.crm.service.ITakeGiftItemService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("gift")
public class GiftController {

    @Autowired
    private IGiftService giftService;
    @Autowired
    private ITakeGiftItemService takeGiftItemService;
    /**
     *礼品管理页面
     * @return 页面地址
     */
    @RequestMapping("view")
    public String gift(){
        return "gift/view";
    }

    /**
     * 分页查询礼品信息
     * @param query 高级查询GiftQuery类的对象
     * @return 每页显示的礼品信息
     */
    @RequestMapping("list")
    @ResponseBody
    public PageResult list(GiftQuery query) {
        return giftService.query(query);
    }
    /**
     * 礼品兑换页面
     * @return
     */
    @RequestMapping("exchange")
    public String exchange(){
        return "gift/exchange";
    }

    /**
     * 添加/编辑礼品信息
     * @param gift 礼品对象
     * @return JSONResult对象
     */
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JSONResult saveOrUpdate(Gift gift) {
        try {
            if (gift.getId() != null) {
                giftService.updateByPrimaryKey(gift);
            } else {
                giftService.insert(gift);
            }
            return new JSONResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONResult().mark("操作失败");
        }
    }
    @RequestMapping("saveTakeGiftItem")
    @ResponseBody
    public JSONResult saveTakeGiftItem(TakeGiftItem takeGiftItem){
        JSONResult result =  new JSONResult();
        try {
            giftService.makeExchage(takeGiftItem);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result.mark("操作失败");
        }
    }
    /**
     * 礼品兑换选择页面
     * @return
     */
    @RequestMapping("listView")
    public String listView(){
        return "gift/giftView";
    }
    @RequestMapping("queryAll")
    @ResponseBody
    public Object queryAll(){

        return giftService.queryAll();
    }
    /**
     * 删除礼品
     * @param id 礼品对象的id
     * @return JSONResult对象
     */
    @RequestMapping("delete")
    @ResponseBody
    public JSONResult delete(Long id) {
        giftService.deleteByPrimaryKey(id);
        return new JSONResult();
    }
}
