package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.OutComeBillItem;
import com.liqinxu.crm.qo.OutComeBillItemQueryObject;
import com.liqinxu.crm.qo.QueryObject;

import java.util.List;
import java.util.Map;

public interface OutComeBillItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OutComeBillItem entity);

    OutComeBillItem selectByPrimaryKey(Long id);

    List<OutComeBillItem> selectAll();

    int updateByPrimaryKey(OutComeBillItem entity);

    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);

    List<Map<String,Object>> selectOutChart();
}