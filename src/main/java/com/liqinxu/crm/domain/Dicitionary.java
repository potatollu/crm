package com.liqinxu.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Dicitionary {
    private Long id;

    private String sn;      //字典编号

    private String name;     //名称

    private String intro;      //简介
}