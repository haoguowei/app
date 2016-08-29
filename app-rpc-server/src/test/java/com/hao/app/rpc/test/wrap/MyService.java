package com.hao.app.rpc.test.wrap;

import com.alibaba.dubbo.common.extension.SPI;

@SPI("myservice")
public interface MyService {

	String sayHello(String name);
}
