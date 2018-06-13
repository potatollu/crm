package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.Permission;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IPermissionService {
    int deleteByPrimaryKey(Long id);

    int insert(Permission entity);

    List<Permission> selectAll();

    PageResult query(QueryObject qo);

    List<Permission> selectByRoleId(Long roleId);

    void reload();

    List<String> selectPermissionsByEmpId(Long employeeId);
}
