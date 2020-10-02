package com.hao.app.manager.controller;

import com.google.gson.JsonObject;
import com.hao.app.service.EmployeeService;
import com.hao.app.service.SysUploadService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

/**
 * 文件上传
 *
 * @author haoguowei
 */
@Controller
@RequestMapping
public class SysUploadController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(SysUploadController.class);

    @Autowired
    private SysUploadService sysUploadService;

    @Resource
    private EmployeeService employeeService;

    public static Workbook getWorkbook(InputStream inputStream, String fileType) throws IOException {
        Workbook workbook = null;
        if (fileType.equalsIgnoreCase("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileType.equalsIgnoreCase("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        }
        return workbook;
    }

    /**
     * 上传文件
     *
     * @param file
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/fileUpload.do")
    public void fileUpload(@RequestParam(value = "file", required = false) MultipartFile file,
                           HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject jsonObject = sysUploadService.writeFileToDisk(file);
        logger.info("文件上传结果：{}", jsonObject);
        response.getWriter().write(jsonObject.toString());
    }

    /**
     * 上传文件
     *
     * @param file
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/fileUploadV2.do")
    public void fileUploadV2(@RequestParam(value = "file", required = false) MultipartFile file,
                             HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String fileName = file.getOriginalFilename();
            logger.info("接收到员工导入文件：" + file.getName());

            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            Workbook workbook = getWorkbook(file.getInputStream(), fileType);

            if (workbook == null) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("success", false);
                jsonObject.addProperty("msg", "导入文件错误");
                jsonObject.addProperty("data", "导入文件错误");
                response.getWriter().write(jsonObject.toString());
                return;
            }

            int rowIndex = 1;
            Sheet sheet = workbook.getSheetAt(0);

            int total = 0;
            int success = 0;
            int err = 0;

            while (true) {
                Row row = sheet.getRow(rowIndex++);
                if (row == null) {
                    break;
                }

                //16列
                String name = getValue(row, 0);
                if (StringUtils.isBlank(name)) {
                    break;
                }

                String phone = getValue(row, 1);
                String idCard = getValue(row, 2);
                String projectsName = getValue(row, 3);

                //职位 employeeJobTypeMap
                String jobTypeStr = getValue(row, 4);
                //民族 minzuMap
                String minzuStr = getValue(row, 5);

            }

            JsonObject jsonObject = new JsonObject();
            String msg = "员工信息导入成功！共读取记录数: " + total + ", 成功: " + success + ", 失败: " + err;
            jsonObject.addProperty("success", true);
            jsonObject.addProperty("msg", msg);
            jsonObject.addProperty("data", msg);
            response.getWriter().write(jsonObject.toString());
        } catch (Exception e) {
            logger.error("员工信息导入失败！", e);
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("success", false);
            jsonObject.addProperty("msg", "员工信息导入失败");
            jsonObject.addProperty("data", "员工信息导入失败");
            response.getWriter().write(jsonObject.toString());
        }

//        EmployeeDO item = new EmployeeDO();
//        item.setStatus();
//        item.setName();
//
//        item.setProjects();
//        item.setProjectsName();
//
//        item.setPhone();
//        item.setIdCard();
//
//        item.setJobType();
//        item.setJobTypeStr();
//        item.setGenderStr();
//
//        item.setEthnic();
//        item.setAge();
//
//        item.setEntryDate();
//        item.setLeaveDate();
//        item.setBirthDay();
//        item.setBirthDate();
//
//        item.setEduType();
//        item.setHukouType();
//
//        item.setHujiAddress();
//        item.setAddress();
//
//        item.setEmergencyContact();
//        item.setEmergencyContactPhone();
//
//        item.setSafeType();
//
//        item.setRemark("批量导入");
//        item.setCreateTime(new Date());
//        item.setUpdateTime(new Date());
//
//        item.setHetong();
//        item.setHetongStr();
//        item.setShenqing();
//        item.setDescr();
//
//        ResultCodeEnum res = employeeService.insert(item);
//        logger.info("导入员工信息：{}, result={}", item, res);
    }

    public String getValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return cell.getStringCellValue();
        } else {
            return BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
        }
    }

}
