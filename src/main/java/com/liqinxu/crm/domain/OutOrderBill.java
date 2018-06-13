package com.liqinxu.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jinyanfeng on 2018/3/30.
 */
@Getter
@Setter
public class OutOrderBill {

    public static final int STAUTS_NORMAL = 0;
    public static final int STAUTS_AUDIT = 1;

    private Long id;

    private String sn; //订单编号

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date vdate; //业务时间

    private int status=STAUTS_NORMAL ;//审核状态

    private BigDecimal totalAmount; //总金额

    private BigDecimal totalNumber;//总数量

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date auditTime; //审核时间

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputTime; //录入时间

    private Employee inputUser; //录入人

    private Employee auditor; //审核人

    private Supplier supplier; //供应商
    //一对多的关系
    private List<OutOrderBillItem> items = new ArrayList<>();

    private String remark;

    private Depot depot;
}
