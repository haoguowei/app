package com.hao.app.manager.controller;

import com.google.gson.Gson;
import com.hao.app.commons.entity.Dicts;
import com.hao.app.commons.entity.param.YunyingCostQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.pojo.YunyingCostDO;
import com.hao.app.service.ProjectsService;
import com.hao.app.service.YunyingCostService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
public class YunyingCostController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(YunyingCostController.class);

    @Resource
    private YunyingCostService yunyingCostService;

    @Resource
    private ProjectsService projectsService;

    @RequestMapping("/initYunyingCost.do")
    public String initYunyingCost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("projectsList", getProjectsList(request));
        request.setAttribute("yunyingPayTypeMap", Dicts.yunyingPayTypeMap);
        return "jsp/cost/initYunyingCost";
    }


    @RequestMapping("/searchYunyingCost.do")
    public void searchYunyingCost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        YunyingCostQueryParam param = genParam(request);
        JsonResult<YunyingCostDO> result = yunyingCostService.searchCost(param);
        writeResponse(response, result);
    }

    private YunyingCostQueryParam genParam(HttpServletRequest request) {
        int projectsId = NumberUtils.toInt(request.getParameter("projectsId"), 0);
        int payType = NumberUtils.toInt(request.getParameter("payType"), 0);

        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");

        int start = NumberUtils.toInt(request.getParameter("start"));
        int limit = NumberUtils.toInt(request.getParameter("limit"), 100);

        YunyingCostQueryParam param = new YunyingCostQueryParam(start, limit);
        if (projectsId > 0) {
            param.setProjectsId(projectsId);
        }
        if (payType > 0) {
            param.setPayType(payType);
        }
        if (StringUtils.isNotBlank(dateStart)) {
            param.setDateStart(dateStart);
        }
        if (StringUtils.isNotBlank(dateEnd)) {
            param.setDateEnd(dateEnd);
        }
        return param;
    }

    @RequestMapping("/initYunyingCostEdit.do")
    public String initYunyingCostEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));
        YunyingCostDO itemObj = yunyingCostService.getById(id);
        request.setAttribute("itemObj", itemObj);

        request.setAttribute("projectsList", getProjectsList(request));
        request.setAttribute("yunyingPayTypeMap", Dicts.yunyingPayTypeMap);
        return "jsp/cost/initYunyingCostEdit";
    }

    @RequestMapping("/saveYunyingCost.do")
    public String saveYunyingCost(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int id = NumberUtils.toInt(request.getParameter("hideId"), 0);
        int projectId = NumberUtils.toInt(request.getParameter("projects"), 0);
        if (projectId <= 0) {
            return failResult(request, "请选择所属项目");
        }

        ProjectsDO projectsDO = projectsService.getById(projectId);
        if (projectsDO == null) {
            return failResult(request, "请选择所属项目");
        }

        YunyingCostDO item = new YunyingCostDO();
        item.setId(id);
        item.setFukuan(request.getParameter("fukuan"));
        item.setShoukuan(request.getParameter("shoukuan"));
        item.setRemark(request.getParameter("remark"));
        item.setProjects(projectsDO.getId());
        item.setProjectsName(projectsDO.getName());

        int payType = NumberUtils.toInt(request.getParameter("payType"), 0);
        item.setPayType(payType);

        String payAmount = request.getParameter("payAmount");
        item.setPayAmount(StringUtils.isBlank(payAmount) ? BigDecimal.valueOf(0) : new BigDecimal(payAmount));

        String payDay = request.getParameter("payDay");
        if (StringUtils.isNotBlank(payDay)) {
            item.setPayDay(new SimpleDateFormat("yyyy-MM-dd").parse(payDay)); //
        }

        ResultCodeEnum resultCode;
        if (id == 0) {
            item.setCreater(getCurrentUserName(request));
            item.setCreateTime(new Date());
            resultCode = yunyingCostService.insert(item);
        } else {
            item.setUpdateTime(new Date());
            resultCode = yunyingCostService.update(item);
        }

        if (resultCode.equals(ResultCodeEnum.SUCCESS)) {
            return successResult(request, "运营管理费", "initYunyingCost.do");
        } else {
            return failResult(request, resultCode);
        }
    }

    private BigDecimal getBigDecimal(BigDecimal v) {
        if (v == null) {
            return BigDecimal.valueOf(0);
        }
        return v;
    }

    @RequestMapping("/initYunyingCostHeJi.do")
    public void initYunyingCostHeJi(HttpServletRequest request, HttpServletResponse response) throws IOException {
        YunyingCostQueryParam param = genParam(request);
        String info = yunyingCostService.searchCost4HJ(param);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("info", info);
        response.getWriter().write(new Gson().toJson(map));
    }
}
