package com.liqinxu.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Serve {
    private Long id;

    private String name;

    private String sn;

    private BigDecimal price;

}