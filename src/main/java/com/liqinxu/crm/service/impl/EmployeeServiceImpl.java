package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.Employee;
import com.liqinxu.crm.domain.Role;
import com.liqinxu.crm.mapper.EmployeeMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IEmployeeService;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        //删除中间表信息
        employeeMapper.deleteRelation(id);
        return employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Employee entity) {
        return employeeMapper.insert(entity);
    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Employee entity) {
        return employeeMapper.updateByPrimaryKey(entity);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = employeeMapper.queryForCount(qo);
        List<?> list = employeeMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }

    @Override
    public void saveOrUpdate(Employee employee) {
        if (employee.getId() != null) {
            //删除中间表信息
            employeeMapper.deleteRelation(employee.getId());
            employeeMapper.updateByPrimaryKey(employee);
        } else {
            employee.setState(true);
            Md5Hash md5Hash = new Md5Hash(employee.getPassword(), employee.getUsername(), 2);
            employee.setPassword(md5Hash.toString());
            employeeMapper.insert(employee);
        }
        //保存员工角色中间表信息
        for (Role role : employee.getRoles()) {
            employeeMapper.saveRelation(employee.getId(), role.getId());
        }
    }

    @Override
    public void changeState(Long id) {
        if (id != null) {
            employeeMapper.changeState(id);
        }
    }

    @Override
    public List<Long> selectRowsByEmpId(Long empId) {
        if (empId != null) {
            return employeeMapper.selectRowsByEmpId(empId);
        }
        return null;
    }

    @Override
    public void resetPassword(Long id, String newPassword, String username) {
        if (id != null && newPassword != null) {
            Md5Hash md5Hash = new Md5Hash(newPassword, username, 2);
            newPassword = md5Hash.toString();
            employeeMapper.resetPassword(id, newPassword);
        }
    }

    @Override
    public Employee login(String username) {
        return employeeMapper.login(username);
    }
}
