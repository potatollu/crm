package com.liqinxu.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Gift {
    private Long id;

    private Long sn;

    private String name;

    //单位
    private String unit;

    //积分
    private Long integral;

    private Long number;

}