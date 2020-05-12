package com.hao.app.manager.controller;

import com.hao.app.commons.entity.param.TableQueryParam;
import com.hao.app.commons.entity.result.TableKey;
import com.hao.app.commons.utils.DateUtil;
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
public class ChartsController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ChartsController.class);

    @Resource
    private IncomeService incomeService;

    @Resource
    private CostsService costsService;

    @Resource
    private ProjectsService projectsService;


    @RequestMapping("/initCostCharts.do")
    public String initCostCharts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TableQueryParam param = genParam(request);

        //时间选择
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

        //项目信息
        ProjectsDO projectsDO = projectsService.getById(param.getProjectsId());
        String projectName = projectsDO == null ? "所有" : projectsDO.getName();

        Map<TableKey, BigDecimal> incomeTable = incomeService.getIncomeTable2(param);
        Map<TableKey, BigDecimal> costTable = costsService.getCostTable2(param);

        Set<Integer> allMonth = getAllMonths(incomeTable, costTable);

        //饼状图合计
        loadPieChart(request, projectName, incomeTable, costTable);

//        //饼状图明细
//        loadPieChartDetail(request, projectName, incomeTable, costTable);
//
//        //月份收支柱状图
//        loadColumnChart(request, projectName, incomeTable, costTable, allMonth);

        return "jsp/charts/initCostCharts";
    }


    private void loadPieChart(HttpServletRequest request, String projectName, Map<TableKey, BigDecimal> incomeTable, Map<TableKey, BigDecimal> costTable) {
        String title = projectName + "收支合计";

        //总收入
        BigDecimal income = BigDecimal.valueOf(0);
        for (TableKey key : incomeTable.keySet()) {
            income = income.add(incomeTable.get(key));
        }


        //总支出
        BigDecimal costs = BigDecimal.valueOf(0);
        for (TableKey key : costTable.keySet()) {
            costs = costs.add(costTable.get(key));
        }

        //合计
        BigDecimal total = income.add(costs);


        StringBuffer sbr = new StringBuffer();
        if (total.doubleValue() != 0D) {
            BigDecimal y = income.multiply(BigDecimal.valueOf(100.0)).divide(total, 2, BigDecimal.ROUND_HALF_UP);

            sbr.append("[");
            sbr.append("{ y: " + fmtBigDecimal(y) + ", pay: " + fmtBigDecimal(income) + ", label: '收入合计' }").append(",");
            sbr.append("{ y: " + fmtBigDecimal(BigDecimal.valueOf(100).subtract(y)) + ", pay: " + fmtBigDecimal(costs) + ", label: '费用合计' }");
            sbr.append("]");
        }

        request.setAttribute("title1", title);
        request.setAttribute("data1", sbr.toString());
    }

    private String fmtBigDecimal(BigDecimal v) {
        if (v == null) {
            return "0";
        }
        return v.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
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

    private TableQueryParam genParam(HttpServletRequest request) {
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


        param.setFromYear(fromYear);
        param.setFromMonth(fromMonth);
        param.setToYear(toYear);
        param.setToMonth(toMonth);

        param.setEnterDateStart(fromYear + "-" + fromMonth + "-01");
        param.setEnterDateEnd(toYear + "-" + toMonth + "-01");


        //默认or用户选择
        int projectsId = 0;
        if (NumberUtils.toInt(request.getParameter("default"), 0) == 0) {
            projectsId = getCurrentProjectsId(request);
            projectsId = projectsId > 0 ? projectsId : 0;
        } else {
            projectsId = NumberUtils.toInt(request.getParameter("projectsId"), 0);
        }
        param.setProjectsId(projectsId);

        return param;
    }

}
