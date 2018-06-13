package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.OutComeType;
import java.util.List;

public interface OutComeTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OutComeType entity);

    OutComeType selectByPrimaryKey(Long id);

    List<OutComeType> selectAll();

    int updateByPrimaryKey(OutComeType entity);
}