package com.hao.app.manager.controller;

import com.hao.app.commons.entity.param.TableQueryParam;
import com.hao.app.commons.utils.DateUtil;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.service.CostsService;
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
import java.util.Arrays;
import java.util.List;

@Controller
public class ChartsController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ChartsController.class);

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
        request.setAttribute("projectName", projectsDO == null ? "所有项目" : projectsDO.getName());

        //TODO

        return "jsp/charts/initCostCharts";
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
