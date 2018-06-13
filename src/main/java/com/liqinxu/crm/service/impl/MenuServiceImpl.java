package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.Menu;
import com.liqinxu.crm.domain.Permission;
import com.liqinxu.crm.domain.Role;
import com.liqinxu.crm.mapper.MenuMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IMenuService;
import com.liqinxu.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {


    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void saveOrUpdate(Menu entity) {
        if (entity.getId() != null) {
            menuMapper.updateByPrimaryKey(entity);
        } else {
            menuMapper.insert(entity);
        }
    }

    @Override
    public Menu selectByPrimaryKey(Long id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Menu> selectAll() {
        return menuMapper.selectAll();
    }

    @Override
    public List<Menu> selectRootMenu() {
        return menuMapper.selectRootMenu();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = menuMapper.queryForCount(qo);
        List<?> list = menuMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }

    @Override
    public Menu selectChildMenu(Long id) {
        return menuMapper.selectChildMenu(id);
    }

    @Override
    public void checkPermiassion(List<Menu> menus) {
        Iterator<Menu> iterator = menus.iterator();
        Subject subject = SecurityUtils.getSubject();
        //1、遍历菜单集合，取得每一个菜单
        while (iterator.hasNext()) {
            Menu menu = iterator.next();
            //2、判断该菜单是否关联权限，就需要进行权限判断，否则就直接放行不检查
            Permission permission = menu.getPermission();
            if (permission != null) {
                //3、如果没有权限就从集合中删除
                if (!subject.isPermitted(permission.getResource())) {
                    iterator.remove();
                    continue;
                }
            }
            //递归判断子菜单
            if (menu.getChildren().size() > 0) {
                checkPermiassion(menu.getChildren());
            }
        }
    }
}
