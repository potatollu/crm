package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.SystemLog;
import com.liqinxu.crm.qo.QueryObject;

import java.util.List;

public interface SystemLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemLog entity);

    SystemLog selectByPrimaryKey(Long id);

    List<SystemLog> selectAll();

    int updateByPrimaryKey(SystemLog entity);

    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);
}