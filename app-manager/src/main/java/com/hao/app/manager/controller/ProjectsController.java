package com.hao.app.manager.controller;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.AreaDO;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.service.AreaService;
import com.hao.app.service.ProjectsService;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ProjectsController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ProjectsController.class);

    @Resource
    private ProjectsService projectsService;

    @Resource
    private AreaService areaService;

    @RequestMapping("/initProjects.do")
    public String initProjects(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "jsp/projects/initProjects";
    }


    @RequestMapping("/searchProjects.do")
    public void searchArea(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int areaId = NumberUtils.toInt(request.getParameter("areaId"));
        JsonResult<ProjectsDO> result = projectsService.search(areaId);
        writeResponse(response, result);
    }

    @RequestMapping("/initProjectsEdit.do")
    public String initProjectsEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));

        ProjectsDO projectsDO = projectsService.getById(id);
        request.setAttribute("itemObj", projectsDO);

        JsonResult<AreaDO> result = areaService.searchAreas();
        List<AreaDO> areaList = result.getResultList();
        int currentAreaId = getCurrentArea(request);
        if (currentAreaId > 0) {
            if (!CollectionUtils.isEmpty(areaList)) {
                List<AreaDO> tmpList = new ArrayList<>();
                for (AreaDO tmp : areaList) {
                    if (tmp.getId().equals(currentAreaId)) {
                        tmpList.add(tmp);
                    }
                }
                request.setAttribute("areaList", tmpList);
            }

        } else {
            request.setAttribute("areaList", areaList);
        }
        return "jsp/projects/initProjectsEdit";
    }

    @RequestMapping("/saveProjects.do")
    public String saveProjects(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("hideId"), 0);
        int areaId = NumberUtils.toInt(request.getParameter("areaId"));

        ProjectsDO obj = new ProjectsDO();
        obj.setId(id);
        obj.setAreaId(areaId);
        obj.setName(request.getParameter("name"));
        obj.setRemark(request.getParameter("remark"));


        ResultCodeEnum resultCode;
        if (id == 0) {
            obj.setCreater(getCurrentUserName(request));
            obj.setCreateTime(new Date());
            resultCode = projectsService.insert(obj);
        } else {
            obj.setUpdateTime(new Date());
            resultCode = projectsService.update(obj);
        }

        if (resultCode.equals(ResultCodeEnum.SUCCESS)) {
            sysLogsService.writeLog(obj.getCreater(), "新增或修改项目:" + obj.toString());
            return successResult(request, "项目管理", "initProjects.do");
        } else {
            return failResult(request, resultCode);
        }
    }
}
