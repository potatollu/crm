package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.SystemLog;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface ISystemLogService {

    int deleteByPrimaryKey(Long id);

    SystemLog selectByPrimaryKey(Long id);

    List<SystemLog> selectAll();

    PageResult query(QueryObject qo);

    void saveOrUpdate(SystemLog systemLog);
}
