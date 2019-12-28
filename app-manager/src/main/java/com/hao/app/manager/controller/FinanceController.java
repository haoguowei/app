package com.hao.app.manager.controller;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.pojo.FinanceDO;
import com.hao.app.service.FinanceService;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;


@Controller
public class FinanceController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(FinanceController.class);

    @Resource
    private FinanceService financeService;


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

        return "jsp/finance/initFinanceEdit";
    }

    @RequestMapping("/saveFinance.do")
    public String saveFinance(HttpServletRequest request, HttpServletResponse response) throws IOException {

        return successResult(request, "区域管理", "initArea.do");
    }
}
