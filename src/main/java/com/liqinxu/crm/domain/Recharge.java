package com.liqinxu.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter@Setter
public class Recharge {
    private Long id;

    private Member member;     //会员对象

    private Integer operationType;      //操作类型

    private PayMode paymode;        //支付类型

    private Integer payBeforeNum;       //充值前金额

    private Integer payAfterNum;        //充值后金额

    private BigDecimal fund;      //充值金额

    private Integer sendNumber = 0;     //赠送金额

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //前台往后台传
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validTime;     //充值时间

    private String remark;      //备注

}