package com.hao.app.rpc.test.extension;

import org.junit.Test;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

public class Main {

	@Test
	public void test() {
		ExtensionLoader<MyExtension> loader = ExtensionLoader.getExtensionLoader(MyExtension.class);
		MyExtension my = loader.getAdaptiveExtension();

		my.sayHello("张三丰", null);
		my.sayHello("刘德华", MyExtensionTypeEnum.DEFAULT);
		my.sayHello("王菲", MyExtensionTypeEnum.DEMO);
	}
}
