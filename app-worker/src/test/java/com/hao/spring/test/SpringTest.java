package com.hao.spring.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class) //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" }) //加载配置
public class SpringTest {
	
	private Logger logger = LoggerFactory.getLogger(SpringTest.class);
	
	@Test
	public void testBeanFactory(){
		logger.info(">>>>>>>>>");
	}
}
