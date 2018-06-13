package com.liqinxu.crm.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liqinxu.crm.util.JSONResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
public class LoginController {

    @RequestMapping("login")
    public String login() {

        return "main";
    }


    @RequestMapping("testJson")
    @ResponseBody
    public Object testJson(String msg) throws IOException {
        JSONResult jsonResult = new JSONResult();
        String text1 = java.net.URLEncoder.encode(msg, "utf-8");
        String path = "http://api.qingyunke.com/api.php?key=free&appid=0&msg=" + text1;
        System.out.println(path);
        //1, 得到URL对象
        URL url = new URL(path);

        //2, 打开连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        //3, 设置提交类型
        //conn.setRequestMethod("POST");

        //4, 设置允许写出数据,默认是不允许 false
        conn.setDoOutput(true);
        conn.setDoInput(true);//当前的连接可以从服务器读取内容, 默认是true

        /*//5, 获取向服务器写出数据的流
        OutputStream os = conn.getOutputStream();
        //参数是键值队  , 不以"?"开始
        os.write(msg.getBytes());
        //os.write("googleTokenKey=&username=admin&password=5df5c29ae86331e1b5b526ad90d767e4".getBytes());
        os.flush();*/
        //6, 获取响应的数据
        //得到服务器写回的响应数据
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
        String str = br.readLine();
        System.out.println("响应内容为:  " + str);
        JSONObject jsonObject = JSON.parseObject(str);
        String result = jsonObject.getString("result");
        String content = jsonObject.getString("content");
        System.out.println(result);
        System.out.println(content);
        if (!result.equals("0")) {
            jsonResult.mark("网络错误了哦！不能继续帮助你啦");
        } else {
            jsonResult.setMsg(content);
        }
        return jsonResult;
    }
}
