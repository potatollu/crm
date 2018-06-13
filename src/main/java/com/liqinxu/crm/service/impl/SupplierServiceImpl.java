package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.Employee;
import com.liqinxu.crm.domain.Supplier;
import com.liqinxu.crm.mapper.SupplierMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.ISupplierService;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class SupplierServiceImpl implements ISupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return supplierMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Supplier entity) {
        return supplierMapper.insert(entity);
    }


    @Override
    public int updateByPrimaryKey(Supplier entity) {
        return supplierMapper.updateByPrimaryKey(entity);
    }

    @Override
    public Supplier selectByPrimaryKey(Long id) {
        return supplierMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Supplier> selectAll() {
        return supplierMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(Supplier supplier) {
        if (supplier.getId() != null) {
            supplier.setInputTime(new Date());
            supplier.setInputUser((Employee)SecurityUtils.getSubject().getPrincipal());
            supplierMapper.updateByPrimaryKey(supplier);
        } else {
            supplier.setInputTime(new Date());
            supplier.setInputUser((Employee)SecurityUtils.getSubject().getPrincipal());
            supplierMapper.insert(supplier);
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = supplierMapper.queryForCount(qo);
        List<?> list = supplierMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }
}
