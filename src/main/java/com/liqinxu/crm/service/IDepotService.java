package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.Depot;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IDepotService {
    
    int deleteByPrimaryKey(Long id);

    int insert(Depot entity);

    Depot selectByPrimaryKey(Long id);

    List<Depot> selectAll();

    int updateByPrimaryKey(Depot entity);

    void saveOrUpdate(Depot depot);

    PageResult query(QueryObject qo);
}
