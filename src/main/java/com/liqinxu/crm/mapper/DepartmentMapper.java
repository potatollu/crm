package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.Department;
import com.liqinxu.crm.qo.QueryObject;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department entity);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department entity);

    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);
}