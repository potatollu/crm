package com.liqinxu.crm.service;


import com.liqinxu.crm.domain.OrderBill;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IOrderBillService {
    int deleteByPrimaryKey(Long id);

    int insert(OrderBill record);

    OrderBill selectByPrimaryKey(Long id);

    List<OrderBill> selectAll();

    int updateByPrimaryKey(OrderBill record);

    PageResult query(QueryObject queryObject);

    void audit(Long id);

}
