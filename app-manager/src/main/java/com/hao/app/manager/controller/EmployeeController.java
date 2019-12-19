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
public class EmployeeController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Resource
    private EmployeeService employeeService;


    @RequestMapping("/initEmployee.do")
    public String initEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "jsp/employee/initEmployee";
    }


    @RequestMapping("/searchEmployee.do")
    public void searchEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
       
    }

    @RequestMapping("/initEmployeeEdit.do")
    public String initEmployeeEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
       
        return "jsp/employee/initEmployeeEdit";
    }

    @RequestMapping("/saveEmployee.do")
    public String saveEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
    }
}
