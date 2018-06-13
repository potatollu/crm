package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.Dicitionary;
import com.liqinxu.crm.domain.DicitionaryItem;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IDicitionaryItemService {
    int deleteByPrimaryKey(Long id);

    DicitionaryItem selectByPrimaryKey(Long id);

    List<DicitionaryItem> selectAll();

    void saveOrUpdate(DicitionaryItem dicitionaryItem);

    List<DicitionaryItem> selectBySn(String sn);
}
