package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.Recharge;
import com.liqinxu.crm.qo.QueryObject;

import java.util.List;

public interface RechargeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Recharge entity);

    Recharge selectByPrimaryKey(Long id);

    List<Recharge> selectAll();

    int updateByPrimaryKey(Recharge entity);
    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);

    List<Recharge> selectByNumber(String number);
}