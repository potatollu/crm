package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.ProductDir;
import com.liqinxu.crm.qo.ProductDirQueryObject;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IProductDirService {
    int deleteByPrimaryKey(Long id);

    ProductDir selectByPrimaryKey(Long id);

    List<ProductDir> selectAll();

    PageResult query(QueryObject qo);

    void saveOrUpdate(ProductDir productDir);

    List<ProductDir> selectProductDir();

    List<ProductDir> selectChildren(Long parentId);

    List<ProductDir> selectByParentId(ProductDirQueryObject qo);
}
