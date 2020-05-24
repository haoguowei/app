package com.hao.app.manager.export;

import com.hao.app.commons.entity.param.TableQueryParam;
import com.hao.app.commons.entity.result.TableKey;
import com.hao.app.commons.utils.WebUtils;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.service.CostsService;
import com.hao.app.service.ProjectsService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("exportCostZhuanxiang")
public class ExportCostZhuanxiang extends AbstractExport {

    @Resource
    private CostsService costsService;

    @Resource
    private ProjectsService projectsService;


    @Override
    public String writeExcel(HttpServletRequest request, HSSFWorkbook wb, HSSFSheet sheet) {
        List<ProjectsDO> projectsList = projectsService.search(null).getResultList();
        if (projectsList == null) {
            projectsList = new ArrayList<>();
        }
        ProjectsDO projectsDO = new ProjectsDO();
        projectsDO.setName("合计");
        projectsDO.setId(0);
        projectsList.add(projectsDO);

        TableQueryParam param = genParam(request);
        String title = param.getTitleName() + "专项费用表";

        //TableKey: projectId表示projectId, type3表示months
        Map<TableKey, BigDecimal> costTable = costsService.getCostTableZhuanxiang(param);

        Set<Integer> allMonth = getZhuanxiangMonths(costTable);
        allMonth.add(0); //合计
        request.setAttribute("allMonth", allMonth);


        HSSFCellStyle cellStyleRight = ExcelUtil.getCellStyleRight(wb);
        HSSFCellStyle cellStyleCenter = ExcelUtil.getCellStyleCenter(wb);
        cellStyleCenter.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyleCenter.setFillPattern(CellStyle.SOLID_FOREGROUND);

        Row titleRow = sheet.getRow(1);
        genCell(titleRow, cellStyleRight, 0, "项目名称");
        int colIdx = 1;
        for (Integer month : allMonth) {
            genCell(titleRow, cellStyleRight, colIdx++, WebUtils.getMonthName(month));
        }

        int rowIdx = 2;
        for (ProjectsDO project : projectsList) {
            Row projectRow = sheet.createRow(rowIdx++);

            genCell(projectRow, cellStyleRight, 0, project.getName());
            int projectColIdx = 1;
            for (Integer month : allMonth) {
                genCell(projectRow, cellStyleRight, projectColIdx++, WebUtils.getZhuanxiangAmount(project.getId(), month, costTable));
            }
        }

        return title;
    }


}
