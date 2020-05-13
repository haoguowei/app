package com.hao.app.manager.export;

import com.hao.app.pojo.AssetsDO;
import com.hao.app.service.CostsService;
import com.hao.app.service.IncomeService;
import com.hao.app.service.ProjectsService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("exportCostTable")
public class ExportCostTable extends AbstractExport {

    @Resource
    private CostsService costsService;


    @Resource
    private IncomeService incomeService;


    @Resource
    private ProjectsService projectsService;


    private List<AssetsDO> searchData(HttpServletRequest request) {
        return null;
    }

    @Override
    public String writeExcel(HttpServletRequest request, HSSFWorkbook wb, HSSFSheet sheet) {
        List<AssetsDO> list = searchData(request);
        String title = "项目费用表";

        HSSFCellStyle cellStyleCenter = ExcelUtil.getCellStyleCenter(wb);
        HSSFCellStyle cellStyleLeft = ExcelUtil.getCellStyleLeft(wb);
        HSSFCellStyle cellStyleRight = ExcelUtil.getCellStyleRight(wb);

        int numb = 0; //序号
        int baseRowIndex = 2;

        //为每一行赋值
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
