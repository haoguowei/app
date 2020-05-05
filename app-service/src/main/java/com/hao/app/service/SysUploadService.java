package com.hao.app.service;

import com.google.gson.JsonObject;
import org.springframework.web.multipart.MultipartFile;

public interface SysUploadService {

	/**
	 * 保存文件到磁盘
	 * @param file
	 * @return
	 */
	JsonObject writeFileToDisk(MultipartFile file);

}
