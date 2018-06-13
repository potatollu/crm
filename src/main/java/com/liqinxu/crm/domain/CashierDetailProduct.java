package com.liqinxu.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 收银明细关联的商品具体信息
 */
@Getter
@Setter
public class CashierDetailProduct {
    private Long id;

    private Long product_id;   //商品id

    private Integer productNum;   //商品数量

    private BigDecimal productMoneySum;   //商品总价格

}