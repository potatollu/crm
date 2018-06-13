package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.Product;
import com.liqinxu.crm.qo.QueryObject;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product entity);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product entity);


    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);

    Object selectRowByProductId(Long id);

    void deleteRelation(Long id);

    int selectTotalProduct();

    double selectSalePriceAvg();
}