package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.Employee;
import com.liqinxu.crm.domain.Permission;
import com.liqinxu.crm.domain.Role;
import com.liqinxu.crm.mapper.RoleMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IRoleService;
import com.liqinxu.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        //删除关联关系
        roleMapper.deleteRelation(id);
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Role entity) {
        return roleMapper.insert(entity);
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Role entity) {
        return roleMapper.updateByPrimaryKey(entity);
    }

    @Override
    public void saveOrUpdate(Role role) {
        if (role.getId() != null) {
            //删除关联关系
            roleMapper.deleteRelation(role.getId());
            roleMapper.updateByPrimaryKey(role);
        } else {
            roleMapper.insert(role);
        }
        //迭代角色中的权限，保存到中间表上去
        for (Permission permission : role.getPermissions()) {
            roleMapper.saveRelation(role.getId(), permission.getId());
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = roleMapper.queryForCount(qo);
        List<?> list = roleMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }

    @Override
    public List<String> selectRolesByEmpId(Long employeeId) {
        if (employeeId != null) {
            return roleMapper.selectRolesByEmpId(employeeId);
        }
        return null;
    }
}
