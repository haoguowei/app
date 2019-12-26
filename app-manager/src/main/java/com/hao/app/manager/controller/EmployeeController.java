package com.hao.app.manager.controller;

import com.hao.app.commons.entity.param.EmployeeQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        String name = request.getParameter("name");
        String idCard = request.getParameter("idCard");
        String entryDateStart = request.getParameter("entryDateStart");
        String entryDateEnd = request.getParameter("entryDateEnd");
        String leaveDateStart = request.getParameter("leaveDateStart");
        String leaveDateEnd = request.getParameter("leaveDateEnd");

        Integer projectsId = getCurrentProjects(request);

        int start = NumberUtils.toInt(request.getParameter("start"));
        int limit = NumberUtils.toInt(request.getParameter("limit"), 100);


        EmployeeQueryParam param = new EmployeeQueryParam(start, limit);
        param.setProjectsId(projectsId);
        param.setName(name);
        param.setIdCard(idCard);
        param.setEntryDateStart(entryDateStart);
        param.setEntryDateEnd(entryDateEnd);
        param.setLeaveDateStart(leaveDateStart);
        param.setLeaveDateEnd(leaveDateEnd);

        JsonResult<EmployeeDO> result = employeeService.searchEmployee(param);
        writeResponse(response, result);
    }

    @RequestMapping("/initEmployeeEdit.do")
    public String initEmployeeEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));
        EmployeeDO itemObj = employeeService.getById(id);
        request.setAttribute("itemObj", itemObj);
        return "jsp/employee/initEmployeeEdit";
    }

    @RequestMapping("/saveEmployee.do")
    public String saveEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int id = NumberUtils.toInt(request.getParameter("hideId"), 0);

        EmployeeDO item = new EmployeeDO();
        item.setId(id);
        item.setName(request.getParameter("name"));
        item.setGender(NumberUtils.toInt(request.getParameter("gender"), 0)); //gender
        item.setPhone(request.getParameter("phone")); //phone
        item.setIdCard(request.getParameter("idCard")); //idCard

        item.setProjects(NumberUtils.toInt(request.getParameter("projects"), 0)); //projects
        item.setJobType(NumberUtils.toInt(request.getParameter("jobType"), 0)); //jobType
        item.setEthnic(NumberUtils.toInt(request.getParameter("ethnic"), 0)); //ethnic

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String birthDate = request.getParameter("birthDate");
        String entryDate = request.getParameter("entryDate");
        String leaveDate = request.getParameter("leaveDate");
        item.setBirthDate(format.parse(birthDate)); //birthDateDiv
        item.setEntryDate(format.parse(entryDate)); //entryDateDiv
        item.setLeaveDate(format.parse(leaveDate)); //leaveDateDiv

        item.setEduType(NumberUtils.toInt(request.getParameter("eduType"), 0)); //eduType
        item.setHukouType(NumberUtils.toInt(request.getParameter("hukouType"), 0)); //hukouType
        item.setHujiAddress(request.getParameter("hujiAddress")); //hujiAddress
        item.setAddress(request.getParameter("address")); //address

        item.setEmergencyContact(request.getParameter("emergencyContact")); //emergencyContact
        item.setEmergencyContactPhone(request.getParameter("emergencyContactPhone")); //emergencyContactPhone

        item.setSafeType(request.getParameter("safeType")); //safeType

        item.setRemark(request.getParameter("remark"));

        ResultCodeEnum resultCode;
        if (id == 0) {
            item.setCreater(getCurrentUserName(request));
            item.setCreateTime(new Date());
            resultCode = employeeService.insert(item);
        } else {
            item.setUpdateTime(new Date());
            resultCode = employeeService.update(item);
        }

        if (resultCode.equals(ResultCodeEnum.SUCCESS)) {
            sysLogsService.writeLog(item.getCreater(), "新增或修改员工:" + item.toString());
            return successResult(request, "员工管理", "initEmployee.do");
        } else {
            return failResult(request, resultCode);
        }
    }
}
