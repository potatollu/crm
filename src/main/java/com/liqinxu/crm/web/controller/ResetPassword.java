package com.liqinxu.crm.web.controller;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.liqinxu.crm.domain.Employee;
import com.liqinxu.crm.service.IEmployeeService;
import com.liqinxu.crm.util.JSONResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class ResetPassword {

    private int code = 0;

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("resetPassword")
    public String resetPassword() {
        return "resetPassword";
    }

    @RequestMapping("checkPassword")
    @ResponseBody
    public Object checkPassword(String password) {
        JSONResult jsonResult = new JSONResult();
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        if (password != null) {
            Md5Hash md5Hash = new Md5Hash(password, employee.getUsername(), 2);
            if (!md5Hash.toString().equals(employee.getPassword())) {
                jsonResult.mark("原密码错误！请检查后输入...");
            }
        }
        return jsonResult;
    }

    @RequestMapping("getCode")
    public void getCode() {
        this.code = (int) ((Math.random() * 9 + 1) * 100000);
    }

    @RequestMapping("checkSN")
    @ResponseBody
    public Object checkSN(String password, String newPassword) {
        System.out.println(code);
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        JSONResult jsonResult = new JSONResult();
        if (!password.equals(code + "")) {
            jsonResult.mark("验证码错误！请重新获取");
        } else {
            employeeService.resetPassword(employee.getId(), newPassword, employee.getUsername());
        }
        return jsonResult;
    }

    @RequestMapping("SendMsg")
    public void SendMsg(String number) throws HTTPException, IOException {
        System.out.println(number);
        //生成验证码
        getCode();
        System.out.println(code);
        //假设短信模板 id 为 123，模板内容为：测试短信，{1}，{2}，{3}，上学。
        SmsSingleSender sender = new SmsSingleSender(1400079298, "acf158bed7bb1f2a543e5b8d71b23bc0");
        ArrayList<String> params = new ArrayList<String>();
        params.add(code + "");
        params.add("30");
        SmsSingleSenderResult result = sender.sendWithParam("86", number, 102004, params, "", "", "");
        System.out.println(result);
    }

}
