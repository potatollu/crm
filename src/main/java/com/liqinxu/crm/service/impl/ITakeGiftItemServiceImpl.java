package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.TakeGiftItem;
import com.liqinxu.crm.mapper.TakeGiftItemMapper;
import com.liqinxu.crm.service.ITakeGiftItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jinyanfeng on 2018/3/29.
 */
@Service
public class ITakeGiftItemServiceImpl implements ITakeGiftItemService {
    @Autowired
    public TakeGiftItemMapper takeGiftItemMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return takeGiftItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TakeGiftItem record) {
        return takeGiftItemMapper.insert(record);
    }

    @Override
    public TakeGiftItem selectByPrimaryKey(Long id) {
        return takeGiftItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TakeGiftItem> selectAll() {
        return takeGiftItemMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(TakeGiftItem record) {
        return takeGiftItemMapper.updateByPrimaryKey(record);
    }
}
