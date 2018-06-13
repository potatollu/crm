package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.domain.Product;
import com.liqinxu.crm.domain.ProductDir;
import com.liqinxu.crm.qo.ProductQueryObject;
import com.liqinxu.crm.service.IProductService;
import com.liqinxu.crm.util.JSONResult;
import com.liqinxu.crm.util.PageResult;
import com.liqinxu.crm.util.UploadUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private ServletContext servletContext;

    /*@RequiresPermissions(value = {"product:view", "商品查看"}, logical = Logical.OR)*/
    @RequestMapping("view")
    public String view() {
        return "/product/view";
    }

    @RequestMapping("viewList")
    public String viewList() {
        return "/product/viewList";
    }

    @RequestMapping("list")
    @ResponseBody
    public Object list(ProductQueryObject qo) {
        PageResult result = productService.query(qo);
        return result;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return productService.selectAll();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JSONResult jsonResult = new JSONResult();
        try {
            productService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("删除失败");
        }
        return jsonResult;
    }

    /*@RequiresPermissions(value = {"product:saveOrUpdate", "添加或更新商品"}, logical = Logical.OR)*/
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Product product, MultipartFile pic) {
        JSONResult jsonResult = new JSONResult();
        try {
            //删除图片:用户传递了图片,当前商品已经有图片
            if (pic != null && !StringUtils.isEmpty(product.getImagePath())) {
                UploadUtil.deleteFile(servletContext, product.getImagePath());
            }
            if (pic != null) {
                //处理上传
                String realPath = servletContext.getRealPath("/static/upload");
                String uploadPath = UploadUtil.upload(pic, realPath);
                product.setImagePath(uploadPath);
            }
            productService.saveOrUpdate(product);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("保存失败");
        }
        return jsonResult;
    }

    @RequestMapping("selectRowByProductId")
    @ResponseBody
    public Object selectRowByProductId(Long id) {
        Object p = productService.selectRowByProductId(id);
        return p;
    }

    @RequestMapping("importXls")
    public void importXls(MultipartFile file, HttpServletResponse response) throws IOException {
        InputStream inputStream = file.getInputStream();
        //创建excel文件,指定输入流
        HSSFWorkbook wb = new HSSFWorkbook(inputStream);
        HSSFSheet sheet = wb.getSheetAt(0);
        //获取最后一行索引
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            Product product = new Product();
            Row row = sheet.getRow(i);
            product.setName(row.getCell(0).getStringCellValue());
            product.setSn(row.getCell(1).getStringCellValue());
            product.setSalePrice(new BigDecimal(row.getCell(2).getStringCellValue()));
            product.setCredits(new BigDecimal(row.getCell(3).getStringCellValue()));
            productService.insert(product);
        }
    }

    @RequestMapping("exportXls")
    @ResponseBody
    public void exportXls(HttpServletResponse response) throws Exception {
        //设置文件下载响应头
        response.setHeader("Content-Disposition", "attachment;filename=product.xls");
        //创建excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("day01");
        //设置标题
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("商品名称");
        row.createCell(1).setCellValue("商品编号");
        row.createCell(2).setCellValue("销售单价");
        row.createCell(3).setCellValue("商品积分");

        List<Product> productList = productService.selectAll();
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(product.getName());
            row.createCell(1).setCellValue(product.getSn());
            row.createCell(2).setCellValue(product.getSalePrice().toString());
            row.createCell(3).setCellValue(product.getCredits().toString());

        }
        //写入数据
        wb.write(response.getOutputStream());
    }


    //查询共有多少种商品
    @RequestMapping("totalProduct")
    @ResponseBody
    public int totalProduct() {
        return productService.selectTotalProduct();
    }

    //查询商品均价
    @RequestMapping("salePriceAvg")
    @ResponseBody
    public double salePriceAvg() {
        return productService.selectSalePriceAvg();
    }


}
