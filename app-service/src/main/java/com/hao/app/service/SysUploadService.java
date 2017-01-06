package com.hao.app.service;

import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

public interface SysUploadService {

	/**
	 * 保存文件到磁盘
	 * @param file
	 * @return
	 */
	JsonObject writeFileToDisk(MultipartFile file);

}
