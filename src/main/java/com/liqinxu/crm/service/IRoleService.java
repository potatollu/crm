package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.Employee;
import com.liqinxu.crm.domain.Role;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.Collection;
import java.util.List;

public interface IRoleService {
    int deleteByPrimaryKey(Long id);

    int insert(Role entity);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role entity);

    void saveOrUpdate(Role entity);

    PageResult query(QueryObject qo);

    List<String> selectRolesByEmpId(Long employeeId);
}
