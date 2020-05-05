package com.hao.app.spring.schema;

import com.hao.app.commons.entity.DemoBean;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class AppNamespaceHandler extends NamespaceHandlerSupport{

	@Override
	public void init() {
		registerBeanDefinitionParser("demo", new AppBeanDefinitionParser(DemoBean.class));
		
		
	}

}
