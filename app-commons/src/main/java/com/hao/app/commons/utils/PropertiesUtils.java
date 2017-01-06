package com.hao.app.commons.utils;

import java.util.Properties;

public class PropertiesUtils {

	private static Properties properties;

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

	public static Properties getProperties() {
		return properties;
	}

	public static void setProperties(Properties properties) {
		PropertiesUtils.properties = properties;
	}
}
