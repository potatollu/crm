package com.liqinxu.crm.web.controller;

import com.liqinxu.crm.service.IOutComeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("outComeType")
public class OutComeTypeController {

    @Autowired
    private IOutComeTypeService outComeTypeService;


    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return outComeTypeService.selectAll();
    }

}
