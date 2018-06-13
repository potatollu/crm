package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.Member;
import com.liqinxu.crm.domain.MemberPayNumber;
import com.liqinxu.crm.domain.Recharge;
import com.liqinxu.crm.mapper.MemberMapper;
import com.liqinxu.crm.mapper.RechargeMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IRechargeService;
import com.liqinxu.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class RechargeServiceImpl implements IRechargeService {

    @Autowired
    private RechargeMapper rechargeMapper;
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return rechargeMapper.deleteByPrimaryKey(id);
    }


    @Override
    public Recharge selectByPrimaryKey(Long id) {
        return rechargeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Recharge> selectAll() {
        return rechargeMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(Recharge recharge) {
        //根据会员卡号查询会员信息,再保存进去
        String number = recharge.getMember().getNumber();
        //增加对应的会员积分:根据卡号取出会员积分进行处理
        int integral = memberMapper.selectIntegralNumber(number);
        //根据卡号查询余额
        BigDecimal big = memberMapper.selectBalanceByNumber(number);
        if (recharge.getId() != null) {
            rechargeMapper.updateByPrimaryKey(recharge);
        } else {
            //保存信息
            Member member =  memberMapper.selectByNumber(number);
            recharge.setMember(member);
            //保存充值时间
            recharge.setValidTime(new Date());
            recharge.setOperationType(recharge.getOperationType());
            //根据会员卡号查询充值前金额和充值后金额
            List<Recharge> amounts = rechargeMapper.selectByNumber(number);
            if (amounts.size() <= 0){
                recharge.setPayBeforeNum(0);
                recharge.setPayAfterNum(recharge.getFund().intValue());
                //冲多少钱就送多少分,修改会员余额
                int newIntegral = integral + recharge.getFund().intValue();
                memberMapper.updateIntegralByNumber(number,newIntegral);
                //修改余额
                BigDecimal big2 = new BigDecimal(recharge.getSendNumber().toString());
                BigDecimal big3 = recharge.getFund().add(big2);
                if (big == null){
                    big = new BigDecimal("0");
                }
                big = big.add(big3);
                memberMapper.updateBalanceByNumber(number,big);
            }else {
                //取最后一个对象
                Recharge amount = amounts.get(amounts.size() - 1);
                //数据库里面充值前金额
                Integer beforeNum = amount.getPayBeforeNum();
                if (beforeNum == null){
                    recharge.setPayBeforeNum(0);
                }else {
                    recharge.setPayBeforeNum(amount.getPayAfterNum());
                }
                //数据库里面的充值后数据
                Integer afterNum = amount.getPayAfterNum();
                if (afterNum == null){
                    recharge.setPayAfterNum(recharge.getFund().intValue());
                }else {
                    recharge.setPayAfterNum(afterNum + recharge.getFund().intValue());
                }
                //增加对应的积分
                int newIntegral = integral + recharge.getFund().intValue();
                memberMapper.updateIntegralByNumber(number,newIntegral);
                //修改余额
                big = big.add(recharge.getFund()).add(new BigDecimal(recharge.getSendNumber()));
                memberMapper.updateBalanceByNumber(number,big);
            }
            rechargeMapper.insert(recharge);
        }
    }

    public Object selectTotalCost(String clientNumber) {
        return null;
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = rechargeMapper.queryForCount(qo);
        List<?> list = rechargeMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }
}
