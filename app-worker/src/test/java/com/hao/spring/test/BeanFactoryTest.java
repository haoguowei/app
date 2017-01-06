package com.hao.spring.test;

import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import junit.framework.TestCase;

public class BeanFactoryTest extends TestCase {

	private Logger logger = LoggerFactory.getLogger(BeanFactoryTest.class);

	public void test() throws SQLException {
		// 获取Ioc配置的抽象资源
		ClassPathResource resource = new ClassPathResource("spring/spring-db.xml");

		// DefaultListableBeanFactory是Ioc容器的始祖（基本容器）
		// 可作为一个单独的BeanFactory也可作为自定义BeanFactory的父类.
		// 如XmlBeanFactory的父类便是DefaultListableBeanFactory
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

		// 创建一个载入BeanDefinition的读取器XmlBeanDefinitionReader载入XML文件形式的BeanDefinition，通过一个回调配置给BeanFactory。
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

		// 从定义好的资源位置读入配置信息，具体的解析过程由XmlBeanDefinitionReader来完成。
		// 完成整个载入和注册Bean定义之后，需要的Ioc容器就建立起来了。这个时候我们就可以直接使用Ioc容器了。
		// 内部调用的doLoadBeanDefinitions是处理核心,主要完成三件事：
		// 1.获取xml文件的验证模式
		// 2.加载xml,并获取Document
		// 3.返回的Document注册成Bean，bean注册到beanDefinitionMap中，new ConcurrentHashMap<String, BeanDefinition>(64)
		reader.loadBeanDefinitions(resource);

		BasicDataSource ds = factory.getBean("dataSource", BasicDataSource.class);
		logger.info("connection info:{}", ds.getConnection().toString());
	}

}
