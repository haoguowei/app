package com.hao.app.spring.schema;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class AppBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
	
	private final Class<?> beanClass;
	
	public AppBeanDefinitionParser(Class<?> beanClass){
		this.beanClass = beanClass;
	}

	@Override
	protected Class<?> getBeanClass(Element element) {
		return beanClass;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		String id = element.getAttribute("id");
		String name = element.getAttribute("name");
		String age = element.getAttribute("age");

		if(StringUtils.hasText(id)){
			builder.addPropertyValue("id", id);
		}
		if(StringUtils.hasText(name)){
			builder.addPropertyValue("name", name);
		}
		if(StringUtils.hasText(age)){
			builder.addPropertyValue("age", age);
		}
	}

}
