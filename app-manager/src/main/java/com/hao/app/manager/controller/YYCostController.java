package com.hao.app.manager.controller;

import com.hao.app.commons.entity.param.AssetsQueryParam;
import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.commons.entity.param.EmployeeQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.AssetsDO;
import com.hao.app.pojo.EmployeeDO;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.pojo.YYCostDO;
import com.hao.app.service.AssetsService;
import com.hao.app.service.EmployeeService;
import com.hao.app.service.ProjectsService;
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
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class YYCostController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(YYCostController.class);

    @Resource
    private YYCostService yYCostService;

    @Resource
    private AssetsService assetsService;

    @Resource
    private ProjectsService projectsService;
    @Resource
    private EmployeeService employeeService;

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

        //选择司机
        EmployeeQueryParam employeeQuery = new EmployeeQueryParam(0, 100);
        employeeQuery.setProjectsId(getCurrentProjectsId(request));
        Set<Integer> set = new HashSet<>();
        set.add(6); //司机
        employeeQuery.setJobTypes(set);
        request.setAttribute("employeeList", employeeService.searchEmployee(employeeQuery).getResultList());

        //选择资产
        AssetsQueryParam param = new AssetsQueryParam(0, 100);
        param.setType(1); //车辆
        int projectsId = getCurrentProjectsId(request);
        if (projectsId > 0) {
            param.setProjectsId(projectsId);
        }
        List<AssetsDO> assetsList = assetsService.searchAssets(param).getResultList();
        request.setAttribute("assetsList", assetsList);

        request.setAttribute("projectsList", getProjectsList(request));

        return "jsp/cost/initYYCostEdit";
    }

    @RequestMapping("/saveYYCost.do")
    public String saveYYCost(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        int id = NumberUtils.toInt(request.getParameter("hideId"), 0);
        int projectId = NumberUtils.toInt(request.getParameter("projects"), 0);
        if (projectId <= 0) {
            return failResult(request, "请选择所属项目");
        }
        ProjectsDO projectsDO = projectsService.getById(projectId);
        if (projectsDO == null) {
            return failResult(request, "请选择所属项目");
        }

        int assetId = NumberUtils.toInt(request.getParameter("assetId"), 0);
        AssetsDO assetsDO = assetsService.getById(assetId);
        if (assetsDO == null) {
            return failResult(request, "请选择消费资产");
        }

        int employeeId = NumberUtils.toInt(request.getParameter("employeeId"), 0);
        EmployeeDO employeeDO = employeeService.getById(employeeId);
        if (employeeDO == null) {
            return failResult(request, "请选择消费司机");
        }

        YYCostDO item = new YYCostDO();
        item.setId(id);
        item.setRemark(request.getParameter("remark"));
        item.setProjects(projectsDO.getId());
        item.setProjectsName(projectsDO.getName());
        item.setAssetId(assetId);
        item.setEmployeeId(employeeDO.getId());
        item.setEmployeeName(employeeDO.getName());

        int startMileage = NumberUtils.toInt(request.getParameter("startMileage"), 0);
        int endMileage = NumberUtils.toInt(request.getParameter("endMileage"), 0);

        int workload = NumberUtils.toInt(request.getParameter("workload"), 0);
        int fuel = NumberUtils.toInt(request.getParameter("fuel"), 0);
        int shiguTimes = NumberUtils.toInt(request.getParameter("shiguTimes"), 0);

        item.setStartMileage(startMileage);
        item.setEndMileage(endMileage);
        item.setWorkload(workload);
        item.setFuel(fuel);
        item.setShiguTimes(shiguTimes);
        item.setFixFactory(request.getParameter("fixFactory"));

        String fuelAmount = request.getParameter("fuelAmount");
        item.setFuelAmount(StringUtils.isBlank(fuelAmount) ? BigDecimal.valueOf(0) : new BigDecimal(fuelAmount));

        String baoyangAmount = request.getParameter("baoyangAmount");
        item.setBaoyangAmount(StringUtils.isBlank(baoyangAmount) ? BigDecimal.valueOf(0) : new BigDecimal(baoyangAmount));

        String fixAmount = request.getParameter("fixAmount");
        item.setFixAmount(StringUtils.isBlank(fixAmount) ? BigDecimal.valueOf(0) : new BigDecimal(fixAmount));

        String shiguAmount = request.getParameter("shiguAmount");
        item.setShiguAmount(StringUtils.isBlank(shiguAmount) ? BigDecimal.valueOf(0) : new BigDecimal(shiguAmount));

        String shiguOutAmount = request.getParameter("shiguOutAmount");
        item.setShiguOutAmount(StringUtils.isBlank(shiguOutAmount) ? BigDecimal.valueOf(0) : new BigDecimal(shiguOutAmount));

        String baoxianAmount = request.getParameter("baoxianAmount");
        item.setBaoxianAmount(StringUtils.isBlank(baoxianAmount) ? BigDecimal.valueOf(0) : new BigDecimal(baoxianAmount));

        String yearCheckAmount = request.getParameter("yearCheckAmount");
        item.setYearCheckAmount(StringUtils.isBlank(yearCheckAmount) ? BigDecimal.valueOf(0) : new BigDecimal(yearCheckAmount));

        String enterDate = request.getParameter("enterDate");
        if (StringUtils.isNotBlank(enterDate)) {
            item.setEnterDate(new SimpleDateFormat("yyyy-MM-dd").parse(enterDate)); //
        }

        ResultCodeEnum resultCode;
        if (id == 0) {
            item.setCreater(getCurrentUserName(request));
            item.setCreateTime(new Date());
            resultCode = yYCostService.insert(item);
        } else {
            item.setUpdateTime(new Date());
            resultCode = yYCostService.update(item);
        }

        if (resultCode.equals(ResultCodeEnum.SUCCESS)) {
            sysLogsService.writeLog(item.getCreater(), "新增或修改区域:" + item.toString());
            return successResult(request, "消费汇总", "initYYCost.do");
        } else {
            return failResult(request, resultCode);
        }
    }
}
