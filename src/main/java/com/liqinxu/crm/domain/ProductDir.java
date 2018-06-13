package com.liqinxu.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ProductDir {
    private Long id;  //商品分类id

    private String dirName;  //商品分类名

    private ProductDir parent;  //与父商品分类的关联关系

}