package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.TakeGiftItem;

import java.util.List;

/**
 * Created by jinyanfeng on 2018/3/29.
 */
public interface ITakeGiftItemService {
    int deleteByPrimaryKey(Long id);

    int insert(TakeGiftItem record);

    TakeGiftItem selectByPrimaryKey(Long id);

    List<TakeGiftItem> selectAll();

    int updateByPrimaryKey(TakeGiftItem record);
}
