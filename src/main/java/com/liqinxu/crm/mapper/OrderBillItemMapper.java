package com.liqinxu.crm.mapper;


import com.liqinxu.crm.domain.OrderBillItem;
import com.liqinxu.crm.util.PageResult;

public interface OrderBillItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderBillItem record);

    void deleteByBillId(Long id);

    PageResult selectByBillId();
}