package com.hao.app.manager.controller;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.AreaDO;
import com.hao.app.service.AreaService;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Controller
public class AreaController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AreaController.class);

    @Resource
    private AreaService areaService;


    @RequestMapping("/initArea.do")
    public String initArea(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "jsp/area/initArea";
    }


    @RequestMapping("/searchArea.do")
    public void searchArea(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonResult<AreaDO> result = areaService.searchAreas();
        writeResponse(response, result);
    }

    @RequestMapping("/initAreaEdit.do")
    public String initAreaEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));

        AreaDO areaDO = areaService.getById(id);
        request.setAttribute("areaDO", areaDO);
        return "jsp/area/initAreaEdit";
    }

    @RequestMapping("/saveArea.do")
    public String saveArea(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("hideId"), 0);

        AreaDO area = new AreaDO();
        area.setId(id);
        area.setName(request.getParameter("name"));
        area.setRemark(request.getParameter("remark"));

        ResultCodeEnum resultCode;
        if (id == 0) {
            area.setCreater(getCurrentUserName(request));
            area.setCreateTime(new Date());
            resultCode = areaService.insert(area);
        } else {
            area.setUpdateTime(new Date());
            resultCode = areaService.update(area);
        }

        if (resultCode.equals(ResultCodeEnum.SUCCESS)) {
            sysLogsService.writeLog(area.getCreater(), "新增或修改区域:" + area.toString());
            return successResult(request, "区域管理", "initArea.do");
        } else {
            return failResult(request, resultCode);
        }
    }
}
