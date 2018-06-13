package com.liqinxu.crm.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {
    private int total;//查询总条数
    private List<?> rows = new ArrayList<>();//页面上的数据
}
