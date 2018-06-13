package com.liqinxu.crm.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TakeGiftItem {
    private Long id;

    private String number;

    private String clientName;

    private String name;

    private String unit;

    private int takeNum;

    private Long needIntegral;
}