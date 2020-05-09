package com.hao.app.manager.controller;

import com.hao.app.commons.entity.param.TableQueryParam;
import com.hao.app.commons.entity.result.AmountTable;
import com.hao.app.commons.entity.result.CostsTableMonth;
import com.hao.app.service.CostsService;
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
import java.util.List;


@Controller
public class CostsTableController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(CostsTableController.class);

    @Resource
    private CostsService costsService;


    @Resource
    private IncomeService incomeService;


    @Resource
    private ProjectsService projectsService;

    @RequestMapping("/initCostsTable.do")
    public String initCostsTable(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TableQueryParam param = genParam(request);
        List<AmountTable> costsTableList = costsService.searchCostsTable(param);

        request.setAttribute("costsTableList", costsTableList);

        return "jsp/costtable/initCostsTable";
    }

    @RequestMapping("/initCostsTableMonth.do")
    public String initCostsTableMonth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TableQueryParam param = genParam(request);
        List<CostsTableMonth> costsTableList = costsService.searchCostsTableMonth(param);

        request.setAttribute("costsTableList", costsTableList);
        return "jsp/costtable/initCostsTableMonth";
    }


    private TableQueryParam genParam(HttpServletRequest request) {
        int projectsId = NumberUtils.toInt(request.getParameter("projectsId"), 0);
        String enterDateStart = request.getParameter("enterDateStart");
        String enterDateEnd = request.getParameter("enterDateEnd");

        TableQueryParam param = new TableQueryParam();
        if (projectsId > 0) {
            param.setProjectsId(projectsId);
        }

        if (StringUtils.isNotBlank(enterDateStart)) {
            param.setEnterDateStart(enterDateStart);
        }

        if (StringUtils.isNotBlank(enterDateEnd)) {
            param.setEnterDateEnd(enterDateEnd);
        }

        return param;
    }

}
