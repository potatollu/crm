package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.OutComeType;
import com.liqinxu.crm.mapper.OutComeTypeMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IOutComeTypeService;
import com.liqinxu.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OutComeTypeServiceImpl implements IOutComeTypeService {

    @Autowired
    private OutComeTypeMapper outComeTypeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return outComeTypeMapper.deleteByPrimaryKey(id);
    }


    @Override
    public OutComeType selectByPrimaryKey(Long id) {
        return outComeTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OutComeType> selectAll() {
        return outComeTypeMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(OutComeType outComeType) {
        if (outComeType.getId() != null) {
            outComeTypeMapper.updateByPrimaryKey(outComeType);
        } else {
            outComeTypeMapper.insert(outComeType);
        }
    }

}
