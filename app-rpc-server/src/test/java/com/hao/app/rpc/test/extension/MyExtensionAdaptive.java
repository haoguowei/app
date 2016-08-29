package com.hao.app.rpc.test.extension;

import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.ExtensionLoader;

@Adaptive
public class MyExtensionAdaptive implements MyExtension{

	@Override
	public String sayHello(String name, MyExtensionTypeEnum type) {
		System.out.println("MyExtension适配器:" + type);
		ExtensionLoader<MyExtension> loader = ExtensionLoader.getExtensionLoader(MyExtension.class);
		MyExtension my = null;
		if(type == null){
			my = loader.getDefaultExtension();
		}else{
			my = loader.getExtension(type.getValue());
		}
		return my.sayHello(name, type);
	}
}
