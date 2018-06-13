package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.Department;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IDepartmentService {
    int deleteByPrimaryKey(Long id);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    PageResult query(QueryObject qo);

    void saveOrUpdate(Department department);
}
