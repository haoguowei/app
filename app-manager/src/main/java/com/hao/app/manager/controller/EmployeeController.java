package com.hao.app.manager.controller;

import com.hao.app.commons.entity.Dicts;
import com.hao.app.commons.entity.param.EmployeeQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.entity.result.JsonResultAjax;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.commons.utils.IdCardUtils;
import com.hao.app.pojo.EmployeeDO;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.service.EmployeeService;
import com.hao.app.service.ProjectsService;
import org.apache.commons.lang.StringUtils;
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
import java.util.Map;


@Controller
public class EmployeeController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Resource
    private EmployeeService employeeService;

    @Resource
    private ProjectsService projectsService;


    @RequestMapping("/initEmpShenqing.do")
    public String initEmpShenqing(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("projectsList", getProjectsList(request));
        return "jsp/employee/initEmpShenqing";
    }

    @RequestMapping("/initEmpShengHe.do")
    public String initEmpShengHe(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("projectsList", getProjectsList(request));
        return "jsp/employee/initEmpShengHe";
    }


    @RequestMapping("/initEmpLizhi.do")
    public String initEmpLizhi(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("projectsList", getProjectsList(request));
        return "jsp/employee/initEmployeeLizhi";
    }


    @RequestMapping("/initEmployee.do")
    public String initEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("projectsList", getProjectsList(request));
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

        int projectsId = NumberUtils.toInt(request.getParameter("projectsId"));
        int status = NumberUtils.toInt(request.getParameter("status")); //0-未入职；1-正式员工；2-离职

        int start = NumberUtils.toInt(request.getParameter("start"));
        int limit = NumberUtils.toInt(request.getParameter("limit"), 100);

        EmployeeQueryParam param = new EmployeeQueryParam(start, limit);
        if (projectsId > 0) {
            param.setProjectsId(projectsId);
        }

        if (projectsId >= 0) {
            param.setStatus(status);
        }


        if (StringUtils.isNotBlank(name)) {
            param.setName(name);
        }
        if (StringUtils.isNotBlank(idCard)) {
            param.setIdCard(idCard);
        }

        if (StringUtils.isNotBlank(entryDateStart)) {
            param.setEntryDateStart(entryDateStart);
        }
        if (StringUtils.isNotBlank(entryDateEnd)) {
            param.setEntryDateEnd(entryDateEnd);
        }

        if (StringUtils.isNotBlank(leaveDateStart)) {
            param.setLeaveDateStart(leaveDateStart);
        }
        if (StringUtils.isNotBlank(leaveDateEnd)) {
            param.setLeaveDateEnd(leaveDateEnd);
        }

        JsonResult<EmployeeDO> result = employeeService.searchEmployee(param);
        writeResponse(response, result);
    }

    @RequestMapping("/initEmployeeEdit.do")
    public String initEmployeeEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));
        EmployeeDO itemObj = employeeService.getById(id);
        request.setAttribute("itemObj", itemObj);

        request.setAttribute("projectsList", getProjectsList(request));
        request.setAttribute("jobTypeMap", Dicts.employeeJobTypeMap);
        request.setAttribute("minzuMap", Dicts.minzuMap);
        request.setAttribute("xueliMap", Dicts.xueliMap);
        request.setAttribute("hukouTypeMap", Dicts.hukouTypeMap);

        return "jsp/employee/initEmployeeEdit";
    }

    @RequestMapping("/saveEmployee.do")
    public String saveEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int id = NumberUtils.toInt(request.getParameter("hideId"), 0);

        int projectId = NumberUtils.toInt(request.getParameter("projects"), 0);
        if (projectId <= 0) {
            return failResult(request, "请选择所属项目");
        }
        ProjectsDO projectsDO = projectsService.getById(projectId);
        if (projectsDO == null) {
            return failResult(request, "请选择所属项目");
        }

        String name = request.getParameter("name");
        if (StringUtils.isBlank(name)) {
            return failResult(request, "姓名为必填项");
        }

        String idCard = request.getParameter("idCard");
        if (StringUtils.isBlank(idCard)) {
            return failResult(request, "身份证号为必填项");
        }

        EmployeeDO item = new EmployeeDO();
        item.setId(id);
        item.setName(name);
        item.setPhone(request.getParameter("phone")); //phone
        item.setIdCard(idCard); //idCard

        item.setProjects(projectId); //projects
        item.setProjectsName(projectsDO.getName());

        item.setHetong(NumberUtils.toInt(request.getParameter("hetong"), 0)); //jobType
        item.setJobType(NumberUtils.toInt(request.getParameter("jobType"), 0)); //jobType
        item.setEthnic(NumberUtils.toInt(request.getParameter("ethnic"), 0)); //ethnic

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String entryDate = request.getParameter("entryDate");
        String leaveDate = request.getParameter("leaveDate");

        if (StringUtils.isNotBlank(entryDate)) {
            item.setEntryDate(format.parse(entryDate)); //entryDateDiv
        }
        if (StringUtils.isNotBlank(leaveDate)) {
            item.setLeaveDate(format.parse(leaveDate)); //leaveDateDiv
        }

        item.setEduType(NumberUtils.toInt(request.getParameter("eduType"), 0)); //eduType
        item.setHukouType(NumberUtils.toInt(request.getParameter("hukouType"), 0)); //hukouType
        item.setHujiAddress(request.getParameter("hujiAddress")); //hujiAddress
        item.setAddress(request.getParameter("address")); //address

        item.setEmergencyContact(request.getParameter("emergencyContact")); //emergencyContact
        item.setEmergencyContactPhone(request.getParameter("emergencyContactPhone")); //emergencyContactPhone

        item.setSafeType(request.getParameter("safeType")); //safeType

        item.setRemark(request.getParameter("remark"));

        Map<String, String> map = IdCardUtils.getBirAgeSex(item.getIdCard());
        if (map != null) {
            if (StringUtils.isNotBlank(map.get("birthday"))) {
                item.setBirthDay(format.parse(map.get("birthday")));
            }
        }

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
            return successResult(request, "员工管理", "initEmployee.do");
        } else {
            return failResult(request, resultCode);
        }
    }

    @RequestMapping("/applyF.do")
    public void applyF(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));
        String shenqing = request.getParameter("shenqing");
        boolean result = employeeService.applyF(id, shenqing);
        writeResponse(response, new JsonResultAjax(result));
    }

    @RequestMapping("/passEmpF.do")
    public void passEmpF(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));
        String descr = request.getParameter("descr");
        boolean result = employeeService.passd(id, descr);
        writeResponse(response, new JsonResultAjax(result));
    }


    @RequestMapping("/notPassEmpF.do")
    public void notPassEmpF(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));
        String descr = request.getParameter("descr");

        boolean result = employeeService.noPassed(id, descr);
        writeResponse(response, new JsonResultAjax(result));
    }

    @RequestMapping("/leaveF.do")
    public void leaveF(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));
        String leaveDate = request.getParameter("leaveDate");

        boolean result = employeeService.leave(id, leaveDate);
        writeResponse(response, new JsonResultAjax(result));
    }


}
