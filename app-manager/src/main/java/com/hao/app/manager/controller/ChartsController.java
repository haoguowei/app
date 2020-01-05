package com.hao.app.manager.controller;

import com.hao.app.commons.entity.Dicts;
import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.commons.entity.param.OtherCostQueryParam;
import com.hao.app.commons.entity.result.ResultStatistics;
import com.hao.app.commons.utils.DateUtil;
import com.hao.app.manager.dto.Chart;
import com.hao.app.service.OtherCostService;
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
    private OtherCostService otherCostService;


    @RequestMapping("/initKanban.do")
    public String initArea(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fromDate = request.getParameter("fromDate");
        String endDate = request.getParameter("endDate");

        SimpleDateFormat DATE = new SimpleDateFormat("yyyy-MM-dd");

        if (StringUtils.isBlank(fromDate)) {
            fromDate = DATE.format(DateUtil.addMonth(new Date(), -6));
        }
        if (StringUtils.isBlank(endDate)) {
            endDate = DATE.format(new Date());
        }

        request.setAttribute("fromDate", fromDate);
        request.setAttribute("endDate", endDate);

        Integer projectsId = null;
        BigDecimal total = BigDecimal.valueOf(0);

        List<Chart> list = new ArrayList<>();
        BigDecimal cardTotal = searchCardTotal(projectsId, fromDate, endDate);
        list.add(new Chart("车辆消费", cardTotal));
        total = total.add(cardTotal);

        List<ResultStatistics> otherList = searchOtherTotal(projectsId, fromDate, endDate);
        if (otherList != null) {
            for (ResultStatistics rs : otherList) {
                String name = Dicts.otherPayTypeMap.get(rs.getK());
                list.add(new Chart(StringUtils.isBlank(name) ? "未知" : name, rs.getVal()));
                total = total.add(rs.getVal());
            }
        }

        request.setAttribute("title", fromDate + " 到 " + endDate + "总开支" + total + "元");
        if (total.equals(BigDecimal.valueOf(0))) {
            return "jsp/charts/initKanban";
        }

        StringBuffer sbr = new StringBuffer("[");
        for (int i = 0; i < list.size(); i++) {
            Chart c = list.get(i);
            if (i != 0) {
                sbr.append(",");
            }

            double y = c.getPay().multiply(BigDecimal.valueOf(100.0)).divide(total).doubleValue();
            sbr.append("{ y: " + y + ", pay: " + c.getPay() + ", label: '" + c.getLabel() + "' }");

        }
        sbr.append("]");
        request.setAttribute("datas", sbr.toString());
//        request.setAttribute("datas", "[{ y: 10, pay: 1000, label: 'Chrome' }]");

        return "jsp/charts/initKanban";
    }

    private List<ResultStatistics> searchOtherTotal(Integer projectsId, String dateStart, String dateEnd) {
        OtherCostQueryParam param = new OtherCostQueryParam();
        param.setProjectsId(projectsId);
        param.setDateStart(dateStart);
        param.setDateEnd(dateEnd);
        return otherCostService.searchTotalPay(param);
    }

    private BigDecimal searchCardTotal(Integer projectsId, String enterDateStart, String enterDateEnd) {
        CostQueryParam param = new CostQueryParam();
        param.setProjectsId(projectsId);
        param.setEnterDateStart(enterDateStart);
        param.setEnterDateEnd(enterDateEnd);
        return yyCostService.searchTotalPay(param);
    }


}
