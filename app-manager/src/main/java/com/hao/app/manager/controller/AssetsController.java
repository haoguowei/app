package com.hao.app.manager.controller;

import com.hao.app.commons.entity.param.AssetsQueryParam;
import com.hao.app.commons.entity.param.EmployeeQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.pojo.AssetsDO;
import com.hao.app.service.AssetsService;
import com.hao.app.service.EmployeeService;
import com.hao.app.utils.Dicts;
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
import java.util.HashSet;
import java.util.Set;

@Controller
public class AssetsController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AssetsController.class);

    @Resource
    private AssetsService assetsService;

    @Resource
    private EmployeeService employeeService;

    @RequestMapping("/initAssets.do")
    public String initAssets(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "jsp/assets/initAssets";
    }


    @RequestMapping("/searchAssets.do")
    public void searchAssets(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer projectsId = getCurrentProjectsId(request);

        int start = NumberUtils.toInt(request.getParameter("start"));
        int limit = NumberUtils.toInt(request.getParameter("limit"), 100);

        String name = request.getParameter("name");
        String number = request.getParameter("number");
        int type = NumberUtils.toInt(request.getParameter("type"));

        AssetsQueryParam param = new AssetsQueryParam(start, limit);
        if (projectsId > 0) {
            param.setProjectsId(projectsId);
        }

        if (type > -1) {
            param.setType(type);
        }

        if (StringUtils.isNotBlank(name)) {
            param.setName(name);
        }

        if (StringUtils.isNotBlank(number)) {
            param.setNumber(number);
        }

        JsonResult<AssetsDO> result = assetsService.searchAssets(param);
        writeResponse(response, result);
    }

    @RequestMapping("/initAssetsEdit.do")
    public String initAssetsEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));

        AssetsDO assetsDO = assetsService.getById(id);
        request.setAttribute("itemObj", assetsDO);

        request.setAttribute("projectsList", getProjectsList(request));
        request.setAttribute("assetsTypeMap", Dicts.assetsTypeMap);

        //责任人
        EmployeeQueryParam employeeQuery = new EmployeeQueryParam();
        employeeQuery.setProjectsId(getCurrentProjectsId(request));
        Set<Integer> set = new HashSet<>();
        set.add(1); //经理
        employeeQuery.setJobTypes(set);
        request.setAttribute("ownerList", employeeService.searchEmployee(employeeQuery).getResultList());

        return "jsp/assets/initAssetsEdit";
    }

    @RequestMapping("/saveAssets.do")
    public String saveAssets(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return successResult(request, "资产信息", "initAssets.do");
    }
}
