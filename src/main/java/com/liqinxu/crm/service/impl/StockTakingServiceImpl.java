package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.Employee;
import com.liqinxu.crm.domain.StockTaking;
import com.liqinxu.crm.mapper.StockTakingMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IStockTakingService;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class StockTakingServiceImpl implements IStockTakingService {

    @Autowired
    private StockTakingMapper stockTakingMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return stockTakingMapper.deleteByPrimaryKey(id);
    }


    @Override
    public StockTaking selectByPrimaryKey(Long id) {
        return stockTakingMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<StockTaking> selectAll() {
        return stockTakingMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(StockTaking stockTaking) {
        if (stockTaking.getId() != null) {
            //当前时间
            stockTaking.setTakeTime(new Date());
            //当前操作用户
            Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
            stockTaking.setEmployeeName(employee.getUsername());
            stockTakingMapper.updateByPrimaryKey(stockTaking);
        } else {
            stockTakingMapper.insert(stockTaking);
        }
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = stockTakingMapper.queryCount(qo);
        List<?> list = stockTakingMapper.queryList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }
}
