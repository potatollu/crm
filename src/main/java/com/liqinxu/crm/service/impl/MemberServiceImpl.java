package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.ConsumptionDetail;
import com.liqinxu.crm.domain.Member;
import com.liqinxu.crm.mapper.ConsumptionDetailMapper;
import com.liqinxu.crm.mapper.ExpenditureMapper;
import com.liqinxu.crm.mapper.MemberMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IMemberService;
import com.liqinxu.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private ExpenditureMapper expenditureMapper;

    @Autowired
    private ConsumptionDetailMapper consumptionDetailMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        /**
         * 先删除分组和等级中间表
         */
        memberMapper.deleteGroupingAndMember(id);
        memberMapper.deleteGradeAndMember(id);
        return memberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Member selectByPrimaryKey(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Member> selectAll() {
        return memberMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(Member member) {
        if (member.getId() != null) {
            //修改先删除中间表,再正常修改,再维护关系
            memberMapper.deleteGroupingAndMember(member.getId());
            memberMapper.deleteGradeAndMember(member.getId());
            memberMapper.updateByPrimaryKey(member);
        } else {
            memberMapper.insert(member);
        }
        /**
         * 保存分组和等级中间表
         */
        memberMapper.insertGroupingAndMember(member.getId(),member.getGroup().getId());
        memberMapper.insertGradeAndMember(member.getId(),member.getGrade().getId());
    }

    public Long selectByMemberId(Long memberId) {
        return memberMapper.selectByMemberId(memberId);
    }

    public Long selectGroupByMemberId(Long memberId) {
        return memberMapper.selectGroupByMemberId(memberId);
    }

    public int selectByBirthday() {
        return memberMapper.selectByBirthday();
    }

    //通过卡号查询对象信息
    public Member selectByNumber(String number) {
        return memberMapper.selectByNumber(number);
    }

    /**
     * 根据会员名/卡号查询会员信息
     * @param message 会员名/卡号
     * @return
     */
    public Object selectMemberByMessage(String message) {
        return memberMapper.selectMemberByMessage(message);
    }

    /**
     * 根据会员卡号删除积分
     * @param memberNumber 会员卡号
     */
    public void updateIntegral(String memberNumber) {
        memberMapper.updateIntegralByNumber(memberNumber,0);
    }


    @Override
    public Object totalCost(String number) {
        return memberMapper.totalCost(number);
    }



    @Override
    public PageResult query(QueryObject qo) {
        int count = memberMapper.queryForCount(qo);
        List<?> list = memberMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }

    @Override
    public int selectByBirthdayWithMonth() {
        //查询出当日过生日的人
        int birthdayByDay = memberMapper.selectByBirthday();
        //查询已经过完生日的人,当前当日时间往前的人
        int birthdayByOver = memberMapper.selectBybirthdayByOver();
        //查询出当月过生日的人
        int birthdayByMonth = memberMapper.selectBybirthdayByMonth();
        return birthdayByMonth - birthdayByOver - birthdayByDay;
    }


    @Override
    public int selectByMemberCount() {
        return memberMapper.selectByMemberCount();
    }

    /**
     * 查询累计消费
     *
     * @return 累计消费
     */
    @Override
    public int selectBytotalExpense() {
        int bytotalExpense = consumptionDetailMapper.selectBytotalExpense();
        return bytotalExpense;
    }

    /**
     * 查询累计消费多少笔
     *
     * @return 消费多少笔
     */
    @Override
    public int selectBytotalCount() {
        int bytotalCount = consumptionDetailMapper.selectBytotalCount();
        return bytotalCount;
    }


    @Override
    public int selectByaverageExpense() {
        //累计消费
        int bytotalExpense = consumptionDetailMapper.selectBytotalExpense();
        //明细表有几个人
        int byMemberCount = consumptionDetailMapper.selectByMemberCount();
        if (bytotalExpense != 0 && byMemberCount != 0) {

            return bytotalExpense / byMemberCount;
        }
        return 0;
    }

    /**
     * 根据排序语句查询消费金额在前三的人，查他的消费总额
     *
     * @return
     */
    @Override
    public Object selectByTOP3() {

        List<Map<String, String>> list = new ArrayList<>();
        List<ConsumptionDetail> consumptionDetails = consumptionDetailMapper.selectByTOP3();
        for (ConsumptionDetail consumptionDetail : consumptionDetails) {
            Map<String, String> map = new HashMap<>();
            map.put("name", consumptionDetail.getMember().getName());//
            map.put("totalClientAmount", consumptionDetail.getPayMoney().toString());
            list.add(map);
        }
        return list;
    }
}
