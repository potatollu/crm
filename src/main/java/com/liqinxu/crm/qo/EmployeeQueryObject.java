package com.liqinxu.crm.qo;

import com.liqinxu.crm.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class EmployeeQueryObject extends QueryObject {
    private String keyword;
    private Long deptId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public String getKeyword() {
        return "".equals(keyword) ? null : keyword;
    }

    public Date getEndDate() {
        if (endDate != null) {
            return DateUtil.getEndDate(endDate);
        }
        return null;
    }
}
