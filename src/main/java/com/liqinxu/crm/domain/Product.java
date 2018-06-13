package com.liqinxu.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter@Getter
public class Product {
    private Long id; //商品id

    private String  name;  //商品名称

    private String sn;  //商品编号

    private BigDecimal salePrice;  //商品售价

    private BigDecimal costPrice;  //进货价

    private BigDecimal vipPrice;  //vip售价

    private BigDecimal credits;  //商品积分

    private String intro;  //商品备注

    private String imagePath;  //商品图片

    private ProductDir productDir;  //与商品分类的关联关系

}