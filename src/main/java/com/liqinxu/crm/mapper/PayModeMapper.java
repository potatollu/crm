package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.PayMode;
import java.util.List;

public interface PayModeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PayMode entity);

    PayMode selectByPrimaryKey(Long id);

    List<PayMode> selectAll();

    int updateByPrimaryKey(PayMode entity);
}