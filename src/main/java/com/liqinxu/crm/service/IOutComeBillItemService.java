package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.OutComeBillItem;
import com.liqinxu.crm.qo.OutComeBillItemQueryObject;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;
import java.util.Map;

public interface IOutComeBillItemService {
    int deleteByPrimaryKey(Long id);

    int insert(OutComeBillItem entity);

    OutComeBillItem selectByPrimaryKey(Long id);

    List<OutComeBillItem> selectAll();

    int updateByPrimaryKey(OutComeBillItem entity);

    PageResult query(QueryObject qo);

    List<Map<String,Object>> selectOutChart();
}
