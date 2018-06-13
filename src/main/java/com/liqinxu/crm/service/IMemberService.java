package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.Member;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IMemberService {
    int deleteByPrimaryKey(Long id);

    Member selectByPrimaryKey(Long id);

    List<Member> selectAll();

    PageResult query(QueryObject qo);

    void saveOrUpdate(Member member);

    Member selectByNumber(String number);
    Long selectByMemberId(Long memberId);

    Long selectGroupByMemberId(Long memberId);

    int selectByBirthday();

    int selectByBirthdayWithMonth();

    int selectByMemberCount();

    /**
     * 查询累计消费
     * @return 累计消费
     */
    int selectBytotalExpense();

    int selectBytotalCount();

    int selectByaverageExpense();

    /**
     * 根据排序语句查询消费金额在前三的人，查他的消费总额
     * @return
     */
    Object selectByTOP3();

    /**
     * 根据会员名/卡号查询会员信息
     * @param message 会员名/卡号
     * @return
     */
    Object selectMemberByMessage(String message);

    /**
     * 根据会员卡号删除积分
     * @param memberNumber 会员卡号
     */
    void updateIntegral(String memberNumber);

    /**
     * 根据g根据卡号查总消费
     * @param number 会员卡号
     */
    Object totalCost(String number);
}
