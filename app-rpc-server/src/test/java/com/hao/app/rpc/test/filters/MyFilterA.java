package com.hao.app.rpc.test.filters;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;

@Activate(group = Constants.CONSUMER)
public class MyFilterA implements Filter{

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		System.out.println("==========MyFilterA 无条件激活=============");
		return null;
	}
}
