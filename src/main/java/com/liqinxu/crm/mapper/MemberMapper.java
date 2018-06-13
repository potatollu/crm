package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.Member;
import com.liqinxu.crm.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface MemberMapper {
    /**
     *  会员增删改差方法
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    int insert(Member entity);

    Member selectByPrimaryKey(Long id);

    List<Member> selectAll();

    int updateByPrimaryKey(Member entity);

    /**
     * 分页方法
     * @param qo
     * @return
     */
    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);

    /**
     * 保存会员和分组中间表
     * @param memberId
     * @param groupingId
     */
    void insertGroupingAndMember(@Param("memberId") Long memberId, @Param("groupingId") Long groupingId);

    /**
     *  保存会员和等级中间表
     * @param memberId
     * @param gradeId
     */
    void insertGradeAndMember(@Param("memberId") Long memberId, @Param("gradeId") Long gradeId);

    /**
     * 根据会员id删除分组和会员中间表
     * @param id
     */
    void deleteGroupingAndMember(Long id);

    /**
     * 根据会员id删除等级和会员中间表
     * @param id
     */
    void deleteGradeAndMember(Long id);

    Member selectByNumber(String number);

    Long selectByMemberId(Long memberId);

    Long selectGroupByMemberId(Long memberId);

    Object selectMemberByMessage(String message);

    Member getMemberByNumber(String number);

    Object totalCost(String number);
    int selectByBirthday();

    int selectBybirthdayByOver();

    int selectBybirthdayByMonth();

    int selectByMemberCount();

    /**
     * 根据会员名/卡号查询会员信息
     * @param message 会员名/卡号
     * @return
     */
    /**
     * 查询会员当前积分
     * @param memberNumber 会员卡号
     * @return
     */
    int selectIntegralNumber(String memberNumber);

    /**
     * 修改会员积分
     * @param memberNumber 会员卡号
     * @param newIntegral 修改后的积分
     */
    void updateIntegralByNumber(@Param("memberNumber") Long memberNumber, @Param("newIntegral") int newIntegral);

    /**
     * 收银后减去会员的钱
     * @param id
     * @param multiply
     */
    void updateBlance(@Param("id") Long id, @Param("multiply") BigDecimal multiply);
    void updateIntegralByNumber(@Param("memberNumber") String memberNumber, @Param("newIntegral") int newIntegral);

    BigDecimal selectBalanceByNumber(String number);

    void updateBalanceByNumber(@Param("number") String number, @Param("big") BigDecimal big);
}