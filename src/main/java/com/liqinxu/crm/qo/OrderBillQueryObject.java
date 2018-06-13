package com.liqinxu.crm.qo;

import com.liqinxu.crm.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class OrderBillQueryObject extends QueryObject {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private int status = -1;

    public Date getEndDate() {
        if (endDate == null) {
            return null;
        }
        return DateUtil.getEndDate(endDate);
    }
    private long supplierId = -1L;
    private Long depotId = -1L;
    private String sn;
}
