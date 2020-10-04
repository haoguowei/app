package com.hao.app.manager.controller;

import com.google.gson.JsonObject;
import com.hao.app.commons.entity.Dicts;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.commons.utils.IdCardUtils;
import com.hao.app.pojo.EmployeeDO;
import com.hao.app.pojo.ProjectsDO;
import com.hao.app.service.EmployeeService;
import com.hao.app.service.ProjectsService;
import com.hao.app.service.SysUploadService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource
    private ProjectsService projectsService;


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

            Map<String, Integer> projectMap = getProjects();

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
                if (StringUtils.isBlank(getValue(row, 0))) {
                    break;
                }

                total += 1;
                try {
                    String idCard = getValue(row, 2);
                    if (StringUtils.isBlank(idCard)) {
                        err += 1;
                        continue;
                    }

                    //是否已存在
                    EmployeeDO edo = employeeService.getByCard(idCard);
                    if (edo != null) {
                        logger.info("已存在：" + edo);
                    } else {
                        saveItem(row, projectMap);
                    }
                    success += 1;
                } catch (Exception e) {
                    err += 1;
                    logger.error("=====> ", e);
                }
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
    }

    private Map<String, Integer> getProjects() {
        Map<String, Integer> map = new HashMap<>();
        List<ProjectsDO> projectsList = projectsService.search(null).getResultList();
        if (CollectionUtils.isEmpty(projectsList)) {
            return map;
        }

        for (ProjectsDO p : projectsList) {
            map.put(p.getName(), p.getId());
        }
        return map;
    }

    public void saveItem(Row row, Map<String, Integer> projectMap) throws ParseException {
        String name = getValue(row, 0);
        String phone = getValue(row, 1);
        String idCard = getValue(row, 2);
        String projectsName = getValue(row, 3);

        //职位 employeeJobTypeMap
        String jobTypeStr = getValue(row, 4);
        //民族 minzuMap
        String minzuStr = getValue(row, 5);
        //入职日期
        String ruzhi = getValue(row, 6);
        //离职日期
        String lizhi = getValue(row, 7);

        //学历
        String xueli = getValue(row, 8);
        //户口类型
        String hukou = getValue(row, 9);

        //户籍地址
        String hukouAddr = getValue(row, 10);
        //现住地址
        String addr = getValue(row, 11);

        //紧急联系人
        String jinjiLianxiren = getValue(row, 12);
        //紧急联系人电话
        String jinjinPhone = getValue(row, 13);

        //有无入职合同
        String hetong = getValue(row, 14);
        //保险类型
        String baoxian = getValue(row, 15);


        EmployeeDO item = new EmployeeDO();
        item.setName(name);
        item.setPhone(phone);
        item.setIdCard(idCard);

        item.setProjects(projectMap.get(projectsName));
        item.setProjectsName(projectsName);

        item.setJobType(Dicts.getKey(Dicts.employeeJobTypeMap, jobTypeStr));
        item.setJobTypeStr(jobTypeStr);

        //民族
        item.setEthnic(Dicts.getKey(Dicts.minzuMap, minzuStr));

        item.setEntryDate(getDate(ruzhi));
        item.setLeaveDate(getDate(lizhi));
        if (item.getLeaveDate() == null) {
            item.setStatus(0);
        } else {
            item.setStatus(1);
        }

        item.setEduType(Dicts.getKey(Dicts.xueliMap, xueli));
        item.setHukouType(Dicts.getKey(Dicts.hukouTypeMap, hukou));
        item.setHetong(hetong(hetong));

        item.setHujiAddress(hukouAddr);
        item.setAddress(addr);

        item.setEmergencyContact(jinjiLianxiren);
        item.setEmergencyContactPhone(jinjinPhone);

        //保险类型
        item.setSafeType(baoxian);

        item.setRemark("批量导入");
        item.setCreater("导入");
        item.setCreateTime(new Date());
        item.setUpdateTime(new Date());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, String> map = IdCardUtils.getBirAgeSex(item.getIdCard());
        if (map != null) {
            if (StringUtils.isNotBlank(map.get("birthday"))) {
                item.setBirthDay(format.parse(map.get("birthday")));
            }
        }

        ResultCodeEnum res = employeeService.insert(item);
        logger.info("导入员工信息：{}, result={}", item, res);
    }

    //0-否，1-是
    private Integer hetong(String hetong) {
        if (StringUtils.isBlank(hetong)) {
            return 0;
        }

        return hetong.equals("有") ? 1 : 0;
    }

    public String getValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex);
        if (cell == null) {
            return "";
        }
        int cellType = cell.getCellType();
        if (cellType == Cell.CELL_TYPE_STRING) {
            return cell.getStringCellValue();
        } else {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                if (cell.getDateCellValue() == null) {
                    return "";
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return dateFormat.format(cell.getDateCellValue());
            }
            return BigDecimal.valueOf(cell.getNumericCellValue()).toPlainString();
        }
    }

    public Date getDate(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
