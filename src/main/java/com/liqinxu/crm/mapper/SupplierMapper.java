package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.Supplier;
import com.liqinxu.crm.qo.QueryObject;

import java.util.List;

public interface SupplierMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Supplier record);

    Supplier selectByPrimaryKey(Long id);

    List<Supplier> selectAll();

    int updateByPrimaryKey(Supplier record);

    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);
}