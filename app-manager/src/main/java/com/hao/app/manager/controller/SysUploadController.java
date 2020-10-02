package com.hao.app.manager.controller;

import com.google.gson.JsonObject;
import com.hao.app.service.SysUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
		logger.info(file.getName());

	}

}
