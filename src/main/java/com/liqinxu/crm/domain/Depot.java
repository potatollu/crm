package com.liqinxu.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Depot {
    private Long id;

    private String name;//仓库名称

    private String sn;//仓库编号

    private String phone;//联系电话

    private Employee manager;//负责人

    private String adress;//地址

}