package com.liqinxu.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ConsumptionDetail {
    private Long id;

    private Member member;

    private Product product;/*
    product.id
        product.name	尼康D2000
        product.salePrice	12000
        product.sn	5
    */

    private BigDecimal payMoney;//amount	12000

    private int productCount;

    private Date dealDate;

    /*

        client.balance	12314
        client.id	1
        client.name	小明

        number	1

         */

}