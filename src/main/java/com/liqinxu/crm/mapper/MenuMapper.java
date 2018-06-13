package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.Menu;
import com.liqinxu.crm.qo.QueryObject;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu entity);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu entity);

    List<Menu> selectRootMenu();

    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);

    Menu selectChildMenu(Long id);

}