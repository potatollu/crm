package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.OutComeType;

import java.util.List;

public interface IOutComeTypeService {
    int deleteByPrimaryKey(Long id);

    OutComeType selectByPrimaryKey(Long id);

    List<OutComeType> selectAll();

    void saveOrUpdate(OutComeType outComeType);
}
