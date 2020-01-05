package com.hao.app.manager.controller;

import com.google.gson.Gson;
import com.hao.app.commons.entity.Dicts;
import com.hao.app.commons.entity.param.OtherCostQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.OtherCostDO;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.service.OtherCostService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Controller
public class OtherCostController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(OtherCostController.class);

    @Resource
    private OtherCostService otherCostService;

    @Resource
    private ProjectsService projectsService;

    @RequestMapping("/initOtherCost.do")
    public String initOtherCost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("projectsList", getProjectsList(request));
        request.setAttribute("otherPayTypeMap", Dicts.otherPayTypeMap);
        return "jsp/cost/initOtherCost";
    }


    @RequestMapping("/searchOtherCost.do")
    public void searchOtherCost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OtherCostQueryParam param = genParam(request);
        JsonResult<OtherCostDO> result = otherCostService.searchCost(param);
        writeResponse(response, result);
    }

    private OtherCostQueryParam genParam(HttpServletRequest request) {
        int projectsId = NumberUtils.toInt(request.getParameter("projectsId"), 0);
        int payType = NumberUtils.toInt(request.getParameter("payType"), 0);

        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");

        int start = NumberUtils.toInt(request.getParameter("start"));
        int limit = NumberUtils.toInt(request.getParameter("limit"), 100);

        OtherCostQueryParam param = new OtherCostQueryParam(start, limit);
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

    @RequestMapping("/initOtherCostEdit.do")
    public String initOtherCostEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));
        OtherCostDO itemObj = otherCostService.getById(id);
        request.setAttribute("itemObj", itemObj);

        request.setAttribute("projectsList", getProjectsList(request));
        request.setAttribute("otherPayTypeMap", Dicts.otherPayTypeMap);
        return "jsp/cost/initOtherCostEdit";
    }

    @RequestMapping("/saveOtherCost.do")
    public String saveOtherCost(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int id = NumberUtils.toInt(request.getParameter("hideId"), 0);
        int projectId = NumberUtils.toInt(request.getParameter("projects"), 0);
        if (projectId <= 0) {
            return failResult(request, "请选择所属项目");
        }

        ProjectsDO projectsDO = projectsService.getById(projectId);
        if (projectsDO == null) {
            return failResult(request, "请选择所属项目");
        }

        OtherCostDO item = new OtherCostDO();
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
            resultCode = otherCostService.insert(item);
        } else {
            item.setUpdateTime(new Date());
            resultCode = otherCostService.update(item);
        }

        if (resultCode.equals(ResultCodeEnum.SUCCESS)) {
            sysLogsService.writeLog(item.getCreater(), "新增或修改日常费用:" + item.toString());
            return successResult(request, "日常消费管理", "initOtherCost.do");
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

    @RequestMapping("/initOtherCostHeJi.do")
    public void initOtherCostHeJi(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OtherCostQueryParam param = genParam(request);
        String info = otherCostService.searchCost4HJ(param);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("info", info);
        response.getWriter().write(new Gson().toJson(map));
    }
}
