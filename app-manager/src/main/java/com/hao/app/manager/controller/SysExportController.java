package com.hao.app.manager.controller;

import com.hao.app.manager.export.ExportDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 导出打印文件
 *
 * @author haoguowei
 */
@Controller
@RequestMapping
public class SysExportController extends BaseController {

    @Autowired
    private ExportDemo exportDemo;


    @RequestMapping("/exportDemo.do")
    public void exportDemo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String modelFile = getModelFilePath(request, "demo.xls");
        exportDemo.exportExcel(modelFile, request, response);
    }


}
