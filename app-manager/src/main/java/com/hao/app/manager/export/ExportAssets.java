package com.hao.app.manager.export;

import com.hao.app.commons.entity.param.AssetsQueryParam;
import com.hao.app.pojo.AssetsDO;
import com.hao.app.service.AssetsService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("exportAssets")
public class ExportAssets extends AbstractExport {

    @Resource
    private AssetsService assetsService;

    @Override
    public String writeExcel(HttpServletRequest request, HSSFWorkbook wb, HSSFSheet sheet) {
        AssetsQueryParam param = new AssetsQueryParam(0, 100);
        List<AssetsDO> list = assetsService.searchAssets(param).getResultList();

        String title = "阜平资产核对表";
        HSSFCellStyle cellStyle = ExcelUtil.getCellStyleKaoqin(wb);

        // 设置列宽度
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);

        sheet.setDefaultRowHeight((short) 400);

        int totalColumns = 15;
        int baseRowIndex = 2;
        int numb = 0;

        //为每一行赋值
        for (AssetsDO assetsDO : list) {
            Row row = sheet.createRow(baseRowIndex);

            //为每一列赋值
            genCell(row, cellStyle, 0, numb += 1);
            genCell(row, cellStyle, 1, fmtDate(assetsDO.getBuyTime()));
            genCell(row, cellStyle, 2, assetsDO.getType());
            genCell(row, cellStyle, 3, assetsDO.getName());
            genCell(row, cellStyle, 4, assetsDO.getNumber());
        }

        return title;
    }

}
