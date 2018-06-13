package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.Expenditure;
import java.util.List;

public interface ExpenditureMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Expenditure entity);

    Expenditure selectByPrimaryKey(Long id);

    List<Expenditure> selectAll();

    int updateByPrimaryKey(Expenditure entity);


}