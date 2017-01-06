package com.hao.spring.test;

import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import junit.framework.TestCase;

public class ApplicationContextTest extends TestCase {

	private Logger logger = LoggerFactory.getLogger(ApplicationContextTest.class);
	private ApplicationContext context;

	public void test() throws SQLException {
		context = new FileSystemXmlApplicationContext("classpath:spring/spring-*.xml");
		BasicDataSource ds = context.getBean("dataSource", BasicDataSource.class);
		logger.info("connection info:{}", ds.getConnection().toString());
	}
}
