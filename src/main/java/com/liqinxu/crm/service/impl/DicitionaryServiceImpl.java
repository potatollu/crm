package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.Dicitionary;
import com.liqinxu.crm.mapper.DicitionaryMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IDicitionaryService;
import com.liqinxu.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DicitionaryServiceImpl implements IDicitionaryService {

    @Autowired
    private DicitionaryMapper dicitionaryMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return dicitionaryMapper.deleteByPrimaryKey(id);
    }


    @Override
    public Dicitionary selectByPrimaryKey(Long id) {
        return dicitionaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Dicitionary> selectAll() {
        return dicitionaryMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(Dicitionary dicitionary) {
        if (dicitionary.getId() != null) {
            dicitionaryMapper.updateByPrimaryKey(dicitionary);
        } else {
            dicitionaryMapper.insert(dicitionary);
        }
    }
}
