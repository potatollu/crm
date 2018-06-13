package com.liqinxu.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CashierDetail {

    private Long id;

    private Member member;      //会员对象

    private Product product;    //商品对象

    private boolean state;      //挂单状态

    /**
     * 中间件信息
     */
    private CashierDetailProduct cashierDetailProduct;

}