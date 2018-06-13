package com.liqinxu.crm.qo;

import com.liqinxu.crm.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class MenberPayNumberQueryObject extends QueryObject {
    private String keyword;

    public String getKeyword() {
        return "".equals(keyword) ? null : keyword;
    }
}
