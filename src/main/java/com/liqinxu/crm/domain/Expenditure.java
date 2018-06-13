package com.liqinxu.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Expenditure {
    private Long id;

    private Member member;

    private Long payCount;

    private Long payDetail_id;


}