package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.CashierDetailProduct;

import java.util.List;

public interface CashierDetailProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CashierDetailProduct entity);

    CashierDetailProduct selectByPrimaryKey(Long id);

    List<CashierDetailProduct> selectAll();

    int updateByPrimaryKey(CashierDetailProduct entity);
}