package com.hao.app.manager.controller;

import com.hao.app.manager.export.ExportAssets;
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

    @Autowired
    private ExportAssets exportAssets;

    /**
     * 获取模板文件全路径地址
     *
     * @param request
     * @param fileName
     * @return
     */
    private String getModelFilePath(HttpServletRequest request, String fileName) {
        return getRealpath(request) + "/" + MODELDIR + "/" + fileName;
    }

    @RequestMapping("/exportDemo.do")
    public void exportDemo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String modelFile = getModelFilePath(request, "demo.xls");
        exportDemo.exportExcel(modelFile, request, response);
    }

    //导出资产
    @RequestMapping("/exportAssets.do")
    public void exportAssets(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String modelFile = getModelFilePath(request, "assets.xls");
        exportAssets.exportExcel(modelFile, request, response);
    }
}
