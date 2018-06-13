package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.StockTaking;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IStockTakingService {
    int deleteByPrimaryKey(Long id);

    StockTaking selectByPrimaryKey(Long id);

    List<StockTaking> selectAll();

    PageResult query(QueryObject qo);

    void saveOrUpdate(StockTaking StockTaking);
}
