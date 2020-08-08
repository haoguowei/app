package com.hao.app.manager.export;

import com.hao.app.commons.entity.param.CostQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.utils.DateUtil;
import com.hao.app.pojo.CostsDO;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.service.CostsService;
import com.hao.app.service.ProjectsService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("exportCosts")
public class ExportCosts extends AbstractExport {

    @Resource
    private CostsService costsService;

    @Resource
    private ProjectsService projectsService;

    //costs.xls
    @Override
    public String writeExcel(HttpServletRequest request, HSSFWorkbook wb, HSSFSheet sheet) {
        CostQueryParam param = genCostsParam(request);

        String title = "所有项目费用明细";
        ProjectsDO projectsDO = projectsService.getById(param.getProjectsId());
        if (projectsDO != null) {
            title = projectsDO.getName() + "项目费用明细";
        }

        //修改标题
        Row titleRow = sheet.getRow(0);
        getCell(titleRow, 0, title);

        HSSFCellStyle cellStyleRight = ExcelUtil.getCellStyleRight(wb);
        HSSFCellStyle cellStyleLeft = ExcelUtil.getCellStyleLeft(wb);

        param.setPageStart(0);
        param.setPageLimit(1000);
        JsonResult<CostsDO> result = costsService.searchCosts(param);
        List<CostsDO> list = result.getResultList();


        String msg = "共" + result.getTotal() + "条记录，最多支持导出1000条，如果超出，请重新选择查询条件。";
        getCell(sheet.getRow(1), 0, msg);


        if (list == null) {
            return title;
        }

        int next = 3;
        for (CostsDO cost : list) {
            Row row = sheet.createRow(next++);

            genCell(row, cellStyleRight, 0, cost.getId());
            genCell(row, cellStyleLeft, 1, cost.getProjectsName());
            genCell(row, cellStyleLeft, 2, DateUtil.fmtDate(cost.getEnterDate()));


            String a = cost.getType1Str();
            String b = cost.getType2Str();
            String c = cost.getType3Str();
            String abc = "";
            if (StringUtils.isBlank(a) && StringUtils.isBlank(b) && StringUtils.isBlank(c)) {
                abc = "";
            } else {
                abc = a + "-" + b + "-" + c;
            }
            genCell(row, cellStyleLeft, 3, abc);
            genCell(row, cellStyleRight, 4, format(cost.getAmount()));


            String statusStr = "";
            if (cost.getStatus() == null || cost.getStatus() == 0) {
                statusStr = "录入";
            } else if (cost.getStatus() == 1) {
                statusStr = "已提交";
            }
            genCell(row, cellStyleLeft, 5, statusStr);


            genCell(row, cellStyleLeft, 6, cost.getNumb());
            genCell(row, cellStyleLeft, 7, cost.getUseful());
            genCell(row, cellStyleLeft, 8, cost.getCreater());
        }


        return title;
    }

    private CostQueryParam genCostsParam(HttpServletRequest request) {
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

}
