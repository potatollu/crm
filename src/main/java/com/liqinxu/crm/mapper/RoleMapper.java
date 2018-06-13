package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.Role;
import com.liqinxu.crm.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role entity);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role entity);

    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);

    void saveRelation(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    void deleteRelation(Long id);

    List<String> selectRolesByEmpId(Long employeeId);
}