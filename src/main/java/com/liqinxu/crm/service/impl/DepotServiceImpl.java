package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.Depot;
import com.liqinxu.crm.mapper.DepotMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IDepotService;
import com.liqinxu.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DepotServiceImpl implements IDepotService {

    @Autowired
    private DepotMapper depotMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return depotMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Depot entity) {
        return depotMapper.insert(entity);
    }


    @Override
    public int updateByPrimaryKey(Depot entity) {
        return depotMapper.updateByPrimaryKey(entity);
    }

    @Override
    public Depot selectByPrimaryKey(Long id) {
        return depotMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Depot> selectAll() {
        return depotMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(Depot depot) {
        if (depot.getId() != null) {
            depotMapper.updateByPrimaryKey(depot);
        } else {
            depotMapper.insert(depot);
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = depotMapper.queryForCount(qo);
        List<?> list = depotMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }
}
