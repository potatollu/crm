package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.Permission;
import com.liqinxu.crm.mapper.PermissionMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IPermissionService;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @SuppressWarnings("all")
    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Permission entity) {
        return permissionMapper.insert(entity);
    }

    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = permissionMapper.queryForCount(qo);
        List<?> list = permissionMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }

    @Override
    public List<Permission> selectByRoleId(Long roleId) {
        return permissionMapper.selectByRoleId(roleId);
    }

    @Override
    public void reload() {
        //获取数据库中已有的权限表达式
        List<String> permissions = permissionMapper.selectAllPermission();
        //获取所有Controller贴了RequestMapping的方法
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        Collection<HandlerMethod> values = handlerMethods.values();
        for (HandlerMethod method : values) {
            //判断这个方法是否贴有权限注解
            RequiresPermissions methodAnnotation = method.getMethodAnnotation(RequiresPermissions.class);
            if (methodAnnotation != null) {
                //判断数据库中是否已经存在
                if (!permissions.contains(methodAnnotation.value()[1])) {
                    Permission permission = new Permission();
                    permission.setName(methodAnnotation.value()[1]);
                    permission.setResource(methodAnnotation.value()[0]);
                    permissionMapper.insert(permission);
                }
            }
        }
    }

    @Override
    public List<String> selectPermissionsByEmpId(Long employeeId) {
        if (employeeId != null) {
            return permissionMapper.selectRolesByEmpId(employeeId);
        }
        return null;
    }
}
