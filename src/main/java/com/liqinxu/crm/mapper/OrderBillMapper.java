package com.liqinxu.crm.mapper;


import com.liqinxu.crm.domain.OrderBill;
import com.liqinxu.crm.qo.QueryObject;

import java.util.List;

public interface OrderBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderBill record);

    OrderBill selectByPrimaryKey(Long id);

    List<OrderBill> selectAll();

    int updateByPrimaryKey(OrderBill record);

    int queryForCount(QueryObject queryObject);

    List<OrderBill> queryForList(QueryObject queryObject);

    void audit(OrderBill old);

}