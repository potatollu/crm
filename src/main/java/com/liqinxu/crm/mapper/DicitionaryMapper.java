package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.Dicitionary;
import java.util.List;

public interface DicitionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Dicitionary entity);

    Dicitionary selectByPrimaryKey(Long id);

    List<Dicitionary> selectAll();

    int updateByPrimaryKey(Dicitionary entity);
}