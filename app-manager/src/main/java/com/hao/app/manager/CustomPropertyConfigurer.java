package com.hao.app.manager;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.hao.app.commons.utils.PropertiesUtils;

/**
 * 自定义获取配置文件
 * 
 * @author haoguowei
 *
 */
public class CustomPropertyConfigurer extends PropertyPlaceholderConfigurer {

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		PropertiesUtils.setProperties(props);
	}

}
