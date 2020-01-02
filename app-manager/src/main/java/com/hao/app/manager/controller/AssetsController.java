package com.hao.app.manager.controller;

import com.google.gson.Gson;
import com.hao.app.commons.entity.Dicts;
import com.hao.app.commons.entity.param.AssetsQueryParam;
import com.hao.app.commons.entity.param.EmployeeQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.manager.export.ExportAssets;
import com.hao.app.pojo.AssetsDO;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.service.AssetsService;
import com.hao.app.service.EmployeeService;
import com.hao.app.service.ProjectsService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AssetsController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AssetsController.class);

    @Resource
    private AssetsService assetsService;

    @Resource
    private EmployeeService employeeService;

    @Resource
    private ProjectsService projectsService;

    @Autowired
    private ExportAssets exportAssets;

    @RequestMapping("/initAssets.do")
    public String initAssets(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("assetsTypeMap", Dicts.assetsTypeMap);
        return "jsp/assets/initAssets";
    }


    @RequestMapping("/searchAssets.do")
    public void searchAssets(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AssetsQueryParam param = genQueryParam(request);
        JsonResult<AssetsDO> result = assetsService.searchAssets(param);
        writeResponse(response, result);
    }

    //导出资产
    @RequestMapping("/exportAssets.do")
    public void exportAssets(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String modelFile = getModelFilePath(request, "assets.xls");
        exportAssets.exportExcel(modelFile, request, response);
    }

    private AssetsQueryParam genQueryParam(HttpServletRequest request) {
        Integer projectsId = getCurrentProjectsId(request);

        int start = NumberUtils.toInt(request.getParameter("start"));
        int limit = NumberUtils.toInt(request.getParameter("limit"), 100);

        String buyTimeStart = request.getParameter("buyTimeStart");
        String buyTimeEnd = request.getParameter("buyTimeEnd");

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

        if (StringUtils.isNotBlank(buyTimeStart)) {
            param.setBuyTimeStart(buyTimeStart);
        }

        if (StringUtils.isNotBlank(buyTimeEnd)) {
            param.setBuyTimeEnd(buyTimeEnd);
        }

        if (StringUtils.isNotBlank(name)) {
            param.setName(name);
        }

        if (StringUtils.isNotBlank(number)) {
            param.setNumber(number);
        }
        return param;
    }

    @RequestMapping("/initAssetsEdit.do")
    public String initAssetsEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));

        AssetsDO assetsDO = assetsService.getById(id);
        request.setAttribute("itemObj", assetsDO);

        request.setAttribute("projectsList", getProjectsList(request));
        request.setAttribute("assetsTypeMap", Dicts.assetsTypeMap);
        request.setAttribute("engineNumberTypeMap", Dicts.engineNumberTypeMap);

        //责任人
        EmployeeQueryParam employeeQuery = new EmployeeQueryParam(0, 100);
        employeeQuery.setProjectsId(getCurrentProjectsId(request));
        Set<Integer> set = new HashSet<>();
        set.add(1); //经理
        employeeQuery.setJobTypes(set);
        request.setAttribute("ownerList", employeeService.searchEmployee(employeeQuery).getResultList());

        return "jsp/assets/initAssetsEdit";
    }

    @RequestMapping("/saveAssets.do")
    public String saveAssets(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int id = NumberUtils.toInt(request.getParameter("hideId"), 0);

        int projectId = NumberUtils.toInt(request.getParameter("projects"), 0);
        if (projectId <= 0) {
            return failResult(request, "请选择所属项目");
        }
        ProjectsDO projectsDO = projectsService.getById(projectId);
        if (projectsDO == null) {
            return failResult(request, "请选择所属项目");
        }

        int type = NumberUtils.toInt(request.getParameter("type"), 0);
        int owner = NumberUtils.toInt(request.getParameter("owner"), 0);
        int quantity = NumberUtils.toInt(request.getParameter("quantity"), 0);
        int quoQuantity = NumberUtils.toInt(request.getParameter("quoQuantity"), 0);
        int engineNumberType = NumberUtils.toInt(request.getParameter("engineNumberType"), 0);
        int staging = NumberUtils.toInt(request.getParameter("staging"), 0);

        String license = request.getParameter("license");
        String brand = request.getParameter("brand");
        String carType = request.getParameter("carType");
        String inOut = request.getParameter("inOut");
        String storageLocation = request.getParameter("storageLocation");
        String engineNumber = request.getParameter("engineNumber");
        String price = request.getParameter("price");
        String purTax = request.getParameter("purTax");

        String name = request.getParameter("name");
        if (StringUtils.isBlank(name)) {
            return failResult(request, "资产名称为必填项");
        }

        String number = request.getParameter("number");
        if (StringUtils.isBlank(number)) {
            return failResult(request, "资产编号为必填项");
        }

        AssetsDO item = new AssetsDO();
        item.setId(id);
        item.setName(name);

        item.setProjects(projectsDO.getId());
        item.setProjectsName(projectsDO.getName());
        item.setType(type);

        item.setNumber(number);
        item.setLicense(license);
        item.setBrand(brand);
        item.setCarType(carType);

        item.setInOut(inOut);
        item.setQuantity(quantity);
        item.setQuoQuantity(quoQuantity);
        item.setOwner(owner);
        item.setStorageLocation(storageLocation);

        item.setEngineNumber(engineNumber);
        item.setEngineNumberType(engineNumberType);

        item.setStaging(staging);
        if (StringUtils.isNotBlank(price)) {
            item.setPrice(new BigDecimal(price));
        }
        if (StringUtils.isNotBlank(purTax)) {
            item.setPurTax(new BigDecimal(purTax));
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String buyTime = request.getParameter("buyTime");
        if (StringUtils.isNotBlank(buyTime)) {
            item.setBuyTime(format.parse(buyTime));
        }

        item.setRemark(request.getParameter("remark"));

        ResultCodeEnum resultCode;
        if (id == 0) {
            item.setCreater(getCurrentUserName(request));
            item.setCreateTime(new Date());
            resultCode = assetsService.insert(item);
        } else {
            item.setUpdateTime(new Date());
            resultCode = assetsService.update(item);
        }

        if (resultCode.equals(ResultCodeEnum.SUCCESS)) {
            sysLogsService.writeLog(item.getCreater(), "新增或修改资产信息:" + item.toString());
            return successResult(request, "资产信息", "initAssets.do");
        } else {
            return failResult(request, resultCode);
        }
    }

    @RequestMapping("/initAssetsHeJi.do")
    public void initAssetsHeJi(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AssetsQueryParam param = genQueryParam(request);
        String info = assetsService.searchAssets4HJ(param);

        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("info", info);
        response.getWriter().write(new Gson().toJson(map));
    }
}
