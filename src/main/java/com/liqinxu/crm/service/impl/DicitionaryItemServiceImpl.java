package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.DicitionaryItem;
import com.liqinxu.crm.mapper.DicitionaryItemMapper;
import com.liqinxu.crm.service.IDicitionaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DicitionaryItemServiceImpl implements IDicitionaryItemService {

    @Autowired
    private DicitionaryItemMapper dicitionaryItemMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return dicitionaryItemMapper.deleteByPrimaryKey(id);
    }


    @Override
    public DicitionaryItem selectByPrimaryKey(Long id) {
        return dicitionaryItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<DicitionaryItem> selectAll() {
        return dicitionaryItemMapper.selectAll();
    }

    public void saveOrUpdate(DicitionaryItem dicitionary) {
        if (dicitionary.getId() != null) {
            dicitionaryItemMapper.updateByPrimaryKey(dicitionary);
        } else {
            dicitionaryItemMapper.insert(dicitionary);
        }
    }

    public List<DicitionaryItem> selectBySn(String sn) {
        return dicitionaryItemMapper.selectBySn(sn);
    }
}
