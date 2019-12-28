package com.hao.app.manager.controller;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.FinanceDO;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.service.FinanceService;
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
import java.time.LocalDate;
import java.util.Date;


@Controller
public class FinanceController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(FinanceController.class);

    @Resource
    private FinanceService financeService;

    @Resource
    private ProjectsService projectsService;


    @RequestMapping("/initFinance.do")
    public String initFinance(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("projectsList", getProjectsList(request));
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();

        request.setAttribute("startDay", year + "-01");
        request.setAttribute("endDay", year + "-" + (month > 9 ? month : ("0" + month)));
        return "jsp/finance/initFinance";
    }


    @RequestMapping("/searchFinance.do")
    public void searchFinance(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer projectsId = NumberUtils.toInt(request.getParameter("projectsId"));
        if (projectsId == null || projectsId <= 0) {
            projectsId = null;
        }
        String fromDay = request.getParameter("fromDay");
        String endDay = request.getParameter("endDay");
        JsonResult<FinanceDO> result = financeService.searchFinance(projectsId, fromDay, endDay);
        writeResponse(response, result);
    }

    @RequestMapping("/initFinanceEdit.do")
    public String initFinanceEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));
        FinanceDO itemObj = financeService.getById(id);
        request.setAttribute("itemObj", itemObj);

        request.setAttribute("projectsList", getProjectsList(request));

        return "jsp/finance/initFinanceEdit";
    }

    @RequestMapping("/saveFinance.do")
    public String saveFinance(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int id = NumberUtils.toInt(request.getParameter("hideId"), 0);
        int projectsId = NumberUtils.toInt(request.getParameter("projects"), 0);

        int projectId = NumberUtils.toInt(request.getParameter("projects"), 0);
        if (projectId <= 0) {
            return failResult(request, "请选择所属项目");
        }
        ProjectsDO projectsDO = projectsService.getById(projectId);
        if (projectsDO == null) {
            return failResult(request, "请选择所属项目");
        }

        FinanceDO item = new FinanceDO();
        item.setId(id);
        item.setRemark(request.getParameter("remark"));
        item.setProjects(projectsId);
        item.setProjectsName(projectsDO.getName());

        String upDay = request.getParameter("upDay") + "-01";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotBlank(upDay)) {
            item.setUpDay(format.parse(upDay));
        }

        String incomeAmount = request.getParameter("incomeAmount");
        if (StringUtils.isNotBlank(incomeAmount)) {
            item.setIncomeAmount(new BigDecimal(incomeAmount));
        } else {
            item.setIncomeAmount(BigDecimal.valueOf(0));
        }

        String payoutAmount = request.getParameter("payoutAmount");
        if (StringUtils.isNotBlank(payoutAmount)) {
            item.setPayoutAmount(new BigDecimal(payoutAmount));
        } else {
            item.setPayoutAmount(BigDecimal.valueOf(0));
        }

        item.setProfit(item.getIncomeAmount().subtract(item.getPayoutAmount()));

        ResultCodeEnum resultCode;
        if (id == 0) {
            item.setCreater(getCurrentUserName(request));
            item.setCreateTime(new Date());
            resultCode = financeService.insert(item);
        } else {
            item.setUpdateTime(new Date());
            resultCode = financeService.update(item);
        }

        if (resultCode.equals(ResultCodeEnum.SUCCESS)) {
            sysLogsService.writeLog(item.getCreater(), "新增或修改收支:" + item.toString());
            return successResult(request, "收支清单", "initFinance.do");
        } else {
            return failResult(request, resultCode);
        }
    }
}
