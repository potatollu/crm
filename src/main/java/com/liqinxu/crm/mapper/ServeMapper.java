package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.Serve;
import com.liqinxu.crm.qo.QueryObject;

import java.util.List;

public interface ServeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Serve entity);

    Serve selectByPrimaryKey(Long id);

    List<Serve> selectAll();

    int updateByPrimaryKey(Serve entity);
    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);

    Serve selectBySn(String sn);
}