package com.liqinxu.crm.mapper;

import com.liqinxu.crm.domain.ProductDir;
import com.liqinxu.crm.qo.ProductDirQueryObject;
import com.liqinxu.crm.qo.QueryObject;

import java.util.List;

public interface ProductDirMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductDir entity);

    ProductDir selectByPrimaryKey(Long id);

    List<ProductDir> selectAll();

    int updateByPrimaryKey(ProductDir entity);

    int queryForCount(QueryObject qo);

    List<?> queryForList(QueryObject qo);

    List<ProductDir> selectProductDir();

    List<ProductDir> selectChildren(Long parentId);


    List<ProductDir> selectByParentId(ProductDirQueryObject qo);

    String selectDivNameByPid(Long productId);

}