package com.hao.app.service.test.schema;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hao.app.commons.entity.DemoBean;

/**
 * 测试自定义标签
 * @author haoguowei
 *
 */
public class ShemaTest {

	private Logger logger = LoggerFactory.getLogger(ShemaTest.class);

	@Test
	public void test() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-demo.xml");  
		DemoBean s = (DemoBean)ctx.getBean("demo");  
		logger.info(s.toString());
	};
}