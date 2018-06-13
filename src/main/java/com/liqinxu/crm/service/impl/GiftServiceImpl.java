package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.*;
import com.liqinxu.crm.mapper.*;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IGiftService;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class GiftServiceImpl implements IGiftService {

    @Autowired
    private GiftMapper giftMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private GiftExchangeMapper giftExchangeMapper;
    @Autowired
    private TakeGiftItemMapper takeGiftItemMapper;
    @Autowired
    private IntegralChangeMapper integralChangeMapper;
    /**
     * 删除礼品信息
     * @param id 需删除对象的id
     * @return 影响的行数
     */
    public int deleteByPrimaryKey(Long id) {
        return giftMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加礼品信息
     * @param record 礼品对象
     * @return 影响的行数
     */
    public int insert(Gift record) {
        return giftMapper.insert(record);
    }

    /**
     * 查询单个礼品信息
     * @param id 查询对象的id
     * @return 影响的行数
     */
    public Gift selectByPrimaryKey(Long id) {
        return giftMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有礼品信息
     * @return 封装所有礼品数据的集合
     */
    public List<Gift> selectAll() {
        return giftMapper.selectAll();
    }

    /**
     * 编辑礼品信息
     * @param record 要编辑的礼品对象
     * @return 影响的行数
     */
    public int updateByPrimaryKey(Gift record) {
        return giftMapper.updateByPrimaryKey(record);
    }

    /**
     * 分页查询礼品信息
     * @param query 高级查询GiftQuery类的对象
     * @return PageResult对象
     */
    public PageResult query(QueryObject query) {
        int count = giftMapper.queryCount(query);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count,giftMapper.queryList(query));
    }

    /**
     * 礼品兑换操作
     */
    @Override
    public void makeExchage(TakeGiftItem takeGiftItem) {

        long changeSum = 0; //积分变动数目

            String number = takeGiftItem.getNumber(); //获取会员卡号,唯一标识
            //获取会员对象
            Member member = memberMapper.getMemberByNumber(number);

            //兑换礼品
                Gift gift = giftMapper.selectByPrimaryKey(takeGiftItem.getId());

                Long needIntegral = takeGiftItem.getNeedIntegral();

                //礼品数量不足
                 if(gift.getNumber() < needIntegral){
                    throw new RuntimeException("礼品数量不足,当前数目:"+ gift.getNumber() +"需要数目:"+ needIntegral);
                }
                //更新礼品数量
                gift.setNumber(gift.getNumber() - needIntegral);
                giftMapper.updateCurrentNum(gift);

                //兑换记录对象
                GiftExchange record = new GiftExchange();
                record.setMember(member); //会员名称
                record.setIntegral(gift.getIntegral()); //兑换积分
                record.setNumber((long) needIntegral); //兑换数量
                record.setGift(gift);//礼品名称
                record.setEmployee((Employee) SecurityUtils.getSubject().getPrincipal());
                record.setUnit(gift.getUnit());
                record.setIntegraldate(new Date());

                //保存兑换记录
                giftExchangeMapper.insert(record);
                //积分变动数目
                changeSum = gift.getIntegral();
         //更新会员积分
            member.setIntegral(member.getIntegral() - (int) (changeSum));
            memberMapper.updateByPrimaryKey(member);
            //积分变动对象
           IntegralChange change = new IntegralChange();
            change.setMemberNumber(member.getNumber()); //会员卡号
            change.setMemberName(member.getName()); //会员名称
            change.setGrade("白金会员"); //会员等级
            change.setChangeType("扣除"); //操作类型
            change.setRemark("兑换礼品");
            change.setChaneTime(new Date());
            change.setAmount("-"+gift.getIntegral());
            //保存积分变动对象0
            integralChangeMapper.insert(change);
         }
    @Override
    public Object queryAll() {
        return giftMapper.queryAll();
    }
}
