package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.Permission;
import com.liqinxu.crm.qo.QueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission entity);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);

    List<String> selectAllPermission();

    List<Permission> selectByRoleId(Long roleId);

    List<String> selectRolesByEmpId(Long employeeId);
}