package com.hao.app.manager;

import com.hao.app.commons.utils.DateUtil;
import com.hao.app.manager.controller.BaseController;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ChartsController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ChartsController.class);


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

        return "jsp/charts/initKanban";
    }


}
