package com.liqinxu.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Setter@Getter
public class OutComeBillItem {
    private Long id;

    private OutComeType outType; //支出分类

    private String outMoney; //支出金额

    //后台往前台传值得时候,日期使用的注解
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //前台往后台传
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date outputTime; //支出时间

    private Employee outputUser;  //支出人员

    private String remark; //备注
}