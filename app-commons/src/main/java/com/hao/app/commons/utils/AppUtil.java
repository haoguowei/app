package com.hao.app.commons.utils;

import java.io.File;
import java.util.Collection;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

/**
 * 常用工具方法
 *
 */
public class AppUtil {
	
	/**
	 * 生成随机路径
	 * 
	 * @param length
	 * @param max
	 * @return
	 */
	public static String getRandomPath(int length, int max) {
		StringBuilder bd = new StringBuilder("/");
		Random r = new Random();
		for (int i = 0; i < length; i++) {
			bd.append(r.nextInt(max) + "/");
		}
		return bd.toString();
	}
	
	/**
	 * 创建文件夹，返回目标文件
	 * 
	 * @param path
	 * @param name
	 * @return
	 */
	public static File genDestFile(String path, String name) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		if (StringUtils.isBlank(name)) {
			return null;
		}
		return new File(path + name);
	}
	
	/**
	 * 集合转成字符串
	 * 
	 * @param <T>
	 * @param Col 集合
	 * @param Separator 分隔符
	 * @return
	 */
	public static <T> String collectionStrToStr(Collection<T> col, String Separator) {
		if (col == null) {
			return "";
		}

		int i = 0;
		StringBuffer sbr = new StringBuffer();
		for (T t : col) {
			if (i != 0) {
				sbr.append(Separator);
			}
			sbr.append(t);
			i += 1;
		}
		return sbr.toString();
	}
}
