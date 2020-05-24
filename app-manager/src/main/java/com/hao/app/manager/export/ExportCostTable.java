package com.hao.app.manager.export;

import com.hao.app.commons.entity.param.TableQueryParam;
import com.hao.app.commons.entity.result.TableKey;
import com.hao.app.commons.utils.WebUtils;
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

@Service("exportCostTable")
public class ExportCostTable extends AbstractExport {

    @Resource
    private CostsService costsService;

    @Resource
    private IncomeService incomeService;

    @Resource
    private ProjectsService projectsService;

    @Override
    public String writeExcel(HttpServletRequest request, HSSFWorkbook wb, HSSFSheet sheet) {
        TableQueryParam param = genParam(request);

        String title = "所有综合费用表";
        String proName = "";
        ProjectsDO projectsDO = projectsService.getById(param.getProjectsId());
        if (projectsDO != null) {
            title = projectsDO.getName() + "费用表";
            proName = projectsDO.getName() + "，";
        }

        Map<TableKey, BigDecimal> incomeTable = incomeService.getIncomeTable2(param);
        Map<TableKey, BigDecimal> costTable = costsService.getCostTable2(param);
        List<Integer> allMonth = getAllMonths(incomeTable, costTable);
        allMonth.add(0); //合计


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
        Cell cell = getCell(titleRow, 0, title);

        int tmpIdx = 3;
        for (int month : allMonth) {
            titleRow.createCell(tmpIdx++).setCellValue(tmpIdx);
            titleRow.createCell(tmpIdx++).setCellValue(tmpIdx);
        }


        //设置月份
        Row queryRow = sheet.getRow(1);
        getCell(queryRow, 0, proName + "报表日期：" + param.getTitleName());


        //月份
        int startCol = 3;
        Row projectRow = sheet.getRow(2);
        for (int month : allMonth) {
            int from = startCol;
            genCell(projectRow, cellStyleCenter, startCol++, WebUtils.getMonthName(month));
            genCell(projectRow, cellStyleCenter, startCol++, "");

            sheet.addMergedRegion(new CellRangeAddress(2, 2, from, from + 1));
        }

        //收入
        startCol = 3;
        Row incomeRow = sheet.getRow(3);
        BigDecimal incomeTotal = BigDecimal.valueOf(0);
        for (int month : allMonth) {
            BigDecimal incomeAmount = getValue(incomeTable, new TableKey(month));
            incomeTotal = incomeTotal.add(incomeAmount);

            if (month == 0) {
                genCell(incomeRow, cellStyleRight2, startCol++, format(incomeTotal));
            } else {
                genCell(incomeRow, cellStyleRight2, startCol++, format(incomeAmount));
            }
            genCell(incomeRow, cellStyleRight2, startCol++, "占比");
        }


        int startRow = 4;
        List<Integer> gudingIds = Arrays.asList(12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 55);
        Map<Integer, BigDecimal> mountMap = new HashMap<>();
        for (int month : allMonth) {
            mountMap.put(month, BigDecimal.valueOf(0));
            mountMap.put(0, BigDecimal.valueOf(0));
        }

        for (int typeId : gudingIds) {
            startCol = 3;
            Row typeRow = sheet.getRow(startRow++);
            BigDecimal totolCost = BigDecimal.valueOf(0);
            BigDecimal totolIncome = BigDecimal.valueOf(0);
            for (int month : allMonth) {
                if (month == 0) {
                    genCell(typeRow, cellStyleRight, startCol++, format(totolCost));
                    genCell(typeRow, cellStyleRight, startCol++, getZhanbiStr(totolIncome, totolCost)); //占比

                    mountMap.put(month, mountMap.get(month).add(totolCost));
                } else {
                    BigDecimal incomeAmount = getValue(incomeTable, new TableKey(month));
                    BigDecimal costAmount = getValue(costTable, new TableKey(month, typeId));
                    totolIncome = totolIncome.add(incomeAmount);
                    totolCost = totolCost.add(costAmount);

                    genCell(typeRow, cellStyleRight, startCol++, format(costAmount));
                    genCell(typeRow, cellStyleRight, startCol++, getZhanbiStr(incomeAmount, costAmount)); //占比

                    mountMap.put(month, mountMap.get(month).add(costAmount));
                }

            }
        }

        //合计
        startCol = 3;
        Row total1 = sheet.getRow(startRow++);
        Row total2 = sheet.getRow(startRow++);
        for (int month : allMonth) {
            BigDecimal costA = mountMap.get(month);
            BigDecimal incomeA = month == 0 ? incomeTotal : getValue(incomeTable, new TableKey(month));

            int _i = startCol++;
            int _j = startCol++;
            genCell(total1, cellStyleRight2, _i, format(costA));
            genCell(total1, cellStyleRight2, _j, getZhanbiStr(incomeA, costA)); //占比

            genCell(total2, cellStyleRight2, _i, getZhanbiStr(incomeA, costA)); //占比
            genCell(total2, cellStyleRight2, _j, "");
        }


        //非固定
        List<Integer> zhipeiIds = new ArrayList<>();
        for (int i = 24; i <= 54; i++) {
            zhipeiIds.add(i);
        }

        Map<Integer, BigDecimal> mountMap2 = new HashMap<>();
        for (int month : allMonth) {
            mountMap2.put(month, BigDecimal.valueOf(0));
            mountMap2.put(0, BigDecimal.valueOf(0));
        }

        for (int typeId : zhipeiIds) {
            startCol = 3;
            Row typeRow = sheet.getRow(startRow++);
            BigDecimal totolCost = BigDecimal.valueOf(0);
            BigDecimal totolIncome = BigDecimal.valueOf(0);
            for (int month : allMonth) {
                if (month == 0) {
                    genCell(typeRow, cellStyleRight, startCol++, format(totolCost));
                    genCell(typeRow, cellStyleRight, startCol++, getZhanbiStr(totolIncome, totolCost)); //占比

                    mountMap2.put(month, mountMap2.get(month).add(totolCost));
                } else {
                    BigDecimal incomeAmount = getValue(incomeTable, new TableKey(month));
                    BigDecimal costAmount = getValue(costTable, new TableKey(month, typeId));
                    totolIncome = totolIncome.add(incomeAmount);
                    totolCost = totolCost.add(costAmount);

                    genCell(typeRow, cellStyleRight, startCol++, format(costAmount));
                    genCell(typeRow, cellStyleRight, startCol++, getZhanbiStr(incomeAmount, costAmount)); //占比

                    mountMap2.put(month, mountMap2.get(month).add(costAmount));
                }
            }
        }


        //合计
        startCol = 3;
        Row total3 = sheet.getRow(startRow++);
        Row total4 = sheet.getRow(startRow++);
        for (int month : allMonth) {
            BigDecimal costA = mountMap2.get(month);
            BigDecimal incomeA = month == 0 ? incomeTotal : getValue(incomeTable, new TableKey(month));

            int _i = startCol++;
            int _j = startCol++;
            genCell(total3, cellStyleRight2, _i, format(costA));
            genCell(total3, cellStyleRight2, _j, getZhanbiStr(incomeA, costA)); //占比

            genCell(total4, cellStyleRight2, _i, getZhanbiStr(incomeA, costA)); //占比
            genCell(total4, cellStyleRight2, _j, "");
        }


        //总合计
        startCol = 3;
        Row total5 = sheet.getRow(startRow++);
        Row total6 = sheet.getRow(startRow++);
        Row total7 = sheet.getRow(startRow++);
        for (int month : allMonth) {
            BigDecimal incomeA = month == 0 ? incomeTotal : getValue(incomeTable, new TableKey(month));
            BigDecimal costA = mountMap.get(month).add(mountMap2.get(month));

            int _i = startCol++;
            int _j = startCol++;

            genCell(total5, cellStyleRight3, _i, format(costA));
            genCell(total5, cellStyleRight3, _j, getZhanbiStr(incomeA, costA)); //占比

            genCell(total6, cellStyleRight3, _i, getZhanbiStr(incomeA, costA)); //占比
            genCell(total6, cellStyleRight3, _j, "");

            genCell(total7, cellStyleRight3, _i, getLirunStr(incomeA, costA));
            genCell(total7, cellStyleRight3, _j, "");
        }


        //标题合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2 + 2 * allMonth.size()));
        cell.getCellStyle().setAlignment(HSSFCellStyle.ALIGN_CENTER);

        return title;
    }

}
