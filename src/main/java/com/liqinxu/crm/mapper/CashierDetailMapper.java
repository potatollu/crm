package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.CashierDetail;
import java.util.List;

public interface CashierDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CashierDetail entity);

    CashierDetail selectByPrimaryKey(Long id);

    List<CashierDetail> selectAll();

    int updateByPrimaryKey(CashierDetail entity);

}