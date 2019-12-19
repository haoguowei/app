package com.hao.app.manager.controller;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@Controller
public class FinanceController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(FinanceController.class);

    @Resource
    private FinanceService financeService;


    @RequestMapping("/initFinance.do")
    public String initFinance(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "jsp/finance/initFinance";
    }


    @RequestMapping("/searchFinance.do")
    public void searchFinance(HttpServletRequest request, HttpServletResponse response) throws IOException {
       
    }

    @RequestMapping("/initFinanceEdit.do")
    public String initFinanceEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
       
        return "jsp/finance/initFinanceEdit";
    }

    @RequestMapping("/saveFinance.do")
    public String saveFinance(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
    }
}
