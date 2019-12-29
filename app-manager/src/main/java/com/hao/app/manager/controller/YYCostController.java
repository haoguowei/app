package com.hao.app.manager.controller;

import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.pojo.YYCostDO;
import com.hao.app.service.YYCostService;
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


@Controller
public class YYCostController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(YYCostController.class);

    @Resource
    private YYCostService yYCostService;


    @RequestMapping("/initYYCost.do")
    public String initYYCost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("projectsList", getProjectsList(request));
        return "jsp/cost/initYYCost";
    }


    @RequestMapping("/searchYYCost.do")
    public void searchYYCost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int projectsId = NumberUtils.toInt(request.getParameter("projectsId"), 0);
        String name = request.getParameter("name");
        String enterDateStart = request.getParameter("enterDateStart");
        String enterDateEnd = request.getParameter("enterDateEnd");

        int start = NumberUtils.toInt(request.getParameter("start"));
        int limit = NumberUtils.toInt(request.getParameter("limit"), 100);


        CostQueryParam param = new CostQueryParam(start, limit);
        if (projectsId > 0) {
            param.setProjectsId(projectsId);
        }
        if (StringUtils.isNotBlank(name)) {
            param.setName(name);
        }
        if (StringUtils.isNotBlank(enterDateStart)) {
            param.setEnterDateStart(enterDateStart);
        }
        if (StringUtils.isNotBlank(enterDateEnd)) {
            param.setEnterDateEnd(enterDateEnd);
        }

        JsonResult<YYCostDO> result = yYCostService.searchYYCost(param);
        writeResponse(response, result);


    }

    @RequestMapping("/initYYCostEdit.do")
    public String initYYCostEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));
        YYCostDO itemObj = yYCostService.getById(id);
        request.setAttribute("itemObj", itemObj);

        request.setAttribute("projectsList", getProjectsList(request));

        return "jsp/cost/initYYCostEdit";
    }

    @RequestMapping("/saveYYCost.do")
    public String saveYYCost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return successResult(request, "消费汇总", "initYYCost.do");
    }
}
