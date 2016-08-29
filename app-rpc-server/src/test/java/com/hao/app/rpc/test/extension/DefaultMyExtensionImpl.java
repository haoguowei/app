package com.hao.app.rpc.test.extension;

public class DefaultMyExtensionImpl implements MyExtension{
	
	@Override
	public String sayHello(String name, MyExtensionTypeEnum type) {
		System.out.println("=========default==========" + name);
		return "Hello," + name;
	}

}
