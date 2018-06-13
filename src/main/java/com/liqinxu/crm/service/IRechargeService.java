package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.Recharge;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IRechargeService {
    int deleteByPrimaryKey(Long id);

    Recharge selectByPrimaryKey(Long id);

    List<Recharge> selectAll();

    PageResult query(QueryObject qo);

    void saveOrUpdate(Recharge recharge);

    Object selectTotalCost(String clientNumber);
}
