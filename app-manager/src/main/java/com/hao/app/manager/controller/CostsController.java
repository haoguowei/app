package com.hao.app.manager.controller;

import com.google.gson.Gson;
import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.CostsDO;
import com.hao.app.pojo.CostsTypeDO;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class CostsController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(CostsController.class);

    @Resource
    private CostsService costsService;

    @Resource
    private ProjectsService projectsService;

    @RequestMapping("/initCosts.do")
    public String initCosts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("projectsList", getProjectsList(request));
        request.setAttribute("type1List", costsService.listCostsTypeByParentId(null));
        return "jsp/cost/initCosts";
    }


    @RequestMapping("/searchCosts.do")
    public void searchCosts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CostQueryParam param = genParam(request);
        JsonResult<CostsDO> result = costsService.searchCosts(param);
        writeResponse(response, result);
    }

    private CostQueryParam genParam(HttpServletRequest request) {
        int projectsId = NumberUtils.toInt(request.getParameter("projectsId"), 0);
        int type1 = NumberUtils.toInt(request.getParameter("type1"), 0);
        int type2 = NumberUtils.toInt(request.getParameter("type2"), 0);
        int type3 = NumberUtils.toInt(request.getParameter("type3"), 0);

        String enterDateStart = request.getParameter("enterDateStart");
        String enterDateEnd = request.getParameter("enterDateEnd");

        int start = NumberUtils.toInt(request.getParameter("start"));
        int limit = NumberUtils.toInt(request.getParameter("limit"), 100);

        CostQueryParam param = new CostQueryParam(start, limit);
        if (projectsId > 0) {
            param.setProjectsId(projectsId);
        }
        if (type1 > 0) {
            param.setType1(type1);
        }
        if (type2 > 0) {
            param.setType2(type2);
        }
        if (type3 > 0) {
            param.setType3(type3);
        }

        if (StringUtils.isNotBlank(enterDateStart)) {
            param.setEnterDateStart(enterDateStart);
        }

        if (StringUtils.isNotBlank(enterDateEnd)) {
            param.setEnterDateEnd(enterDateEnd);
        }

        return param;
    }


    @RequestMapping("/initCostsEdit.do")
    public String initCostsEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));
        CostsDO itemObj = costsService.getById(id);
        request.setAttribute("itemObj", itemObj);

        request.setAttribute("projectsList", getProjectsList(request));
        request.setAttribute("type1List", costsService.listCostsTypeByParentId(null));
        return "jsp/cost/initCostsEdit";
    }

    @RequestMapping("/saveCosts.do")
    public String saveCosts(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int id = NumberUtils.toInt(request.getParameter("hideId"), 0);
        int projectId = NumberUtils.toInt(request.getParameter("projects"), 0);
        if (projectId <= 0) {
            return failResult(request, "请选择所属项目");
        }
        ProjectsDO projectsDO = projectsService.getById(projectId);
        if (projectsDO == null) {
            return failResult(request, "请选择所属项目");
        }


        CostsDO item = new CostsDO();
        item.setId(id);
        item.setRemark(request.getParameter("remark"));
        item.setProjects(projectsDO.getId());
        item.setProjectsName(projectsDO.getName());

        String enterDate = request.getParameter("enterDate");
        if (StringUtils.isNotBlank(enterDate)) {
            item.setEnterDate(new SimpleDateFormat("yyyy-MM-dd").parse(enterDate)); //
        }

        //费用金额
        String amount = request.getParameter("amount");
        item.setAmount(StringUtils.isBlank(amount) ? BigDecimal.valueOf(0) : new BigDecimal(amount));

        item.setNumb(request.getParameter("numb"));
        item.setUseful(request.getParameter("useful"));

        //费用类型
        int type1 = NumberUtils.toInt(request.getParameter("type1"), 0);
        int type2 = NumberUtils.toInt(request.getParameter("type2"), 0);
        int type3 = NumberUtils.toInt(request.getParameter("type3"), 0);
        item.setType1(type1);
        item.setType2(type2);
        item.setType3(type3);

        ResultCodeEnum resultCode;
        if (id == 0) {
            item.setCreater(getCurrentUserName(request));
            item.setCreateTime(new Date());
            item.setStatus(0);
            resultCode = costsService.insert(item);
        } else {
            item.setStatus(0);
            item.setUpdateTime(new Date());
            resultCode = costsService.update(item);
        }

        if (resultCode.equals(ResultCodeEnum.SUCCESS)) {
            return successResult(request, "费用管理", "initCosts.do");
        } else {
            return failResult(request, resultCode);
        }
    }

    @RequestMapping("/getTypeListByParentId.do")
    public void getTypeListByParentId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int parentId = NumberUtils.toInt(request.getParameter("parentId"), 0);
        List<CostsTypeDO> list = costsService.listCostsTypeByParentId(parentId);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("info", list);
        response.getWriter().write(new Gson().toJson(map));
    }


}
