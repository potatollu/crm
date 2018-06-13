package com.liqinxu.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class OrderBillItem {

    public static final int STAUTS_NORMAL = 0;
    public static final int STAUTS_AUDIT = 1;
    private Long id;
    private String sn;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date vdate;//业务发生的时间

    //单据的状态:
    private int status = STAUTS_NORMAL;

    //总金额
    private BigDecimal totalAmount;

    //总数量
    private BigDecimal totalNumber;

    //审核时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date auditTime;
    //录入时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputTime;

    //录入人
    private Employee inputUser;

    //审核人
    private Employee auditor;

    //成本价
    private BigDecimal costPrice;
    private BigDecimal number;
    private BigDecimal amount;
    private String remark;
    private Product product;
    //关联订单的编号
    private Long billId;
}