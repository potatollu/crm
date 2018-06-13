package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.Supplier;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface ISupplierService {
    
    int deleteByPrimaryKey(Long id);

    int insert(Supplier entity);

    Supplier selectByPrimaryKey(Long id);

    List<Supplier> selectAll();

    int updateByPrimaryKey(Supplier entity);

    void saveOrUpdate(Supplier supplier);


    PageResult query(QueryObject qo);
}
