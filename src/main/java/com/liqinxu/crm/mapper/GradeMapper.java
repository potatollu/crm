package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.Grade;
import java.util.List;

public interface GradeMapper {
    /**
     * 等级增删改查方法
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    int insert(Grade entity);

    Grade selectByPrimaryKey(Long id);

    List<Grade> selectAll();

    int updateByPrimaryKey(Grade entity);
}