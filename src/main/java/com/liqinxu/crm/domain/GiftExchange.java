package com.liqinxu.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
//礼遇兑换记录
@Getter
@Setter
public class GiftExchange {
    private Long id;
    //会员卡号,name
    private Member member;
    //礼物名称
    private Gift gift;
    //单位(bao)
    private String unit;
    //数量
    private Long number;
    //积分
    private Long integral;
    //操作员
    private Employee employee;
    //兑换日期
    //后台往前台传值得时候,日期使用的注解
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //前台往后台传
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date integraldate;
}