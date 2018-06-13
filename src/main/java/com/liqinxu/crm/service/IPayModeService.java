package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.PayMode;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IPayModeService {
    int deleteByPrimaryKey(Long id);

    PayMode selectByPrimaryKey(Long id);

    List<PayMode> selectAll();

    void saveOrUpdate(PayMode payMode);
}
