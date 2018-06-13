package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.Serve;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IServeService {
    int deleteByPrimaryKey(Long id);

    Serve selectByPrimaryKey(Long id);

    List<Serve> selectAll();

    PageResult query(QueryObject qo);

    void saveOrUpdate(Serve serve);

    Serve selectBySn(String sn);
}
