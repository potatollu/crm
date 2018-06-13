package com.liqinxu.crm.service;

import com.liqinxu.crm.domain.Product;
import com.liqinxu.crm.domain.ProductDir;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.util.PageResult;

import java.util.List;

public interface IProductService {
    int deleteByPrimaryKey(Long id);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    PageResult query(QueryObject qo);

    void saveOrUpdate(Product product);

    void insert(Product product);

    /**
     * 通过商品id查询对应的商品信息
     * @param id 商品id
     * @return  商品信息
     */
    Object selectRowByProductId(Long id);


    int selectTotalProduct();

    double selectSalePriceAvg();
}
