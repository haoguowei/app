package com.hao.app.rpc.test.extension;

import com.alibaba.dubbo.common.extension.SPI;

@SPI("default")
public interface MyExtension {

	String sayHello(String name, MyExtensionTypeEnum type);
}
