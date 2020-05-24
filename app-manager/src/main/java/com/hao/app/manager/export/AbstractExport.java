package com.hao.app.manager.export;

import com.hao.app.commons.entity.param.TableQueryParam;
import com.hao.app.commons.entity.result.TableKey;
import com.hao.app.commons.utils.DateUtil;
import com.hao.app.commons.utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
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
import java.util.*;

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

	protected static Cell getCell(Row row, int index, String val) {
		Cell cell = row.getCell(index);
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


	public Set<Integer> getZhuanxiangMonths(Map<TableKey, BigDecimal> costTable) {
		List<Integer> allMonth = new ArrayList<>();
		for (TableKey key : costTable.keySet()) {
			allMonth.add(key.getType3());
		}

		Collections.sort(allMonth);

		Set<Integer> set = new LinkedHashSet<>();
		for (Integer i : allMonth) {
			set.add(i);
		}
		return set;
	}

	public TableQueryParam genParam(HttpServletRequest request) {
		int first = NumberUtils.toInt(request.getParameter("first"), 0);
		int projectsId = NumberUtils.toInt(request.getParameter("projectsId"), 0);
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");

		int type1 = NumberUtils.toInt(request.getParameter("type1"), 0);
		int type2 = NumberUtils.toInt(request.getParameter("type2"), 0);
		int type3 = NumberUtils.toInt(request.getParameter("type3"), 0);

		if (first == 0) {
			int year = DateUtil.getYear();
			fromDate = year + "-01-01";
			toDate = year + "-12-31";
		}

		TableQueryParam param = new TableQueryParam();
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

		param.setEnterDateStart(fromDate);
		param.setEnterDateEnd(toDate);
		return param;
	}


	public static String getLirunStr(BigDecimal income, BigDecimal cost) {
		if (income.doubleValue() == 0D) {
			return "";
		}

		BigDecimal zhanbi = cost.multiply(BigDecimal.valueOf(100.0)).divide(income, 2, BigDecimal.ROUND_HALF_UP);
		BigDecimal val = BigDecimal.valueOf(100).subtract(zhanbi);
		return format2(val);
	}

	public static String getZhanbiStr(BigDecimal income, BigDecimal cost) {
		if (income.doubleValue() == 0D) {
			return "";
		}

		BigDecimal zhanbi = cost.multiply(BigDecimal.valueOf(100.0)).divide(income, 2, BigDecimal.ROUND_HALF_UP);
		return format2(zhanbi);
	}

	public static BigDecimal getValue(Map<TableKey, BigDecimal> map, TableKey key) {
		BigDecimal val = map.get(key);
		if (val == null) {
			return BigDecimal.valueOf(0);
		}
		return val;
	}

	public static BigDecimal format(BigDecimal v) {
		if (v == null) {
			return BigDecimal.valueOf(0);
		}
		return v.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public static String format2(BigDecimal v) {
		if (v == null) {
			return BigDecimal.valueOf(0).toString() + "%";
		}
		return v.setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "%";
	}

	public List<Integer> getAllMonths(Map<TableKey, BigDecimal> incomeTable, Map<TableKey, BigDecimal> costTable) {
		List<Integer> allMonth = new ArrayList<>();
		for (TableKey key : incomeTable.keySet()) {
			allMonth.add(key.getProjectId());
		}
		for (TableKey key : costTable.keySet()) {
			allMonth.add(key.getProjectId());
		}

		Collections.sort(allMonth);

		Set<Integer> set = new LinkedHashSet<>();
		for (Integer i : allMonth) {
			set.add(i);
		}

		List<Integer> ls = new ArrayList<>(set.size());
		for (int x : set) {
			ls.add(x);
		}
		return ls;
	}
}
