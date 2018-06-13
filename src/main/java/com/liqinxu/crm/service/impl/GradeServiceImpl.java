package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.Grade;
import com.liqinxu.crm.mapper.GradeMapper;
import com.liqinxu.crm.service.IGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements IGradeService {

    @Autowired
    private GradeMapper gradeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return gradeMapper.deleteByPrimaryKey(id);
    }


    @Override
    public Grade selectByPrimaryKey(Long id) {
        return gradeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Grade> selectAll() {
        return gradeMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(Grade grade) {
        if (grade.getId() != null) {
            gradeMapper.updateByPrimaryKey(grade);
        } else {
            gradeMapper.insert(grade);
        }
    }
}
