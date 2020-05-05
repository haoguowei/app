package com.hao.app.service.impl;

import com.google.gson.JsonObject;
import com.hao.app.commons.entity.Constants;
import com.hao.app.commons.utils.PropertiesUtils;
import com.hao.app.commons.utils.Utils;
import com.hao.app.service.SysUploadService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SysUploadServiceImpl implements SysUploadService {

	private Logger logger = LoggerFactory.getLogger(SysUploadServiceImpl.class);

	private static long MAXSIZE = 10 * 1024 * 1024;
	private static String FILETYPE = "jpg|png|jpeg";

	@Override
	public JsonObject writeFileToDisk(MultipartFile file) {
		boolean success = false;
		String msg = "";
		String data = "";

		if (!verifyFileType(file)) {
			msg = "请上传“" + FILETYPE + "”格式的文件！";
		} 
		else if (!verifyFileSize(file)) {
			msg = "上传的文件太大！";
		} 
		else {
			data = writeToDisk(file);
			if (StringUtils.isBlank(data)) {
				msg = "文件上传失败！";
			} else {
				success = true;
			}
		}

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("success", success);
		jsonObject.addProperty("msg", msg);
		jsonObject.addProperty("data", data);
		return jsonObject;
	}

	/**
	 * 文件保存
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @return 文件路径＋名称
	 */
	public String writeToDisk(MultipartFile file) {
		String filePath = Utils.getRandomPath(2, 64);
		String fileName = file.getOriginalFilename();
		fileName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
		
		String basePath = PropertiesUtils.getProperty(Constants.CONFIG_KEY_UPFILE_PATH);

		logger.info("====================================");
		logger.info("文件类型：" + file.getContentType());
		logger.info("文件大小：" + file.getSize());
		logger.info("文件路径：" + basePath + filePath);
		logger.info("文件名称：" + fileName);
		logger.info("====================================");

		try {
			file.transferTo(Utils.genDestFile(basePath + filePath, fileName));
			return filePath + fileName;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("文件保存失败！file：" + fileName);
		}
		return null;
	}

	/**
	 * 校验文件类型
	 * 
	 * @param file
	 * @return
	 */
	public boolean verifyFileType(MultipartFile file) {
		if (file == null) {
			return false;
		}
		Pattern reg = Pattern.compile("([^\\s]+(\\.(?i)(" + FILETYPE + "))$)");
		Matcher matcher = reg.matcher(file.getOriginalFilename());
		return matcher.find();
	}

	/**
	 * 校验文件大小
	 * 
	 * @param file
	 * @return
	 */
	public boolean verifyFileSize(MultipartFile file) {
		return verifyFileSize(file, MAXSIZE);
	}

	/**
	 * 校验文件大小
	 * 
	 * @param file
	 * @return
	 */
	public boolean verifyFileSize(MultipartFile file, long fileSize) {
		if (file == null) {
			return false;
		}
		return file.getSize() <= fileSize;
	}
}
