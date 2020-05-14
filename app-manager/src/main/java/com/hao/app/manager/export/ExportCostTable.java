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
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

        String title = "所有项目费用表";
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
        HSSFCellStyle cellStyleCenter = ExcelUtil.getCellStyleCenter(wb);

        //修改标题
        Row titleRow = sheet.getRow(0);
        Cell cell = getCell(titleRow, 0, title);

        int tmpIdx = 3;
        for (int month : allMonth) {
            titleRow.createCell(tmpIdx++);
            titleRow.createCell(tmpIdx++);
        }

        //标题合并单元格
//        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, tmpIdx - 2));
        cell.getCellStyle().setAlignment(HSSFCellStyle.ALIGN_CENTER);

        //设置月份
        Row queryRow = sheet.getRow(1);
        getCell(queryRow, 0, proName + "报表月份：" + param.getTitleName());


        //月份
        int startCol = 3;
        Row projectRow = sheet.getRow(2);
        for (int month : allMonth) {
            genCell(projectRow, cellStyleCenter, startCol++, WebUtils.getMonthName(month));
            genCell(projectRow, cellStyleCenter, startCol++, "");
        }

        //收入
        startCol = 3;
        Row incomeRow = sheet.getRow(3);
        BigDecimal incomeTotal = BigDecimal.valueOf(0);
        for (int month : allMonth) {
            BigDecimal incomeAmount = getValue(incomeTable, new TableKey(month));
            incomeTotal = incomeTotal.add(incomeAmount);

            if (month == 0) {
                genCell(incomeRow, cellStyleRight, startCol++, format(incomeTotal));
            } else {
                genCell(incomeRow, cellStyleRight, startCol++, format(incomeAmount));
            }
            genCell(incomeRow, cellStyleRight, startCol++, "占比");
        }

        int startRow = 4;
        List<Integer> gudingIds = Arrays.asList(12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23);
        for (int typeId : gudingIds) {
            startCol = 3;
            Row typeRow = sheet.getRow(startRow++);
            for (int month : allMonth) {
                BigDecimal costAmount = getValue(costTable, new TableKey(month, typeId));
                BigDecimal incomeAmount = getValue(incomeTable, new TableKey(month));

                genCell(typeRow, cellStyleRight, startCol++, format(costAmount));
                genCell(typeRow, cellStyleRight, startCol++, format2(getZhanbi(costAmount, incomeAmount))); //占比
            }
        }


        return title;
    }

}
