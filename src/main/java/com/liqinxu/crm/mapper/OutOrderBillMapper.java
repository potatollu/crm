package com.liqinxu.crm.mapper;


import com.liqinxu.crm.domain.OutOrderBill;
import com.liqinxu.crm.qo.QueryObject;

import java.util.List;

public interface OutOrderBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OutOrderBill record);

    OutOrderBill selectByPrimaryKey(Long id);

    List<OutOrderBill> selectAll();

    int updateByPrimaryKey(OutOrderBill record);

    int queryForCount(QueryObject queryObject);

    List<OutOrderBill> queryForList(QueryObject queryObject);

    void audit(OutOrderBill old);
}