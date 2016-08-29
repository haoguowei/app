package com.hao.app.rpc.test.extension;

public class DemoMyExtensionImpl implements MyExtension{

	@Override
	public String sayHello(String name, MyExtensionTypeEnum type) {
		System.out.println("=========demo==========" + name);
		return "Hello," + name;
	}

}
