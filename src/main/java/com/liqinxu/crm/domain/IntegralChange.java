package com.liqinxu.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter@Setter
public class IntegralChange {

    private Long id;


    private String memberNumber;

    private String memberName;

    //会员等级
    private String grade;

    //变动类型
    private String changeType;

    //变动数额
    private String amount;

    //变动时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date chaneTime;

    //备注
    private String remark;

}