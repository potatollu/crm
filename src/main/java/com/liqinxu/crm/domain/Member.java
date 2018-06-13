package com.liqinxu.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员
 */
@Getter@Setter
public class Member {
    private Long id;

    private String number;    //会员卡号

    private String name;    //名称

    private String password;    //密码

    private String tel;     //手机号码

    private String sex;     //性别

    private Long dicitionary_id;    //数据字典

    private Grouping group;    //分组对象

    private Grade grade;    //等级对象

    private String qq;      //QQ

    private String wechat;    //微信

    private String email;      //邮箱
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //前台往后台传
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;      //生日

    private int integral;       //积分

    private BigDecimal balance;     //存储余额

    private String site;        //客户地址

    private String totalCost; //消费总额


}