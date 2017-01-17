package com.hao.app.manager.export;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

@Service("exportDemo")
public class ExportDemo extends AbstractExport {

	@Override
	public String writeExcel(HttpServletRequest request, HSSFWorkbook wb, HSSFSheet sheet) {
		return "导出DEMO表";
	}

}
