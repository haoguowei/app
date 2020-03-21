package com.hao.app.manager.controller;

import com.hao.app.commons.entity.param.IncomeQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.IncomeDO;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.service.IncomeService;
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
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class IncomeController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(IncomeController.class);

    @Resource
    private IncomeService incomeService;

    @Resource
    private ProjectsService projectsService;

    @RequestMapping("/initIncome.do")
    public String initIncome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("projectsList", getProjectsList(request));
        return "jsp/income/initIncome";
    }


    @RequestMapping("/searchIncome.do")
    public void searchIncome(HttpServletRequest request, HttpServletResponse response) throws IOException {
        IncomeQueryParam param = genParam(request);
        JsonResult<IncomeDO> result = incomeService.search(param);
        writeResponse(response, result);
    }

    private IncomeQueryParam genParam(HttpServletRequest request) {
        int projectsId = NumberUtils.toInt(request.getParameter("projectsId"), 0);

        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");

        int start = NumberUtils.toInt(request.getParameter("start"));
        int limit = NumberUtils.toInt(request.getParameter("limit"), 100);

        IncomeQueryParam param = new IncomeQueryParam(start, limit);
        if (projectsId > 0) {
            param.setProjectsId(projectsId);
        }

        if (StringUtils.isNotBlank(dateStart)) {
            param.setDateStart(dateStart);
        }
        if (StringUtils.isNotBlank(dateEnd)) {
            param.setDateEnd(dateEnd);
        }
        return param;
    }

    @RequestMapping("/initIncomeEdit.do")
    public String initIncomeEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));
        IncomeDO itemObj = incomeService.getById(id);
        request.setAttribute("itemObj", itemObj);

        request.setAttribute("projectsList", getProjectsList(request));
        return "jsp/income/initIncomeEdit";
    }

    @RequestMapping("/saveIncome.do")
    public String saveIncome(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int id = NumberUtils.toInt(request.getParameter("hideId"), 0);
        int projectId = NumberUtils.toInt(request.getParameter("projects"), 0);
        if (projectId <= 0) {
            return failResult(request, "请选择所属项目");
        }

        ProjectsDO projectsDO = projectsService.getById(projectId);
        if (projectsDO == null) {
            return failResult(request, "请选择所属项目");
        }

        IncomeDO item = new IncomeDO();
        item.setId(id);
        item.setRemark(request.getParameter("remark"));
        item.setJiafang(request.getParameter("jiafang"));
        item.setJiafangInfo(request.getParameter("jiafangInfo"));
        item.setProjects(projectsDO.getId());
        item.setProjectsName(projectsDO.getName());


        String payAmount = request.getParameter("amount");
        item.setAmount(StringUtils.isBlank(payAmount) ? BigDecimal.valueOf(0) : new BigDecimal(payAmount));

        String incomeDay = request.getParameter("incomeDay");
        if (StringUtils.isNotBlank(incomeDay)) {
            item.setIncomeDay(new SimpleDateFormat("yyyy-MM-dd").parse(incomeDay + "-01"));
        }

        ResultCodeEnum resultCode;
        if (id == 0) {
            item.setCreater(getCurrentUserName(request));
            item.setCreateTime(new Date());
            resultCode = incomeService.insert(item);
        } else {
            item.setUpdateTime(new Date());
            resultCode = incomeService.update(item);
        }

        if (resultCode.equals(ResultCodeEnum.SUCCESS)) {
            sysLogsService.writeLog(item.getCreater(), "新增或修改收入:" + item.toString());
            return successResult(request, "收入管理", "initIncome.do");
        } else {
            return failResult(request, resultCode);
        }
    }

    private BigDecimal getBigDecimal(BigDecimal v) {
        if (v == null) {
            return BigDecimal.valueOf(0);
        }
        return v;
    }
}
