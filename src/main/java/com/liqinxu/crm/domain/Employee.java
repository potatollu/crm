package com.liqinxu.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Employee {
    private Long id;

    private String username;

    private String realname;

    private String password;

    private String tel;

    private String email;

    private Department dept;

    //后台往前台传值得时候,日期使用的注解
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    //前台往后台传
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;

    private Boolean state;

    private boolean admin;

    //roles[0].id
    private List<Role> roles = new ArrayList<>();

}