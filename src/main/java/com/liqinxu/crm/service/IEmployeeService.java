package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.Employee;
import com.liqinxu.crm.domain.Role;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IEmployeeService {
    int deleteByPrimaryKey(Long id);

    int insert(Employee entity);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee entity);

    PageResult query(QueryObject qo);

    void saveOrUpdate(Employee employee);

    void changeState(Long id);

    List<Long> selectRowsByEmpId(Long empId);

    void resetPassword(Long id, String newPassword,String username);

    Employee login(String username);
}
