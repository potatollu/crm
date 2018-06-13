package com.liqinxu.crm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 给Controller类做增强
 */
@ControllerAdvice
public class HandlerExceptionUtil {

    /**
     * 针对出现哪些异常做增强处理
     */
    @ExceptionHandler(UnauthorizedException.class)
    public void handlerException(HttpServletResponse response, HandlerMethod method) {
        //判断是否是Ajax请求，贴有RequestBody这个注解就是,Ajax方式返回字符串，不是则跳转到页面
        RequestBody requestBody = method.getMethodAnnotation(RequestBody.class);
        try {
            if (requestBody != null) {
                response.getWriter().write(new ObjectMapper().writeValueAsString(new JSONResult().mark("没有权限进行操作")));
            } else {
                response.sendRedirect("/nopermission.jsp");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
