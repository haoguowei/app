package com.hao.app.rpc.test.wrap;

import org.junit.Test;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

public class Main {
	
	@Test
	public void test() {
		ExtensionLoader<MyService> loader = ExtensionLoader.getExtensionLoader(MyService.class);
		MyService service = loader.getDefaultExtension();
		service.sayHello("刘德华");
	}

}
