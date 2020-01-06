package com.hao.app.manager.controller;

import com.hao.app.commons.entity.Dicts;
import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.commons.entity.param.OtherCostQueryParam;
import com.hao.app.commons.entity.result.ResultStatistics;
import com.hao.app.commons.utils.DateUtil;
import com.hao.app.manager.dto.Chart;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.service.OtherCostService;
import com.hao.app.service.ProjectsService;
import com.hao.app.service.YYCostService;
import org.apache.commons.lang.StringUtils;
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
public class ChartsController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ChartsController.class);

    @Resource
    private YYCostService yyCostService;

    @Resource
    private OtherCostService oherCostService;

    @Resource
    private ProjectsService projectsService;


    @RequestMapping("/initKanban.do")
    public String initArea(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        //项目
        Integer projectId = null;
        ProjectsDO projectsDO = projectsService.getById(projectId);

        //饼状图
        printPieChart(request, projectsDO, fromDate, endDate);

        //柱状图
        printColumnChart(request, projectsDO, fromDate, endDate);

        return "jsp/charts/initKanban";
    }

    private void printColumnChart(HttpServletRequest request, ProjectsDO projectsDO, String fromDate, String endDate) {

    }

    //饼状图
    private void printPieChart(HttpServletRequest request, ProjectsDO projectsDO, String fromDate, String endDate) {
        BigDecimal total = BigDecimal.valueOf(0);
        Integer projectsId = projectsDO == null ? null : projectsDO.getId();

        //生成标题
        String title = projectsDO != null ? projectsDO.getName() : "所有";
        title += "项目" + fromDate + " 到 " + endDate + "总开支" + total + "元";
        request.setAttribute("title", title);

        //车辆消费
        List<Chart> list = new ArrayList<>();
        BigDecimal cardTotal = searchCardTotal(projectsId, fromDate, endDate);
        list.add(new Chart("车辆消费", cardTotal));
        total = total.add(cardTotal);

        //其他消费
        List<ResultStatistics> otherList = searchOtherTotal(projectsId, fromDate, endDate);
        if (otherList != null) {
            for (ResultStatistics rs : otherList) {
                String name = Dicts.otherPayTypeMap.get(rs.getK());
                list.add(new Chart(StringUtils.isBlank(name) ? "未知" : name, rs.getVal()));
                total = total.add(rs.getVal());
            }
        }

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

    private List<ResultStatistics> searchOtherTotal(Integer projectsId, String dateStart, String dateEnd) {
        OtherCostQueryParam param = new OtherCostQueryParam();
        param.setProjectsId(projectsId);
        param.setDateStart(dateStart);
        param.setDateEnd(dateEnd);
        return oherCostService.searchTotalPay(param);
    }

    private BigDecimal searchCardTotal(Integer projectsId, String enterDateStart, String enterDateEnd) {
        CostQueryParam param = new CostQueryParam();
        param.setProjectsId(projectsId);
        param.setEnterDateStart(enterDateStart);
        param.setEnterDateEnd(enterDateEnd);
        return yyCostService.searchTotalPay(param);
    }


}
