package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.TakeGiftItem;
import java.util.List;

public interface TakeGiftItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TakeGiftItem record);

    TakeGiftItem selectByPrimaryKey(Long id);

    List<TakeGiftItem> selectAll();

    int updateByPrimaryKey(TakeGiftItem record);
}