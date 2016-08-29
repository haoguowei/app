package com.hao.app.rpc.service.impl;

import com.hao.app.rpc.service.MyRPCService;

public class MyRPCServiceImpl implements MyRPCService {

	@Override
	public String sayHello(String name) {
		System.out.println("======================================" + name);
		return "你好," + name;
	}

}
