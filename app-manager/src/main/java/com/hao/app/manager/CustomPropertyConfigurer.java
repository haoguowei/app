package com.hao.app.manager;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 自定义获取配置文件
 * 
 * @author haoguowei
 *
 */
public class CustomPropertyConfigurer extends PropertyPlaceholderConfigurer {

	private static Properties sroperties;

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		sroperties = props;
	}

	public static Object getProperty(String key) {
		return sroperties.getProperty(key);
	}
}
