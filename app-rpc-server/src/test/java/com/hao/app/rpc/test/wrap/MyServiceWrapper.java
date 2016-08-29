package com.hao.app.rpc.test.wrap;

/**
 * ExtensionLoader会把加载扩展点时（通过扩展点配置文件中内容），如果该实现有拷贝构造函数，则判定为扩展点Wrapper类。
 *
 */
public class MyServiceWrapper implements MyService{
	
	private MyService myService;
	
	//构造函数
	public MyServiceWrapper(MyService myService){
		if (myService == null) {
            throw new IllegalArgumentException("myService == null");
        }
		this.myService = myService;
	}

	@Override
	public String sayHello(String name) {
		System.out.println("======我是代理！====before====");
		String res = myService.sayHello(name);
		System.out.println("======我是代理！====after====");
		return res;
	}

}
