package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.Menu;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IMenuService {
    int deleteByPrimaryKey(Long id);

    void saveOrUpdate(Menu entity);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    List<Menu> selectRootMenu();

    PageResult query(QueryObject qo);

    Menu selectChildMenu(Long id);

    void checkPermiassion(List<Menu> menus);
}
