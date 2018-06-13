package com.liqinxu.crm.util;


import com.liqinxu.crm.domain.Employee;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Set;

public class UserContext {

    private static final String EXPRESSIONS_IN_SESSION = "EXPRESSIONS_IN_SESSION";
    private static final String EMPLOYEE_IN_SESSION = "EMPLOYEE_IN_SESSION";

    private UserContext() { }
    public static HttpSession getSession() {
        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        return attributes.getRequest().getSession();
    }

    //登录用户信息的共享和注销
    public static void setCurrentUser(Employee currentUser) {
        if (currentUser == null) {
            getSession().invalidate();
        } else {
            getSession().setAttribute(EMPLOYEE_IN_SESSION, currentUser);
        }
    }

    public static Employee getCurrentUser() {
        return (Employee) getSession().getAttribute(EMPLOYEE_IN_SESSION);
    }

    public static void setExpressions(Set<String> expressions) {
        getSession().setAttribute(EXPRESSIONS_IN_SESSION, expressions);
    }

    public static Set<String> getExpressions() {
        return (Set<String>) getSession().getAttribute(EXPRESSIONS_IN_SESSION);
    }
}
