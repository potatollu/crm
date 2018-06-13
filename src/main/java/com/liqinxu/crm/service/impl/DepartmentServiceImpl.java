package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.Department;
import com.liqinxu.crm.domain.Employee;
import com.liqinxu.crm.domain.Role;
import com.liqinxu.crm.mapper.DepartmentMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IDepartmentService;
import com.liqinxu.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return departmentMapper.deleteByPrimaryKey(id);
    }


    @Override
    public Department selectByPrimaryKey(Long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(Department department) {
        if (department.getId() != null) {
            departmentMapper.updateByPrimaryKey(department);
        } else {
            departmentMapper.insert(department);
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = departmentMapper.queryForCount(qo);
        List<?> list = departmentMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }
}
