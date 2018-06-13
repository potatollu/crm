package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.GiftExchange;
import com.liqinxu.crm.mapper.GiftExchangeMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IGiftExchangeService;
import com.liqinxu.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by jinyanfeng on 2018/3/27.
 */
@Service
public class GiftExchangeServiceImpl implements IGiftExchangeService {
    @Autowired
    public GiftExchangeMapper giftExchangeMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(GiftExchange record) {
        return giftExchangeMapper.insert(record);
    }

    @Override
    public List<GiftExchange> selectAll() {
        return giftExchangeMapper.selectAll();
    }
    @Override
    public PageResult query(QueryObject qo) {
        int count = giftExchangeMapper.queryForCount(qo);
        List<?> list = giftExchangeMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }
}
