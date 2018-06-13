package com.liqinxu.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
/**
 * 字典明细表
 */
public class DicitionaryItem {
    private Long id;

    private Dicitionary parent;   //字典id

    private String name;      //名称

    private String intro;      //简介

}