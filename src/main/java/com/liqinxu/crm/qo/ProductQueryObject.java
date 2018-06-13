package com.liqinxu.crm.qo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductQueryObject extends QueryObject {
    private String keyword;
    //private String firstId;
    private Long secondId;
}
