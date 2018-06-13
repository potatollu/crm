package com.liqinxu.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class MemberPayNumber {
    private Long id;

    /**
     * 会员
     */
    private Member member;

    /**
     * 服务名称
     */
    private Serve serve;


    /**
     * 充值次数
     */
    private Integer payNumber;

    /**
     * 有效时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //前台往后台传
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date validTime;

    /**
     * 支付时间
     */
    //后台往前台传值得时候,日期使用的注解
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //前台往后台传
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 充值金额
     */
    private BigDecimal fund;

    /**
     * 支付方式
     */
    private PayMode paymode;

    /**
     * 充值前次数
     */
    private Integer payBeforeNum;

    /**
     * 充值后次数
     */
    private Integer payAfterNum;


}