package com.liqinxu.crm.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 会员等级
 */
@Getter@Setter
public class Grade {
    private Long id;

    private String name;    //等级名称

    private Double discount;    //等级折扣

}