package com.hao.app.manager.export;

import com.hao.app.commons.entity.param.TableQueryParam;
import com.hao.app.commons.entity.result.TableKey;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.service.CostsService;
import com.hao.app.service.IncomeService;
import com.hao.app.service.ProjectsService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Service("exportCostTableMonth")
public class ExportCostTableMonth extends AbstractExport {

    @Resource
    private CostsService costsService;

    @Resource
    private IncomeService incomeService;

    @Resource
    private ProjectsService projectsService;


    @Override
    public String writeExcel(HttpServletRequest request, HSSFWorkbook wb, HSSFSheet sheet) {
        TableQueryParam param = genParam(request);

        String title = "所有项目部门费用表";
        ProjectsDO projectsDO = projectsService.getById(param.getProjectsId());
        if (projectsDO != null) {
            title = projectsDO.getName() + "部门费用表";
        }

        List<ProjectsDO> projectsList = projectsService.search(null).getResultList();
        if (projectsList == null) {
            return title;
        }

        HSSFCellStyle cellStyleRight = ExcelUtil.getCellStyleRight(wb);

        HSSFCellStyle cellStyleRight2 = ExcelUtil.getCellStyleRight(wb);
        cellStyleRight2.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyleRight2.setFillPattern(CellStyle.SOLID_FOREGROUND);

        HSSFCellStyle cellStyleRight3 = ExcelUtil.getCellStyleRight(wb);
        cellStyleRight3.setFillForegroundColor(IndexedColors.CORNFLOWER_BLUE.getIndex());
        cellStyleRight3.setFillPattern(CellStyle.SOLID_FOREGROUND);

        HSSFCellStyle cellStyleCenter = ExcelUtil.getCellStyleCenter(wb);
        cellStyleCenter.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyleCenter.setFillPattern(CellStyle.SOLID_FOREGROUND);

        //修改标题
        Row titleRow = sheet.getRow(0);
        Cell cell = null;
        for (int i = 0; i < projectsList.size(); i++) {
            if (i == 0) {
                cell = getCell(titleRow, 0, title);
            }
            if (i >= 3) {
                titleRow.createCell(i);
            }

        }
        //标题合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3 + projectsList.size()));
        cell.getCellStyle().setAlignment(HSSFCellStyle.ALIGN_CENTER);

        //设置月份
        Row queryRow = sheet.getRow(1);
        getCell(queryRow, 0, "报表月份：" + param.getTitleName());


        Map<TableKey, BigDecimal> incomeTable = incomeService.getIncomeTable(param);
        Map<TableKey, BigDecimal> costTable = costsService.getCostTable(param);
        int startCol = 3;

        //项目名称
        Row projectRow = sheet.getRow(2);
        for (int i = 0; i < projectsList.size(); i++) {
            ProjectsDO project = projectsList.get(i);
            genCell(projectRow, cellStyleCenter, startCol + i, project.getName());
        }
        genCell(projectRow, cellStyleCenter, startCol + projectsList.size(), "合计");

        //收入
        Row incomeRow = sheet.getRow(3);
        BigDecimal incomeTotal = BigDecimal.valueOf(0);
        for (int i = 0; i < projectsList.size(); i++) {
            ProjectsDO project = projectsList.get(i);
            BigDecimal incomeAmount = getValue(incomeTable, new TableKey(project.getId()));
            genCell(incomeRow, cellStyleRight2, startCol + i, format(incomeAmount));
            incomeTotal = incomeTotal.add(incomeAmount);
        }
        genCell(incomeRow, cellStyleRight2, startCol + projectsList.size(), format(incomeTotal));

        //固定费用
        int starRow = 4;
        List<Integer> gudingIds = Arrays.asList(12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
        //项目id：项目合计
        Map<Integer, BigDecimal> gudingTotal = new HashMap<>(projectsList.size());
        for (int r : gudingIds) {
            Row itemRow = sheet.getRow(starRow++);
            BigDecimal itemTotal = BigDecimal.valueOf(0);
            for (int i = 0; i < projectsList.size(); i++) {
                ProjectsDO project = projectsList.get(i);
                BigDecimal costAmount = getValue(costTable, new TableKey(project.getId(), r));
                genCell(itemRow, cellStyleRight, startCol + i, format(costAmount));
                itemTotal = itemTotal.add(costAmount);

                //每一个项目的所有类型合计
                if (gudingTotal.get(project.getId()) == null) {
                    gudingTotal.put(project.getId(), costAmount);
                } else {
                    gudingTotal.put(project.getId(), gudingTotal.get(project.getId()).add(costAmount));
                }
            }
            genCell(itemRow, cellStyleRight, startCol + projectsList.size(), format(itemTotal));
        }

        //固定费用合计/占比
        Row gudingHejiRow = sheet.getRow(starRow++);
        Row gudingZhanbiRow = sheet.getRow(starRow++);
        BigDecimal gudingHejiTotal = BigDecimal.valueOf(0);
        for (int i = 0; i < projectsList.size(); i++) {
            ProjectsDO project = projectsList.get(i);
            BigDecimal cost = gudingTotal.get(project.getId());
            genCell(gudingHejiRow, cellStyleRight2, startCol + i, format(cost));

            BigDecimal incomeAmount = getValue(incomeTable, new TableKey(project.getId()));
            genCell(gudingZhanbiRow, cellStyleRight2, startCol + i, format2(getZhanbi(incomeAmount, cost)));

            gudingHejiTotal = gudingHejiTotal.add(cost);
        }
        genCell(gudingHejiRow, cellStyleRight2, startCol + projectsList.size(), format(gudingHejiTotal));
        genCell(gudingZhanbiRow, cellStyleRight2, startCol + projectsList.size(), format2(getZhanbi(incomeTotal, gudingHejiTotal)));


        //非固定
        List<Integer> zhipeiIds = new ArrayList<>();
        for (int i = 24; i <= 54; i++) {
            zhipeiIds.add(i);
        }

        //项目id：项目合计
        Map<Integer, BigDecimal> zhipeiTotal = new HashMap<>(projectsList.size());
        for (int r : zhipeiIds) {
            Row itemRow = sheet.getRow(starRow++);
            BigDecimal itemTotal = BigDecimal.valueOf(0);
            for (int i = 0; i < projectsList.size(); i++) {
                ProjectsDO project = projectsList.get(i);
                BigDecimal costAmount = getValue(costTable, new TableKey(project.getId(), r));
                genCell(itemRow, cellStyleRight, startCol + i, format(costAmount));
                itemTotal = itemTotal.add(costAmount);

                //每一个项目的所有类型合计
                if (zhipeiTotal.get(project.getId()) == null) {
                    zhipeiTotal.put(project.getId(), costAmount);
                } else {
                    zhipeiTotal.put(project.getId(), zhipeiTotal.get(project.getId()).add(costAmount));
                }
            }
            genCell(itemRow, cellStyleRight, startCol + projectsList.size(), format(itemTotal));
        }

        //费用合计/占比
        Row zhipeiHejiRow = sheet.getRow(starRow++);
        Row zhipeiZhanbiRow = sheet.getRow(starRow++);
        BigDecimal zhipeiHejiTotal = BigDecimal.valueOf(0);
        for (int i = 0; i < projectsList.size(); i++) {
            ProjectsDO project = projectsList.get(i);
            BigDecimal cost = zhipeiTotal.get(project.getId());
            genCell(zhipeiHejiRow, cellStyleRight2, startCol + i, format(cost));

            BigDecimal incomeAmount = getValue(incomeTable, new TableKey(project.getId()));
            genCell(zhipeiZhanbiRow, cellStyleRight2, startCol + i, format2(getZhanbi(incomeAmount, cost)));

            zhipeiHejiTotal = zhipeiHejiTotal.add(cost);
        }
        genCell(zhipeiHejiRow, cellStyleRight2, startCol + projectsList.size(), format(zhipeiHejiTotal));
        genCell(zhipeiZhanbiRow, cellStyleRight2, startCol + projectsList.size(), format2(getZhanbi(incomeTotal, zhipeiHejiTotal)));


        //总合计
        Row totalRow = sheet.getRow(starRow++);
        //总占比
        Row zhanbiRow = sheet.getRow(starRow++);
        //利润率
        Row lirunRow = sheet.getRow(starRow++);
        for (int i = 0; i < projectsList.size(); i++) {
            ProjectsDO project = projectsList.get(i);
            BigDecimal incomeAmount = getValue(incomeTable, new TableKey(project.getId()));

            BigDecimal tmp = zhipeiTotal.get(project.getId()).add(gudingTotal.get(project.getId()));

            BigDecimal zb = getZhanbi(incomeAmount, tmp);
            genCell(totalRow, cellStyleRight3, startCol + i, format(tmp));
            genCell(zhanbiRow, cellStyleRight3, startCol + i, format2(zb));
            genCell(lirunRow, cellStyleRight3, startCol + i, format2(BigDecimal.valueOf(100).subtract(zb)));
        }

        BigDecimal tmpt = zhipeiHejiTotal.add(gudingHejiTotal);
        BigDecimal zbt = getZhanbi(incomeTotal, tmpt);
        genCell(totalRow, cellStyleRight3, startCol + projectsList.size(), format(tmpt));
        genCell(zhanbiRow, cellStyleRight3, startCol + projectsList.size(), format2(zbt));
        genCell(lirunRow, cellStyleRight3, startCol + projectsList.size(), format2(BigDecimal.valueOf(100).subtract(zbt)));

        return title;
    }


}
