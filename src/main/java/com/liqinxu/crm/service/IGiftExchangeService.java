package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.GiftExchange;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

/**
 * Created by jinyanfeng on 2018/3/27.
 */
public interface IGiftExchangeService  {
    int deleteByPrimaryKey(Long id);

    int insert(GiftExchange record);

    List<GiftExchange> selectAll();

    PageResult query(QueryObject qo);

}
