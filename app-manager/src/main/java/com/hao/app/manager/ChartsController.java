package com.hao.app.manager;

import com.hao.app.manager.controller.BaseController;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@Controller
public class ChartsController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ChartsController.class);


    @RequestMapping("/initKanban.do")
    public String initArea(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fromDate = request.getParameter("fromDate");
        String endDate = request.getParameter("endDate");

        if (StringUtils.isBlank(fromDate)) {
            fromDate = "2019-01";
        }
        if (StringUtils.isBlank(endDate)) {
            endDate = "2020-01";
        }

        //TODO
        BigDecimal totalIn = BigDecimal.valueOf(2500.38);
        BigDecimal totalOut = BigDecimal.valueOf(500);

        request.setAttribute("fromDate", fromDate);
        request.setAttribute("endDate", endDate);

        request.setAttribute("totalIn", totalIn);
        request.setAttribute("totalOut", totalOut);
        request.setAttribute("total", totalIn.subtract(totalOut));
        return "jsp/charts/initKanban";
    }


}
