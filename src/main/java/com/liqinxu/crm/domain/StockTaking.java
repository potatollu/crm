package com.liqinxu.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter@Setter
public class StockTaking {

    private Long id;

    //商品编号
    private String productSn;

    //商品名称
    private String productName;

    //商品类别
    private String dirName;

    //供应商
    private String supplierName;

    //库存数量
    private BigDecimal totalNumber;

    //仓库
    private String depotName;

    //盘点时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date takeTime;

    //盘点人
    private String employeeName;

}