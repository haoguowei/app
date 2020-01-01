package com.hao.app.manager.export;

import com.hao.app.commons.utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件导出
 * 
 * @author haoguowei
 *
 */
public abstract class AbstractExport {

	private Logger logger = LoggerFactory.getLogger(AbstractExport.class);

	/**
	 * 写excel文件内容
	 *
	 * @param request
	 * @param wb
	 * @param sheet
	 * @return
	 */
	public abstract String writeExcel(HttpServletRequest request, HSSFWorkbook wb, HSSFSheet sheet);

	protected static Cell genCell(Row row, HSSFCellStyle style, int index, BigDecimal val) {
		Cell cell = genCell(row, style, index);
		cell.setCellValue(val.doubleValue());
		return cell;
	}

	protected static Cell genCell(Row row, int index) {
		Cell cell = row.createCell(index);
		return cell;
	}

	protected static Cell genCell(Row row, HSSFCellStyle style, int index) {
		Cell cell = row.createCell(index);
		cell.setCellStyle(style);
		return cell;
	}

	protected static Cell genCell(Row row, HSSFCellStyle style, int index, String val) {
		Cell cell = genCell(row, style, index);
		cell.setCellValue(val);
		return cell;
	}

	protected static Cell genCell(Row row, HSSFCellStyle style, int index, double val) {
		Cell cell = genCell(row, style, index);
		cell.setCellValue(val);
		return cell;
	}

	/**
	 * 导出Excel
	 *
	 * @param modelFile 模板文件，为空时创建新文件
	 * @param request
	 * @param response
	 */
	public void exportExcel(String modelFile, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出Excel,modelFile={}", modelFile);
		ServletOutputStream os = null;
		try {
			HSSFWorkbook wb;
			HSSFSheet sheet;
			if (StringUtils.isBlank(modelFile)) {
				wb = new HSSFWorkbook();
				sheet = wb.createSheet();
			} else {
				wb = new HSSFWorkbook(new FileInputStream(modelFile));
				sheet = wb.getSheetAt(0);
			}

			short rowHeight = 500;
			sheet.setDefaultRowHeight(rowHeight);
			String fileName = writeExcel(request, wb, sheet);
			fileName = Utils.transcodingStr(fileName) + ".xls";

			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=" + fileName);
			logger.info("导出Excel,modelFile={},fileName={}", modelFile, fileName);
			os = response.getOutputStream();
			wb.write(os);
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected static Cell genCell(Row row, HSSFCellStyle style, int index, int val) {
		Cell cell = genCell(row, style, index);
		cell.setCellValue(val);
		return cell;
	}

	protected Row genRow(HSSFSheet sheet, int idx) {
		short rowHeight = 500;
		Row row = sheet.createRow(idx);
		row.setHeight(rowHeight);
		return row;
	}

	protected static String fmtDate(Date date) {
		return fmtDate(date, "yyyy-MM-dd");
	}

	protected static String fmtDate(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}


}
