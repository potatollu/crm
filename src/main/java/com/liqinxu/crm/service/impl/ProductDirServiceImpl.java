package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.ProductDir;
import com.liqinxu.crm.mapper.ProductDirMapper;
import com.liqinxu.crm.qo.ProductDirQueryObject;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IProductDirService;
import com.liqinxu.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductDirServiceImpl implements IProductDirService {

    @Autowired
    private ProductDirMapper productDirMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return productDirMapper.deleteByPrimaryKey(id);
    }


    @Override
    public ProductDir selectByPrimaryKey(Long id) {
        return productDirMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProductDir> selectAll() {
        return productDirMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(ProductDir productDir) {
        if (productDir.getId() != null) {
            productDirMapper.updateByPrimaryKey(productDir);
        } else {
            productDirMapper.insert(productDir);
        }
    }

    @Override
    public List<ProductDir> selectProductDir() {
        return productDirMapper.selectProductDir();
    }

    @Override
    public List<ProductDir> selectChildren(Long parentId) {
        return productDirMapper.selectChildren(parentId);
    }

    @Override
    public List<ProductDir> selectByParentId(ProductDirQueryObject qo) {
        return productDirMapper.selectByParentId(qo);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = productDirMapper.queryForCount(qo);
        List<?> list = productDirMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }
}
