package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.Serve;
import com.liqinxu.crm.mapper.ServeMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IServeService;
import com.liqinxu.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ServeServiceImpl implements IServeService {

    @Autowired
    private ServeMapper serveMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return serveMapper.deleteByPrimaryKey(id);
    }


    @Override
    public Serve selectByPrimaryKey(Long id) {
        return serveMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Serve> selectAll() {
        return serveMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(Serve serve) {
        if (serve.getId() != null) {
            serveMapper.updateByPrimaryKey(serve);
        } else {
            serveMapper.insert(serve);
        }
    }

    public Serve selectBySn(String sn) {
        return serveMapper.selectBySn(sn);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = serveMapper.queryForCount(qo);
        List<?> list = serveMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }
}
