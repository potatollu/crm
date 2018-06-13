package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.PayMode;
import com.liqinxu.crm.mapper.PayModeMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IPayModeService;
import com.liqinxu.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PayModeServiceImpl implements IPayModeService {

    @Autowired
    private PayModeMapper payModeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return payModeMapper.deleteByPrimaryKey(id);
    }


    @Override
    public PayMode selectByPrimaryKey(Long id) {
        return payModeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PayMode> selectAll() {
        return payModeMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(PayMode payMode) {
        if (payMode.getId() != null) {
            payModeMapper.updateByPrimaryKey(payMode);
        } else {
            payModeMapper.insert(payMode);
        }
    }

}
