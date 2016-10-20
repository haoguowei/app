package com.hao.app.commons.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * 获取配置文件的配置
 *
 * @author yanwei
 * @since 1.0.0
 */
public class PropertiesUtil {
	
	private static Properties properties;
	
	static{
		try {
			properties = new Properties();
			properties.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			System.err.println("获取配置文件失败");
			e.printStackTrace();
		}
	}
	
	public static Properties getInstance(){
		return properties;
	}
	
	public static String getValue(String key){
		return properties.getProperty(key);
	}
	
}
