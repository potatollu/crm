package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.Depot;
import com.liqinxu.crm.qo.QueryObject;

import java.util.List;

public interface DepotMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Depot record);

    Depot selectByPrimaryKey(Long id);

    List<Depot> selectAll();

    int updateByPrimaryKey(Depot record);

    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);

}