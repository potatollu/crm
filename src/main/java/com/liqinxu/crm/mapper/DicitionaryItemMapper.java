package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.DicitionaryItem;
import java.util.List;

public interface DicitionaryItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DicitionaryItem entity);

    DicitionaryItem selectByPrimaryKey(Long id);

    List<DicitionaryItem> selectAll();

    int updateByPrimaryKey(DicitionaryItem entity);

    List<DicitionaryItem> selectBySn(String sn);
}