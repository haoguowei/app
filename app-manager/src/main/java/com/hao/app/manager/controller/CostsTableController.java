package com.hao.app.manager.controller;

import com.hao.app.commons.entity.param.TableQueryParam;
import com.hao.app.commons.entity.result.TableKey;
import com.hao.app.commons.utils.DateUtil;
import com.hao.app.pojo.CostsTypeDO;
import com.hao.app.pojo.ProjectsDO;
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
import java.math.BigDecimal;
import java.util.*;


@Controller
public class CostsTableController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(CostsTableController.class);

    @Resource
    private CostsService costsService;


    @Resource
    private IncomeService incomeService;


    @Resource
    private ProjectsService projectsService;

    @RequestMapping("/initCostsTableMonth.do")
    public String initCostsTableMonth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TableQueryParam param = genParam(request);
        Map<TableKey, BigDecimal> incomeTable = incomeService.getIncomeTable(param);
        Map<TableKey, BigDecimal> costTable = costsService.getCostTable(param);
        request.setAttribute("incomeTable", incomeTable);
        request.setAttribute("costTable", costTable);

        List<ProjectsDO> projectsList = projectsService.search(null).getResultList();
        ProjectsDO projectsDO = new ProjectsDO();
        projectsDO.setName("占合同比");
        projectsDO.setId(0);
        projectsList.add(projectsDO);

        request.setAttribute("projectsList", projectsList);

        List<CostsTypeDO> allTypeList = costsService.getTableTypes();
        request.setAttribute("allTypeList", allTypeList);


        List<String> yearList = Arrays.asList("2019", "2020", "2021", "2022", "2023", "2024", "2025");
        List<String> monthList = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        request.setAttribute("yearList", yearList);
        request.setAttribute("monthList", monthList);

        request.setAttribute("fromYear", param.getFromYear());
        request.setAttribute("fromMonth", param.getFromMonth());
        request.setAttribute("toYear", param.getToYear());
        request.setAttribute("toMonth", param.getToMonth());

        return "jsp/costtable/initCostsTableMonth";
    }

    private Set<Integer> getAllMonths(Map<TableKey, BigDecimal> incomeTable, Map<TableKey, BigDecimal> costTable) {
        List<Integer> allMonth = new ArrayList<>();
        for (TableKey key : incomeTable.keySet()) {
            allMonth.add(key.getProjectId());
        }
        for (TableKey key : costTable.keySet()) {
            allMonth.add(key.getProjectId());
        }

        Collections.sort(allMonth);

        Set<Integer> set = new LinkedHashSet<>();
        for (Integer i : allMonth) {
            set.add(i);
        }
        return set;
    }

    @RequestMapping("/initCostsTable.do")
    public String initCostsTable(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TableQueryParam param = genParam(request);

        Map<TableKey, BigDecimal> incomeTable = incomeService.getIncomeTable2(param);
        Map<TableKey, BigDecimal> costTable = costsService.getCostTable2(param);
        request.setAttribute("incomeTable", incomeTable);
        request.setAttribute("costTable", costTable);


        Set<Integer> allMonth = getAllMonths(incomeTable, costTable);
        allMonth.add(0); //合计
        request.setAttribute("allMonth", allMonth);

        List<CostsTypeDO> allTypeList = costsService.getTableTypes();
        request.setAttribute("allTypeList", allTypeList);


        /////////////
        List<String> yearList = Arrays.asList("2019", "2020", "2021", "2022", "2023", "2024", "2025");
        List<String> monthList = Arrays.asList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
        request.setAttribute("yearList", yearList);
        request.setAttribute("monthList", monthList);

        request.setAttribute("fromYear", param.getFromYear());
        request.setAttribute("fromMonth", param.getFromMonth());
        request.setAttribute("toYear", param.getToYear());
        request.setAttribute("toMonth", param.getToMonth());

        request.setAttribute("projectsList", getProjectsList(request));
        request.setAttribute("projectsId", param.getProjectsId());

        return "jsp/costtable/initCostsTable";
    }


    private TableQueryParam genParam(HttpServletRequest request) {
        int projectsId = NumberUtils.toInt(request.getParameter("projectsId"), 0);
        String fromYear = request.getParameter("fromYear");
        String fromMonth = request.getParameter("fromMonth");
        String toYear = request.getParameter("toYear");
        String toMonth = request.getParameter("toMonth");

        String year = String.valueOf(DateUtil.getYear());

        fromYear = StringUtils.isBlank(fromYear) ? year : fromYear;
        fromMonth = StringUtils.isBlank(fromMonth) ? "01" : fromMonth;
        toYear = StringUtils.isBlank(toYear) ? year : toYear;
        toMonth = StringUtils.isBlank(toMonth) ? "12" : toMonth;

        TableQueryParam param = new TableQueryParam();
        if (projectsId > 0) {
            param.setProjectsId(projectsId);
        }

        param.setFromYear(fromYear);
        param.setFromMonth(fromMonth);
        param.setToYear(toYear);
        param.setToMonth(toMonth);

        param.setEnterDateStart(fromYear + "-" + fromMonth + "-01");
        param.setEnterDateEnd(toYear + "-" + toMonth + "-01");

        return param;
    }

}
