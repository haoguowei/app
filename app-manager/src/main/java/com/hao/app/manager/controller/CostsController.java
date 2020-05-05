package com.hao.app.manager.controller;

import com.hao.app.commons.entity.param.AssetsQueryParam;
import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.commons.entity.param.EmployeeQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.EmpStatusEnum;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.AssetsDO;
import com.hao.app.pojo.CostsDO;
import com.hao.app.pojo.EmployeeDO;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.service.AssetsService;
import com.hao.app.service.CostsService;
import com.hao.app.service.EmployeeService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Controller
public class CostsController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(CostsController.class);

    @Resource
    private CostsService costsService;

    @Resource
    private AssetsService assetsService;

    @Resource
    private ProjectsService projectsService;
    @Resource
    private EmployeeService employeeService;

    @RequestMapping("/initCosts.do")
    public String initCosts(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("projectsList", getProjectsList(request));

        //选择司机
        request.setAttribute("employeeList", getEmployeeList(getCurrentProjectsId(request)));
        return "jsp/cost/initCosts";
    }


    @RequestMapping("/searchYYCost.do")
    public void searchYYCost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CostQueryParam param = genParam(request);
        JsonResult<CostsDO> result = costsService.searchYYCost(param);
        writeResponse(response, result);
    }

    private CostQueryParam genParam(HttpServletRequest request) {
        int projectsId = NumberUtils.toInt(request.getParameter("projectsId"), 0);
        int employeeId = NumberUtils.toInt(request.getParameter("employeeId"), 0);
        String enterDateStart = request.getParameter("enterDateStart");
        String enterDateEnd = request.getParameter("enterDateEnd");

        int start = NumberUtils.toInt(request.getParameter("start"));
        int limit = NumberUtils.toInt(request.getParameter("limit"), 100);


        CostQueryParam param = new CostQueryParam(start, limit);
        if (projectsId > 0) {
            param.setProjectsId(projectsId);
        }
        if (employeeId > 0) {
            param.setProjectsId(employeeId);
        }

        if (StringUtils.isNotBlank(enterDateStart)) {
            param.setEnterDateStart(enterDateStart);
        }
        if (StringUtils.isNotBlank(enterDateEnd)) {
            param.setEnterDateEnd(enterDateEnd);
        }
        return param;
    }

    private List<EmployeeDO> getEmployeeList(Integer projectsId) {
        //选择司机
        EmployeeQueryParam employeeQuery = new EmployeeQueryParam(0, 100);
        employeeQuery.setStatus(EmpStatusEnum.OFFICIAL.getCode());
        employeeQuery.setProjectsId(projectsId);
        Set<Integer> set = new HashSet<>();
        set.add(6); //司机
        employeeQuery.setJobTypes(set);
        return employeeService.searchEmployee(employeeQuery).getResultList();
    }

    @RequestMapping("/initCostsEdit.do")
    public String initCostsEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));
        CostsDO itemObj = costsService.getById(id);
        request.setAttribute("itemObj", itemObj);

        //选择司机
        request.setAttribute("employeeList", getEmployeeList(getCurrentProjectsId(request)));

        //选择资产
        AssetsQueryParam param = new AssetsQueryParam(0, 100);
        int projectsId = getCurrentProjectsId(request);
        if (projectsId > 0) {
            param.setProjectsId(projectsId);
        }
        List<AssetsDO> assetsList = assetsService.searchAssets(param).getResultList();
        request.setAttribute("assetsList", assetsList);

        request.setAttribute("projectsList", getProjectsList(request));

        return "jsp/cost/initCostsEdit";
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

        CostsDO item = new CostsDO();
        item.setId(id);
        item.setRemark(request.getParameter("remark"));
        item.setProjects(projectsDO.getId());
        item.setProjectsName(projectsDO.getName());

        String enterDate = request.getParameter("enterDate");
        if (StringUtils.isNotBlank(enterDate)) {
            item.setEnterDate(new SimpleDateFormat("yyyy-MM-dd").parse(enterDate)); //
        }

//        BigDecimal total = getBigDecimal(item.getFuelAmount());
//        item.setTotalAmount(total);

        ResultCodeEnum resultCode;
        if (id == 0) {
            item.setCreater(getCurrentUserName(request));
            item.setCreateTime(new Date());
            resultCode = costsService.insert(item);
        } else {
            item.setUpdateTime(new Date());
            resultCode = costsService.update(item);
        }

        if (resultCode.equals(ResultCodeEnum.SUCCESS)) {
            return successResult(request, "消费汇总", "initCosts.do");
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

}
