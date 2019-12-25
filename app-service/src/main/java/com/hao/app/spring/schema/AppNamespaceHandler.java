package com.hao.app.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

import com.hao.app.commons.entity.DemoBean;

public class AppNamespaceHandler extends NamespaceHandlerSupport{

	@Override
	public void init() {
		registerBeanDefinitionParser("demo", new AppBeanDefinitionParser(DemoBean.class));
		
		
	}

}
