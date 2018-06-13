package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.Employee;
import com.liqinxu.crm.qo.EmployeeQueryObject;
import com.liqinxu.crm.service.IEmployeeService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @RequiresPermissions(value = {"employee:view", "员工列表"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view() {
        return "/employee/view";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(EmployeeQueryObject qo) {
        PageResult result = employeeService.query(qo);
        return result;
    }
    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return employeeService.selectAll();
    }
    @RequiresPermissions(value = {"employee:saveOrUpdate", "添加或更新员工"}, logical = Logical.OR)
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Employee employee) {
        JSONResult jsonResult = new JSONResult();
        try {
            employeeService.saveOrUpdate(employee);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"employee:changeState", "离入职操作"}, logical = Logical.OR)
    @RequestMapping("changeState")
    @ResponseBody
    public Object changeState(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            employeeService.changeState(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("操作失败");
        }
        return jsonResult;
    }

    @RequiresPermissions(value = {"employee:resetPassword", "重置密码"}, logical = Logical.OR)
    @RequestMapping("resetPassword")
    @ResponseBody
    public Object resetPassword(Long id, String newPassword, String username) {
        JSONResult jsonResult = new JSONResult();
        try {
            employeeService.resetPassword(id, newPassword, username);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("操作失败");
        }
        return jsonResult;
    }

    @RequestMapping("selectRowsByEmpId")
    @ResponseBody
    public Object selectByEmpId(Long empId) {
        List<Long> rolesId = employeeService.selectRowsByEmpId(empId);
        return rolesId;
    }

    @RequestMapping("exportXls")
    public void exportXls(HttpServletResponse response) throws IOException {
        //设置文件下载响应头
        response.setHeader("Content-Disposition", "attachment;filename=employee.xls");
        //创建Excel文件
        Workbook workbook = new HSSFWorkbook();
        //创建工作簿
        Sheet sheet = workbook.createSheet("day01");
        //设置标题
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("用户名");
        row.createCell(1).setCellValue("真实姓名");
        row.createCell(2).setCellValue("邮箱");
        row.createCell(3).setCellValue("电话");
        List<Employee> employeeList = employeeService.selectAll();
        for (int i = 0; i < employeeList.size(); i++) {
            Employee employee = employeeList.get(i);
            //创建行
            row = sheet.createRow(i + 1);
            //设置单元格内容
            row.createCell(0).setCellValue(employee.getUsername());
            row.createCell(1).setCellValue(employee.getRealname());
            row.createCell(2).setCellValue(employee.getEmail());
            row.createCell(3).setCellValue(employee.getTel());
        }
        //写入数据
        workbook.write(response.getOutputStream());
    }

    /**
     * 数据导入数据库
     * @param response
     * @param file
     * @throws Exception
     */
    @RequestMapping("importXls")
    public void  importXls(MultipartFile file, HttpServletResponse response) throws Exception{
        InputStream inputStream = file.getInputStream();
        HSSFWorkbook wb = new HSSFWorkbook(inputStream);
        Sheet sheet = wb.getSheetAt(0);
        int num = sheet.getLastRowNum();
        for  (int i = 1;i <= num;i ++){
            Employee employee = new Employee();
            Row row = sheet.getRow(i);
            employee.setUsername(row.getCell(0).getStringCellValue());
            employee.setRealname(row.getCell(1).getStringCellValue());
            employee.setEmail(row.getCell(2).getStringCellValue());
            employee.setTel(row.getCell(3).getStringCellValue());
            employeeService.insert(employee);
        }
    }
}
