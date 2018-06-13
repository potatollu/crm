package com.liqinxu.crm.service.impl;

import com.liqinxu.crm.domain.Product;
import com.liqinxu.crm.mapper.ProductDirMapper;
import com.liqinxu.crm.mapper.ProductMapper;
import com.liqinxu.crm.qo.QueryObject;
import com.liqinxu.crm.service.IProductService;
import com.liqinxu.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductDirMapper productDirMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        //productMapper.deleteRelation(id);
        return productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Product product) {
        productMapper.insert(product);
    }

    @Override
    public Product selectByPrimaryKey(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> selectAll() {
        return productMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(Product product) {
        if (product.getId() != null) {
            //productDirMapper.updateByPrimaryKey(product.getProductDir());
            productMapper.updateByPrimaryKey(product);
        } else {
            productMapper.insert(product);
        }
    }

    @Override
    public Object selectRowByProductId(Long id) {
        return productMapper.selectRowByProductId(id);
    }

    /**
     * 查询商品种数
     * @return 商品种数
     */
    @Override
    public int selectTotalProduct() {
        return productMapper.selectTotalProduct();
    }

    /**
     * 查询商品销售均价
     * @return 商品均价
     */
    @Override
    public double selectSalePriceAvg() {
        return productMapper.selectSalePriceAvg();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int count = productMapper.queryForCount(qo);
        List<?> list = productMapper.queryForList(qo);
        if (count == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        return new PageResult(count, list);
    }
}
