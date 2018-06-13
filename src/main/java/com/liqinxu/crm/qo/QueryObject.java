package com.liqinxu.crm.qo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryObject {
    private int page;//当前页
    private int rows;//每页显示的条数

    public int getStart() {
        return (page - 1) * rows;
    }
}
