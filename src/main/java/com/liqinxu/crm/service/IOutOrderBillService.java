package com.liqinxu.crm.service;


import com.liqinxu.crm.domain.OutOrderBill;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IOutOrderBillService {
    int deleteByPrimaryKey(Long id);

    int insert(OutOrderBill record);

    OutOrderBill selectByPrimaryKey(Long id);

    List<OutOrderBill> selectAll();

    int updateByPrimaryKey(OutOrderBill record);

    PageResult query(QueryObject queryObject);

    void audit(Long id);
}
