package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.SystemLog;
import com.liqinxu.crm.mapper.SystemLogMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.ISystemLogService;
import com.liqinxu.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SystemLogServiceImpl implements ISystemLogService {

    @Autowired
    private SystemLogMapper systemLogMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return systemLogMapper.deleteByPrimaryKey(id);
    }


    @Override
    public SystemLog selectByPrimaryKey(Long id) {
        return systemLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemLog> selectAll() {
        return systemLogMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(SystemLog systemLog) {
        if (systemLog.getId() != null) {
            systemLogMapper.updateByPrimaryKey(systemLog);
        } else {
            systemLogMapper.insert(systemLog);
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = systemLogMapper.queryForCount(qo);
        List<?> list = systemLogMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }
}
