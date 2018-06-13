package com.liqinxu.crm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liqinxu.crm.domain.Employee;
import com.liqinxu.crm.domain.SystemLog;
import com.liqinxu.crm.mapper.SystemLogMapper;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class SystemLogUtil {

    @Autowired
    private SystemLogMapper systemLogMapper;

    public void writelog(JoinPoint joinPoint) {
        SystemLog systemLog = new SystemLog();
        //操作时间
        systemLog.setOpTime(new Date());
        //操作IP request
        systemLog.setOpIP(RequestUtil.getRequest().getRemoteAddr());
        //操作用户
        Object employee = SecurityUtils.getSubject().getPrincipal();
        systemLog.setEmployee((Employee) employee);
        //执行的参数
        Object[] args = joinPoint.getArgs();
        try {
            systemLog.setParams(new ObjectMapper().writeValueAsString(args));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        //执行的全限定名+方法
        String function = joinPoint.getTarget().getClass().getName() + ":" + joinPoint.getSignature().getName();
        systemLog.setFunction(function);
        systemLogMapper.insert(systemLog);
    }
}
