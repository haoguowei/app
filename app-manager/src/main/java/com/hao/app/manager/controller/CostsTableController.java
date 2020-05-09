package com.hao.app.manager.controller;

import com.hao.app.commons.entity.param.TableQueryParam;
import com.hao.app.commons.entity.result.TableKey;
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
import java.util.List;
import java.util.Map;


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
    public String initCostsTable(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TableQueryParam param = genParam(request);
        Map<TableKey, BigDecimal> incomeTable = incomeService.getIncomeTable(param);
        Map<TableKey, BigDecimal> costTable = costsService.getCostTable(param);
        request.setAttribute("incomeTable", incomeTable);
        request.setAttribute("costTable", costTable);

        List<ProjectsDO> projectsList = projectsService.search(null).getResultList();
        request.setAttribute("projectsList", projectsList);

        List<CostsTypeDO> allTypeList = costsService.getTableTypes();
        request.setAttribute("allTypeList", allTypeList);

        return "jsp/costtable/initCostsTableMonth";
    }

    @RequestMapping("/initCostsTable.do")
    public String initCostsTableMonth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TableQueryParam param = genParam(request);
        return "jsp/costtable/initCostsTable";
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
            param.setEnterDateStart(enterDateStart + "-01");
        }

        if (StringUtils.isNotBlank(enterDateEnd)) {
            param.setEnterDateEnd(enterDateEnd + "-31");
        }

        return param;
    }

}
