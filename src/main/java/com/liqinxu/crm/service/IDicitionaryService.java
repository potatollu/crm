package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.Dicitionary;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IDicitionaryService {
    int deleteByPrimaryKey(Long id);

    Dicitionary selectByPrimaryKey(Long id);

    List<Dicitionary> selectAll();

    void saveOrUpdate(Dicitionary dicitionary);
}
