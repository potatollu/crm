package com.liqinxu.crm.web.controller;


import com.liqinxu.crm.domain.CashierDetail;
import com.liqinxu.crm.domain.CashierDetailProduct;
import com.liqinxu.crm.mapper.*;
import com.liqinxu.crm.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
@RequestMapping("cashier")
public class CashierController {

    @Autowired
    private CashierDetailMapper cashierDetailMapper;
    @Autowired
    private CashierDetailProductMapper cashierDetailProductMapper;
    @Autowired
    private MemberMapper memberMapper;

    @RequestMapping("view")
    public String view() {
        return "/cashier/view";
    }

    @RequestMapping("save")
    @ResponseBody
    public Object save(BigDecimal amount, CashierDetailProduct cashierDetailProduct, CashierDetail cashierDetail) {
        JSONResult jsonResult = new JSONResult();
        jsonResult.setMsg("付款成功");
        try {
            cashierDetailProduct.setProduct_id(Long.valueOf(cashierDetail.getProduct().getSn()));
            cashierDetailProduct.setProduct_id(Long.valueOf(cashierDetail.getProduct().getSn()));
            cashierDetailProductMapper.insert(cashierDetailProduct);
            cashierDetail.setCashierDetailProduct(cashierDetailProduct);
            cashierDetailMapper.insert(cashierDetail);
            memberMapper.updateBlance(cashierDetail.getMember().getId(), cashierDetailProduct.getProductMoneySum().multiply(BigDecimal.valueOf(cashierDetailProduct.getProductNum())));
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.mark("付款成功");
        }

        return jsonResult;
    }

    @RequestMapping("listAll")
    @ResponseBody
    public Object listAllcashier() {
        return cashierDetailMapper.selectAll();
    }
}
