package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.Member;
import com.liqinxu.crm.domain.MemberPayNumber;
import com.liqinxu.crm.domain.Serve;
import com.liqinxu.crm.qo.MenberPayNumberQueryObject;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IMemberPayNumberService;
import com.liqinxu.crm.service.IMemberService;
import com.liqinxu.crm.service.IServeService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("memberPayNumber")
public class MemberPayNumberController {

    @Autowired
    private IMemberPayNumberService memberPayNumberService;
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IServeService serveService;

    @RequestMapping("view")
    public String view() {
        return "/memberPayNumber/view";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(QueryObject qo) {
        PageResult result = memberPayNumberService.query(qo);
        return result;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return memberPayNumberService.selectAll();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            memberPayNumberService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(MemberPayNumber memberPayNumber) {
        JSONResult jsonResult = new JSONResult();
        String number = memberPayNumber.getMember().getNumber();
        //通过卡号查询会员信息,再保存进去
        Member member = memberService.selectByNumber(number);
        memberPayNumber.setMember(member);
        //通过服务名称查询服务信息，再保存进去
        String sn = memberPayNumber.getServe().getSn();
        Serve serve = serveService.selectBySn(sn);
        memberPayNumber.setServe(serve);
        //保存支付时间
        memberPayNumber.setPayTime(new Date());
        //根据服务名称和会员卡号查询对应服务的充值前次数和充值后次数
        List<MemberPayNumber> frequencys = memberPayNumberService.selectNumberBySn(sn,number);
        if (frequencys.size() <= 0){
            memberPayNumber.setPayBeforeNum(0);
            memberPayNumber.setPayAfterNum(memberPayNumber.getPayNumber());
        }else {
            try {
                //取最后一个对象
                MemberPayNumber frequency = frequencys.get(frequencys.size() - 1);
                //数据库里面充值前次数
                Integer beforeNum = frequency.getPayBeforeNum();
                if (beforeNum == null){
                    memberPayNumber.setPayBeforeNum(0);
                }else {
                    memberPayNumber.setPayBeforeNum(frequency.getPayAfterNum());
                }
                //数据库里面的数据
                Integer afterNum = frequency.getPayAfterNum();
                if (afterNum == null){
                    memberPayNumber.setPayAfterNum(memberPayNumber.getPayNumber());
                }else {
                    memberPayNumber.setPayAfterNum(afterNum + memberPayNumber.getPayNumber());
                }
            } catch (Exception e) {
                e.printStackTrace();
                jsonResult.mark("保存失败");
            }
        }
        memberPayNumberService.saveOrUpdate(memberPayNumber);
        return jsonResult;
    }


    @RequestMapping("searchMember")
    @ResponseBody
    public Object searchMember(MenberPayNumberQueryObject menberPayNumberQueryObject) {
        MemberPayNumber searchMember = memberPayNumberService.searchMember(menberPayNumberQueryObject);
        if (searchMember != null) {
            return searchMember;
        }
        return false;
    }

    @RequestMapping("listClientItem")
    @ResponseBody
    public Object listClientItem(String clientNumber) {
        List<MemberPayNumber> list = memberPayNumberService.listClientItem(clientNumber);
        PageResult pageResult = new PageResult();
        pageResult.setRows(list);
        return pageResult;
    }

    /*//保存充值记录
    @RequestMapping("saveExpenseItem")
    @ResponseBody
    public Object saveExpenseItem(MemberPayNumber memberPayNumber) {
        JSONResult jsonResult = new JSONResult();
        try {
            memberPayNumberService.saveOrUpdate(memberPayNumber);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }*/



}
