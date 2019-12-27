package com.hao.app.manager.controller;

import com.hao.app.commons.entity.param.AssetsQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.pojo.AssetsDO;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.service.AssetsService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class AssetsController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AssetsController.class);

    @Resource
    private AssetsService assetsService;

    @Resource
    private ProjectsService projectsService;


    @RequestMapping("/initAssets.do")
    public String initAssets(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "jsp/assets/initAssets";
    }


    @RequestMapping("/searchAssets.do")
    public void searchAssets(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer projectsId = getCurrentProjects(request);

        int start = NumberUtils.toInt(request.getParameter("start"));
        int limit = NumberUtils.toInt(request.getParameter("limit"), 100);

        String name = request.getParameter("name");
        String number = request.getParameter("number");
        int type = NumberUtils.toInt(request.getParameter("type"));

        AssetsQueryParam param = new AssetsQueryParam(start, limit);
        param.setProjectsId(projectsId);
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

        Integer projectsId = getCurrentProjects(request);
        ProjectsDO projectsDO = projectsService.getById(projectsId);
        List<ProjectsDO> projectsList = new ArrayList<>();
        projectsList.add(projectsDO);

        request.setAttribute("projectsList", projectsList);

        return "jsp/assets/initAssetsEdit";
    }

    @RequestMapping("/saveAssets.do")
    public String saveAssets(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return successResult(request, "资产信息", "initAssets.do");
    }
}
