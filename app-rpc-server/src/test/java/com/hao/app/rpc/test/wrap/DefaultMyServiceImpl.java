package com.hao.app.rpc.test.wrap;

import com.hao.app.rpc.test.extension.MyExtension;

public class DefaultMyServiceImpl implements MyService{
	
	private MyExtension myExtension;
	
	public void setMyService(MyExtension myExtension) {
		this.myExtension = myExtension;
	}


	@Override
	public String sayHello(String name) {
		System.out.println("====default======" + name);
		myExtension.sayHello("哈哈", null);
		return "你好，" + name;
	}
}
