package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.GiftExchange;
import com.liqinxu.crm.qo.QueryObject;

import java.util.List;

public interface GiftExchangeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GiftExchange record);

    GiftExchange selectByPrimaryKey(Long id);

    List<GiftExchange> selectAll();

    int updateByPrimaryKey(GiftExchange record);

    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);
}