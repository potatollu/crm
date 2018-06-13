package com.liqinxu.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@RequestMapping("SQLBakOrRecover")
public class SQLBakOrRecover {

    @RequestMapping("sqlBak")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String dir = request.getServletContext().getRealPath("/WEB-INF/down");
        System.out.println("文件真实路径：----------------->"+dir);
        //执行备份操作
        backup("managersystem", dir);
        //Thread.sleep(8000);
        response.setContentType("application/x-msdownload");
        String fileName = "sqlBak.sql";
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.contains("IE")) {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        } else {
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
        }
        OutputStream out = response.getOutputStream();
//        Files.copy(Paths.get(dir, fileName), response.getOutputStream());
        Files.copy(Paths.get(dir, fileName), response.getOutputStream());
    }


    // 备份数据库
    public static void backup(String dbName, String filePath) {
        try {
            @SuppressWarnings("unused")
            Process process = Runtime.getRuntime().exec(
                    "cmd  /c  mysqldump -uroot -pXu199515=+ " + dbName + " > "
                            + filePath + "/" + "sqlBak"
                            + ".sql");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("备份数据库");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    // 恢复数据库
    public static void load(String dbName, String filePath) {
        try {
            @SuppressWarnings("unused")
            Process process = Runtime.getRuntime().exec(
                    "cmd  /c  mysql -uroot -padmin " + dbName + " < " + filePath);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("恢复数据库");
            e.printStackTrace();
        }
    }

}
