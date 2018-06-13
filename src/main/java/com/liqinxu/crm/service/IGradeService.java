package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.Grade;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IGradeService {
    int deleteByPrimaryKey(Long id);

    Grade selectByPrimaryKey(Long id);

    List<Grade> selectAll();

    void saveOrUpdate(Grade grade);
}
