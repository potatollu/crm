package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.Member;
import com.liqinxu.crm.qo.MemberQueryObject;
import com.liqinxu.crm.service.IMemberService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    private IMemberService memberService;


    @RequestMapping("view")
    public String view() {
        return "/member/view";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(MemberQueryObject qo) {
        PageResult result = memberService.query(qo);
        return result;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return memberService.selectAll();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            memberService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"member:saveOrUpdate", "添加或更新部门"}, logical = Logical.OR)
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Member member) {
        JSONResult jsonResult = new JSONResult();
        try {
            memberService.saveOrUpdate(member);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

    //根据会员id查询等级信息用于编辑回显
    @RequestMapping("selectMemberByMessage")
    @ResponseBody
    public Object selectMemberByMessage(String keyword) {
        return memberService.selectMemberByMessage(keyword);
    }

    @RequestMapping("selectByMemberId")
    @ResponseBody
    public Object selectByMemberId(Long memberId) {
        return memberService.selectByMemberId(memberId);
    }

    //根据会员id查询分组信息用于编辑回显
    @RequestMapping("selectGroupByMemberId")
    @ResponseBody
    public Object selectGroupByMemberId(Long memberId) {
        return memberService.selectGroupByMemberId(memberId);
    }

    @RequestMapping("totalCost")
    @ResponseBody
    public Object totalCost(String number) {
        return memberService.totalCost(number);
    }

    //查询当日过生日的会员数
    @RequestMapping("bircount")
    @ResponseBody
    public int bircount() {
        return memberService.selectByBirthday();
    }

    //查询当月过生日的会员数
    @RequestMapping("bimcount")
    @ResponseBody
    public int bimcount() {
        return memberService.selectByBirthdayWithMonth();
    }

    //查询会员总数
    @RequestMapping("memberCount")
    @ResponseBody
    public int memberCount() {
        return memberService.selectByMemberCount();
    }

    //查询消费多少钱
    @RequestMapping("totalExpense")
    @ResponseBody
    public int totalExpense() {
        int bytotalExpense = memberService.selectBytotalExpense();
        return bytotalExpense;
    }

    //查询累计消费多少笔
    @RequestMapping("totalCount")
    @ResponseBody
    public int totalCount() {
        return memberService.selectBytotalCount();
    }

    //查询平均每位会员消费
    @RequestMapping("averageExpense")
    @ResponseBody
    public int averageExpense() {
        return memberService.selectByaverageExpense();
    }

    //查询TOP3
    @RequestMapping("getInfo")
    @ResponseBody
    public Object getInfo() {
        //根据排序语句查询消费金额在前三的人，查他的消费总额和消费人
        return memberService.selectByTOP3();
    }

    @RequestMapping("selectByNumber")
    @ResponseBody
    public Object selectByMemberId(String number) {
        return memberService.selectByNumber(number);
    }

    /**
     * 根据会员卡号删除积分
     * @param memberNumber 会员卡号
     * @return JSONResult对象
     */
    @RequestMapping("updateIntegral")
    @ResponseBody
    public JSONResult updateIntegral(String memberNumber) {
        memberService.updateIntegral(memberNumber);
        return new JSONResult();
    }
}
