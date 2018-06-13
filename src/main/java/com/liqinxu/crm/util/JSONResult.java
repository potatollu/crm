package com.liqinxu.crm.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JSONResult {
    private boolean success = true;
    private String msg;

    /**
     *  操作失败调用该方法
     * @param msg   传入错误到信息
     * @return  返回自身对象
     */
    public JSONResult mark(String msg) {
        success = false;
        this.msg = msg;
        return this;
    }
}
