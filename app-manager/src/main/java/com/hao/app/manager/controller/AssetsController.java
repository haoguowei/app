package com.hao.app.manager.controller;

import com.google.gson.Gson;
import com.hao.app.commons.entity.Dicts;
import com.hao.app.commons.entity.param.AssetsQueryParam;
import com.hao.app.commons.entity.param.EmployeeQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.EmpStatusEnum;
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
        request.setAttribute("brandMap", Dicts.cardBrandMap);
        request.setAttribute("carTypeMap", Dicts.carTypeMap);
        request.setAttribute("projectsList", getProjectsList(request));
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
        int start = NumberUtils.toInt(request.getParameter("start"));
        int limit = NumberUtils.toInt(request.getParameter("limit"), 100);

        int projectsId = NumberUtils.toInt(request.getParameter("projectsId"));
        int carType = NumberUtils.toInt(request.getParameter("carType"));
        int brand = NumberUtils.toInt(request.getParameter("brand"));
        String buyTimeStart = request.getParameter("buyTimeStart");
        String buyTimeEnd = request.getParameter("buyTimeEnd");

        String name = request.getParameter("name");
        String number = request.getParameter("number");

        AssetsQueryParam param = new AssetsQueryParam(start, limit);
        if (projectsId > 0) {
            param.setProjectsId(projectsId);
        }

        if (brand > 0) {
            param.setBrand(brand);
        }

        if (carType > 0) {
            param.setCarType(carType);
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
        request.setAttribute("engineNumberTypeMap", Dicts.cardNumberTypeMap);

        request.setAttribute("brandMap", Dicts.cardBrandMap);
        request.setAttribute("carTypeMap", Dicts.carTypeMap);

        //责任人
        EmployeeQueryParam employeeQuery = new EmployeeQueryParam(0, 100);
        employeeQuery.setStatus(EmpStatusEnum.OFFICIAL.getCode());
        employeeQuery.setProjectsId(getCurrentProjectsId(request));
        Set<Integer> set = new HashSet<>();
        set.add(1); //经理
        set.add(2); //主管
        set.add(5); //队长
        set.add(6); //司机
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

        int owner = NumberUtils.toInt(request.getParameter("owner"), 0);
        int quantity = NumberUtils.toInt(request.getParameter("quantity"), 0);
        int quoQuantity = NumberUtils.toInt(request.getParameter("quoQuantity"), 0);
        int engineNumberType = NumberUtils.toInt(request.getParameter("engineNumberType"), 0);
        int staging = NumberUtils.toInt(request.getParameter("staging"), 0);
        int brand = NumberUtils.toInt(request.getParameter("brand"), 0);
        int carType = NumberUtils.toInt(request.getParameter("carType"), 0);

        String license = request.getParameter("license");
        String inOut = request.getParameter("inOut");
        String storageLocation = request.getParameter("storageLocation");
        String engineNumber = request.getParameter("engineNumber");
        String price = request.getParameter("price");
        String purTax = request.getParameter("purTax");
        String tanxiao = request.getParameter("tanxiao");
        String zhejiu = request.getParameter("zhejiu");

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
        if (StringUtils.isNotBlank(tanxiao)) {
            item.setTanxiao(new BigDecimal(tanxiao));
        }
        if (StringUtils.isNotBlank(zhejiu)) {
            item.setZhejiu(new BigDecimal(zhejiu));
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
            sysLogsService.writeLog(item.getCreater(), "新增或修改车辆管理:" + item.toString());
            return successResult(request, "车辆管理", "initAssets.do");
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
