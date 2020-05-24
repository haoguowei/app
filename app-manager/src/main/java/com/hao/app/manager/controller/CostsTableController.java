package com.hao.app.manager.controller;

import com.hao.app.commons.entity.param.TableQueryParam;
import com.hao.app.commons.entity.result.TableKey;
import com.hao.app.commons.utils.DateUtil;
import com.hao.app.manager.export.ExportCostTable;
import com.hao.app.manager.export.ExportCostTableMonth;
import com.hao.app.pojo.CostsTypeDO;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.service.CostsService;
import com.hao.app.service.IncomeService;
import com.hao.app.service.ProjectsService;
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

    @Resource
    private ExportCostTable exportCostTable;

    @Resource
    private ExportCostTableMonth exportCostTableMonth;


    @RequestMapping("/exportCostTable.do")
    public void exportCostTable(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String modelFile = getModelFilePath(request, "cost.xls");
        exportCostTable.exportExcel(modelFile, request, response);
    }

    @RequestMapping("/exportCostTableMonth.do")
    public void exportCostTableMonth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String modelFile = getModelFilePath(request, "costMonth.xls");
        exportCostTableMonth.exportExcel(modelFile, request, response);
    }

    @RequestMapping("/initCostsZhuanxiang.do")
    public String initCostsZhuanxiang(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TableQueryParam param = genParam(request);

        //TableKey: projectId表示projectId, type3表示months
        Map<TableKey, BigDecimal> costTable = costsService.getCostTableZhuanxiang(param);
        request.setAttribute("costTable", costTable);

        Set<Integer> allMonth = getZhuanxiangMonths(costTable);
        allMonth.add(0); //合计
        request.setAttribute("allMonth", allMonth);

        List<ProjectsDO> projectsList = projectsService.search(null).getResultList();
        ProjectsDO projectsDO = new ProjectsDO();
        projectsDO.setName("合计");
        projectsDO.setId(0);
        projectsList.add(projectsDO);

        request.setAttribute("projectsList", projectsList);
        request.setAttribute("fromDate", param.getEnterDateStart());
        request.setAttribute("toDate", param.getEnterDateEnd());
        request.setAttribute("type1", param.getType1());
        request.setAttribute("type2", param.getType2());
        request.setAttribute("type3", param.getType3());


        request.setAttribute("type1List", costsService.listCostsTypeByParentId(null));
        if (param.getType1() != null && param.getType1() > 0) {
            request.setAttribute("type2List", costsService.listCostsTypeByParentId(param.getType1()));
        }
        if (param.getType2() != null && param.getType2() > 0) {
            request.setAttribute("type3List", costsService.listCostsTypeByParentId(param.getType2()));
        }

        return "jsp/costtable/initCostsZhuanxiang";
    }

    private Set<Integer> getZhuanxiangMonths(Map<TableKey, BigDecimal> costTable) {
        List<Integer> allMonth = new ArrayList<>();
        for (TableKey key : costTable.keySet()) {
            allMonth.add(key.getType3());
        }

        Collections.sort(allMonth);

        Set<Integer> set = new LinkedHashSet<>();
        for (Integer i : allMonth) {
            set.add(i);
        }
        return set;
    }

    @RequestMapping("/initCostsTableMonth.do")
    public String initCostsTableMonth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TableQueryParam param = genParam(request);
        Map<TableKey, BigDecimal> incomeTable = incomeService.getIncomeTable(param);
        Map<TableKey, BigDecimal> costTable = costsService.getCostTable(param);
        request.setAttribute("incomeTable", incomeTable);
        request.setAttribute("costTable", costTable);

        List<ProjectsDO> projectsList = projectsService.search(null).getResultList();
        ProjectsDO projectsDO = new ProjectsDO();
        projectsDO.setName("合计");
        projectsDO.setId(0);
        projectsList.add(projectsDO);

        request.setAttribute("projectsList", projectsList);

        List<CostsTypeDO> allTypeList = costsService.getTableTypes();
        request.setAttribute("allTypeList", allTypeList);

        request.setAttribute("fromDate", param.getEnterDateStart());
        request.setAttribute("toDate", param.getEnterDateEnd());

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
        request.setAttribute("fromDate", param.getEnterDateStart());
        request.setAttribute("toDate", param.getEnterDateEnd());

        request.setAttribute("projectsList", getProjectsList(request));
        request.setAttribute("projectsId", param.getProjectsId());

        return "jsp/costtable/initCostsTable";
    }


    private TableQueryParam genParam(HttpServletRequest request) {
        int first = NumberUtils.toInt(request.getParameter("first"), 0);
        int projectsId = NumberUtils.toInt(request.getParameter("projectsId"), 0);
        int type1 = NumberUtils.toInt(request.getParameter("type1"), 0);
        int type2 = NumberUtils.toInt(request.getParameter("type2"), 0);
        int type3 = NumberUtils.toInt(request.getParameter("type3"), 0);
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");

        if (first == 0) {
            int year = DateUtil.getYear();
            fromDate = year + "-01-01";
            toDate = year + "-12-31";
        }

        TableQueryParam param = new TableQueryParam();
        if (projectsId > 0) {
            param.setProjectsId(projectsId);
        }
        if (type1 > 0) {
            param.setType1(type1);
        }
        if (type2 > 0) {
            param.setType2(type2);
        }
        if (type3 > 0) {
            param.setType3(type3);
        }

        param.setEnterDateStart(fromDate);
        param.setEnterDateEnd(toDate);
        return param;
    }

}
