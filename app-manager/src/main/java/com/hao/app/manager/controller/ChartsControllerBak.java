package com.hao.app.manager.controller;

import com.hao.app.commons.utils.DateUtil;
import com.hao.app.manager.dto.Chart;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ChartsControllerBak extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ChartsControllerBak.class);

    @Resource
    private CostsService costsService;

    @Resource
    private ProjectsService projectsService;


    @RequestMapping("/initKanban.do")
    public String initArea(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("projectsList", getProjectsList(request));
        SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");
        String fromDate = request.getParameter("fromDate");
        String endDate = request.getParameter("endDate");
        if (StringUtils.isBlank(fromDate)) {
            fromDate = DATE.format(DateUtil.addMonth(new Date(), -6));
        }
        if (StringUtils.isBlank(endDate)) {
            endDate = DATE.format(new Date());
        }
        request.setAttribute("fromDate", fromDate);
        request.setAttribute("endDate", endDate);

        int abc = NumberUtils.toInt(request.getParameter("abc"), 0);
        Integer projectId = 0;
        if (abc == 0) {
            projectId = getCurrentProjectsId(request);
            projectId = projectId > 0 ? projectId : 0;
        } else {
            projectId = NumberUtils.toInt(request.getParameter("projects"), 0);
        }

        request.setAttribute("projectId", projectId);
        ProjectsDO projectsDO = projectsService.getById(projectId);

        //饼状图
        printPieChart(request, projectsDO, fromDate, endDate);

        //柱状图
        printColumnChart(request, projectsDO, fromDate, endDate);

        return "jsp/charts/initKanban";
    }

    private void printColumnChart(HttpServletRequest request, ProjectsDO projectsDO, String fromDate, String endDate) {
        BigDecimal total = BigDecimal.valueOf(0);
        Integer projectsId = projectsDO == null ? null : projectsDO.getId();

        //获取所有项目
        List<Chart> list = new ArrayList<>();
        List<ProjectsDO> projectList = null;
        if (projectsDO == null) {
            projectList = projectsService.search(null).getResultList();
            projectList = projectList == null ? new ArrayList<>() : projectList;
        } else {
            projectList = new ArrayList<>();
            projectList.add(projectsDO);
        }

        //生成标题
        String title = projectsDO != null ? projectsDO.getName() : "所有";
        title += "项目开支" + total + "元";
//        request.setAttribute("title2", title);
        request.setAttribute("title2", "项目开支情况");

//      [{
//            // x:0, //横轴顺序
//            label: '阜平',
//                    y: 100
//        }, {
//            label: '行唐',
//                    y: 200
//        }, {
//            label: '保定',
//                    y: 300
//        }]

        StringBuffer sbr = new StringBuffer("[");
        for (int i = 0; i < list.size(); i++) {
            Chart c = list.get(i);
            if (i != 0) {
                sbr.append(",");
            }

            sbr.append("{label: '" + c.getLabel() + "', y: " + c.getY() + "}");
        }
        sbr.append("]");
        request.setAttribute("datas2", sbr.toString());

    }

    private BigDecimal fmtBigDecimal(BigDecimal v) {
        if (v == null) {
            return BigDecimal.valueOf(0);
        }
        return v;
    }

    //饼状图
    private void printPieChart(HttpServletRequest request, ProjectsDO projectsDO, String fromDate, String endDate) {
        BigDecimal total = BigDecimal.valueOf(0);
        Integer projectsId = projectsDO == null ? null : projectsDO.getId();

        //车辆消费
        List<Chart> list = new ArrayList<>();


        //生成标题
        String pname = projectsDO == null ? "" : projectsDO.getName();
        String title = fromDate + " 到 " + endDate + pname + "总开支" + total + "元";
        request.setAttribute("title", title);

        if (total.equals(BigDecimal.valueOf(0))) {
            return;
        }

        //生成json
        StringBuffer sbr = new StringBuffer("[");
        for (int i = 0; i < list.size(); i++) {
            Chart c = list.get(i);
            if (i != 0) {
                sbr.append(",");
            }

            double y = c.getPay().multiply(BigDecimal.valueOf(100.0)).divide(total, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
            sbr.append("{ y: " + y + ", pay: " + c.getPay() + ", label: '" + c.getLabel() + "' }");

        }
        sbr.append("]");
        request.setAttribute("datas", sbr.toString());
//      request.setAttribute("datas", "[{ y: 10, pay: 1000, label: 'Chrome' }]");
    }

}
