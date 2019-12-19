package com.hao.app.manager.controller;

import com.hao.app.service.YYCostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class YYCostController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(YYCostController.class);

    @Resource
    private YYCostService yYCostService;


    @RequestMapping("/initYYCost.do")
    public String initYYCost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "jsp/cost/initYYCost";
    }


    @RequestMapping("/searchYYCost.do")
    public void searchYYCost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @RequestMapping("/initYYCostEdit.do")
    public String initYYCostEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {

        return "jsp/cost/initYYCostEdit";
    }

    @RequestMapping("/saveYYCost.do")
    public String saveYYCost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return successResult(request, "区域管理", "initArea.do");
    }
}
