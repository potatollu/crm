package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.Employee;
import com.liqinxu.crm.domain.OutComeBillItem;
import com.liqinxu.crm.domain.Role;
import com.liqinxu.crm.mapper.OutComeBillItemMapper;
import com.liqinxu.crm.qo.OutComeBillItemQueryObject;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IOutComeBillItemService;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class OutComeBillItemServiceImpl implements IOutComeBillItemService {

    @Autowired
    private OutComeBillItemMapper outComeBillItemMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return outComeBillItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OutComeBillItem entity) {
        //Employee subject = (Employee)SecurityUtils.getSubject();
        Employee subject = (Employee)SecurityUtils.getSubject().getPrincipal();
        entity.setOutputUser(subject);
        return outComeBillItemMapper.insert(entity);
    }

    @Override
    public OutComeBillItem selectByPrimaryKey(Long id) {
        return outComeBillItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<OutComeBillItem> selectAll() {
        return outComeBillItemMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(OutComeBillItem entity) {
        Employee subject = (Employee)SecurityUtils.getSubject().getPrincipal();
        entity.setOutputUser(subject);
        return outComeBillItemMapper.updateByPrimaryKey(entity);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = outComeBillItemMapper.queryForCount(qo);
        List<?> list = outComeBillItemMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }

    @Override
    public List<Map<String, Object>> selectOutChart() {
        return outComeBillItemMapper.selectOutChart();
    }


}
