package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.Grouping;
import java.util.List;

public interface GroupingMapper {
    /**
     * 分组增删改差方法
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    int insert(Grouping entity);

    Grouping selectByPrimaryKey(Long id);

    List<Grouping> selectAll();

    int updateByPrimaryKey(Grouping entity);
}