package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.Employee;
import com.liqinxu.crm.domain.Role;
import com.liqinxu.crm.qo.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    /**
     * 增加员工方法
     * @param entity 员工对象
     * @return  返回修改行数
     */
    int insert(Employee entity);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee entity);

    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);

    void changeState(Long id);

    void saveRelation(@Param("employeeId") Long employeeId, @Param("roleId") Long roleId);

    void deleteRelation(Long employeeId);

    List<Long> selectRowsByEmpId(Long empId);

    void resetPassword(@Param("id") Long id, @Param("newPassword") String newPassword);

    Employee login(String username);
}