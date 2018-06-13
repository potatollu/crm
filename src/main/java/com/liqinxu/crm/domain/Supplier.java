package com.liqinxu.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter@Setter
public class Supplier {
    private Long id;

    private String name; //供应商

    private String username; //联系人

    private String tel;  //联系电话
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputTime; //录入时间

    private Employee inputUser; //录入人

    private String address;//供应商地址

    private String mark;//备注

}