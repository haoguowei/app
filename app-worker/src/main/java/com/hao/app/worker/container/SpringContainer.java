package com.hao.app.worker.container;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hao.app.worker.Container;

public class SpringContainer implements Container {

	private static final Logger logger = LoggerFactory.getLogger(SpringContainer.class);

	public static final String SPRING_CONFIG = "classpath*:conf/spring/spring-*.xml";

	public static ClassPathXmlApplicationContext context;

	public static ClassPathXmlApplicationContext getContext() {
		return context;
	}

	public void start() {
		context = new ClassPathXmlApplicationContext(new String[] { SPRING_CONFIG });
		context.start();
	}

	public void stop() {
		try {
			if (context != null) {
				context.stop();
				context.close();
				context = null;
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
		}
	}

}