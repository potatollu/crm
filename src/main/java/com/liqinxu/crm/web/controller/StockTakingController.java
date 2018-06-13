package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.StockTaking;
import com.liqinxu.crm.qo.StockTakingQuery;
import com.liqinxu.crm.service.IProductDirService;
import com.liqinxu.crm.service.IStockTakingService;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("stockTaking")
public class StockTakingController {

    @Autowired
    private IStockTakingService stockTakingService;
    @Autowired
    private IProductDirService productDirService;

    @RequiresPermissions(value = {"stockTaking:view", "库存盘点"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view() {
        return "/stockTaking/view";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(StockTakingQuery qo) {
        PageResult result = stockTakingService.query(qo);
        return result;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return stockTakingService.selectAll();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            stockTakingService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    /**
     * 修改或保存仓库数据
     * @param stockTaking
     * @return
     */
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(StockTaking stockTaking) {
        JSONResult jsonResult = new JSONResult();
        try {
            stockTakingService.saveOrUpdate(stockTaking);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

    /**
     * 查询所有商品类别
     * @return
     */
    @RequestMapping("selectAllDirName")
    @ResponseBody
    public Object selectAllDirName() {
        return productDirService.selectAll();
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
        List<StockTaking> stockTakings = stockTakingService.selectAll();
        for (int i = 0; i < stockTakings.size(); i++) {
            StockTaking stockTaking = stockTakings.get(i);
            //创建行
            row = sheet.createRow(i + 1);
            //设置单元格内容
            row.createCell(0).setCellValue(stockTaking.getProductSn());
            row.createCell(1).setCellValue(stockTaking.getProductName());
            row.createCell(2).setCellValue(stockTaking.getDirName());
            row.createCell(3).setCellValue(stockTaking.getEmployeeName());
        }
        //写入数据
        workbook.write(response.getOutputStream());
    }
}
