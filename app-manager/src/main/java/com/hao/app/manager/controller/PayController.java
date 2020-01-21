package com.hao.app.manager.controller;

import com.hao.app.commons.entity.param.PayQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.PayDO;
import com.hao.app.pojo.PayDetailDO;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.service.PayService;
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

@Controller
public class PayController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(PayController.class);

    @Resource
    private PayService payService;

    @Resource
    private ProjectsService projectsService;

    @RequestMapping("/initPay.do")
    public String initPay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("projectsList", getProjectsList(request));
        return "jsp/pay/initPay";
    }


    @RequestMapping("/initPayDetail.do")
    public String initPayDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int payId = NumberUtils.toInt(request.getParameter("payId"), 0);
        if (payId <= 0) {
            return failResult(request, "请先选择工资单");
        }

        PayDO itemObj = payService.getById(payId);
        request.setAttribute("itemObj", itemObj);
        return "jsp/pay/initPayDetail";
    }

    @RequestMapping("/searchPayDetail.do")
    public void searchPayDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int payId = NumberUtils.toInt(request.getParameter("payId"), 0);
        JsonResult<PayDetailDO> result = payService.searchPayDetail(payId);
        writeResponse(response, result);
    }


    @RequestMapping("/searchPay.do")
    public void searchPay(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PayQueryParam param = genParam(request);
        JsonResult<PayDO> result = payService.searchPay(param);
        writeResponse(response, result);
    }

    private PayQueryParam genParam(HttpServletRequest request) {
        int projectsId = NumberUtils.toInt(request.getParameter("projectsId"), 0);
        int status = NumberUtils.toInt(request.getParameter("status"), 0);
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        int start = NumberUtils.toInt(request.getParameter("start"));
        int limit = NumberUtils.toInt(request.getParameter("limit"), 100);


        PayQueryParam param = new PayQueryParam(start, limit);
        if (projectsId > 0) {
            param.setProjectsId(projectsId);
        }
        if (status >= 0) {
            param.setStatus(status);
        }

        if (StringUtils.isNotBlank(startDate)) {
            param.setStartDate(startDate + "-01");
        }
        if (StringUtils.isNotBlank(endDate)) {
            param.setEndDate(endDate + "-01");
        }
        return param;
    }


    @RequestMapping("/initPayEdit.do")
    public String initPayEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));
        PayDO itemObj = payService.getById(id);
        request.setAttribute("itemObj", itemObj);
        request.setAttribute("projectsList", getProjectsList(request));
        return "jsp/pay/initPayEdit";
    }


    @RequestMapping("/initPayDetailEdit.do")
    public String initPayDetailEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));
//        PayDO itemObj = payService.getById(id);
//        request.setAttribute("itemObj", itemObj);
//        request.setAttribute("projectsList", getProjectsList(request));
        return "jsp/pay/initPayDetailEdit";
    }


    @RequestMapping("/savePay.do")
    public String savePay(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int id = NumberUtils.toInt(request.getParameter("hideId"), 0);
        int projectId = NumberUtils.toInt(request.getParameter("projects"), 0);
        if (projectId <= 0) {
            return failResult(request, "请选择所属项目");
        }
        ProjectsDO projectsDO = projectsService.getById(projectId);
        if (projectsDO == null) {
            return failResult(request, "请选择所属项目");
        }

        PayDO item = new PayDO();
        item.setId(id);
        item.setRemark(request.getParameter("remark"));
        item.setProjects(projectsDO.getId());
        item.setProjectsName(projectsDO.getName());

        String payMonth = request.getParameter("payMonth");
        if (StringUtils.isNotBlank(payMonth)) {
            item.setPayMonth(new SimpleDateFormat("yyyy-MM-dd").parse(payMonth + "-01")); //
        }

        ResultCodeEnum resultCode;
        if (id == 0) {
            item.setCreater(getCurrentUserName(request));
            item.setCreateTime(new Date());
            resultCode = payService.insert(item);
        } else {
            item.setUpdateTime(new Date());
            resultCode = payService.update(item);
        }

        if (resultCode.equals(ResultCodeEnum.SUCCESS)) {
            sysLogsService.writeLog(item.getCreater(), "新增或修改工资单:" + item.toString());
            return successResult(request, "工资单管理", "initPay.do");
        } else {
            return failResult(request, resultCode);
        }
    }

    @RequestMapping("/savePayDetail.do")
    public String savePayDetail(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        return null;
    }


}
