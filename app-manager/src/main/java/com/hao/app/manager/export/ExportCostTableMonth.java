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
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

        String title = "所有项目月度费用表";
        ProjectsDO projectsDO = projectsService.getById(param.getProjectsId());
        if (projectsDO != null) {
            title = projectsDO.getName() + "月度费用表";
        }

        //修改标题
        Row titleRow = sheet.getRow(0);
        Cell titleCell = getCell(titleRow, 0, title);

        //设置月份
        Row queryRow = sheet.getRow(1);
        Cell queryCell = getCell(queryRow, 0, "报表月份：" + param.getTitleName());

        List<ProjectsDO> projectsList = projectsService.search(null).getResultList();
        if (projectsList == null) {
            return title;
        }

        Map<TableKey, BigDecimal> incomeTable = incomeService.getIncomeTable(param);
        Map<TableKey, BigDecimal> costTable = costsService.getCostTable(param);

        HSSFCellStyle cellStyleCenter = ExcelUtil.getCellStyleCenter(wb);
        HSSFCellStyle cellStyleLeft = ExcelUtil.getCellStyleLeft(wb);
        HSSFCellStyle cellStyleRight = ExcelUtil.getCellStyleRight(wb);

        int numb = 0; //序号
        int baseColIndex = 2;

        //为每一行赋值
        if (projectsList == null) {
            return title;
        }

        for (ProjectsDO project : projectsList) {

        }


//        for (AssetsDO assetsDO : list) {
//            Row row = genRow(sheet, baseRowIndex);
//
//            //为每一列赋值
//            genCell(row, cellStyleCenter, 0, numb += 1);
//            genCell(row, cellStyleCenter, 1, fmtDate(assetsDO.getBuyTime()));
//            genCell(row, cellStyleLeft, 2, "车辆");
//            genCell(row, cellStyleLeft, 3, assetsDO.getName());
//            genCell(row, cellStyleLeft, 4, assetsDO.getNumber());
//
//            genCell(row, cellStyleLeft, 5, assetsDO.getCarType());
//            genCell(row, cellStyleLeft, 6, assetsDO.getLicense());
//            genCell(row, cellStyleLeft, 7, assetsDO.getBrand());
//
//            genCell(row, cellStyleRight, 8, assetsDO.getPrice()); //单价
//            genCell(row, cellStyleRight, 9, assetsDO.getPurTax());//购置税
//            genCell(row, cellStyleRight, 10, assetsDO.getQuantity());//数量
//
//            BigDecimal zz = (assetsDO.getPrice().add(assetsDO.getPurTax())).multiply(BigDecimal.valueOf(assetsDO.getQuantity()));
//            genCell(row, cellStyleRight, 11, zz); //总值=（单价+购置税）* 数量
//            genCell(row, cellStyleRight, 12, assetsDO.getStaging()); //分摊
//
//            BigDecimal je = Objects.equals(assetsDO.getStaging(), 0) ? BigDecimal.valueOf(0) : zz.divide(BigDecimal.valueOf(assetsDO.getStaging()));
//            genCell(row, cellStyleRight, 13, je); //金额=总值/分摊
//
//            genCell(row, cellStyleLeft, 14, assetsDO.getRemark());
//        }

        return title;
    }

}
