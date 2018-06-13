package com.liqinxu.crm.mapper;


import com.liqinxu.crm.domain.OutOrderBillItem;

public interface OutOrderBillItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OutOrderBillItem record);

    void deleteByBillId(Long id);
}