package com.hao.app.manager.controller;

import com.hao.app.commons.entity.param.EmployeeQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.pojo.EmployeeDO;
import com.hao.app.service.EmployeeService;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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
        String name = request.getParameter("name");

        int start = NumberUtils.toInt(request.getParameter("start"));
        int limit = NumberUtils.toInt(request.getParameter("limit"), 100);

        EmployeeQueryParam param = new EmployeeQueryParam(start, limit);
        param.setName(name);

        JsonResult<EmployeeDO> result = employeeService.searchEmployee(param);
        writeResponse(response, result);
    }

    @RequestMapping("/initEmployeeEdit.do")
    public String initEmployeeEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "jsp/employee/initEmployeeEdit";
    }

    @RequestMapping("/saveEmployee.do")
    public String saveEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return successResult(request, "区域管理", "initArea.do");
    }
}
