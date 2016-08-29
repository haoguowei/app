package com.hao.app.rpc.test.filters;

import java.util.List;

import org.junit.Test;

import com.alibaba.dubbo.common.Constants;
import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Filter;

public class Main {
	
	@Test
	public void test() {
		URL url = URL.valueOf("test://test:11/test?filters=myFilterA");
		ExtensionLoader<Filter> loader = ExtensionLoader.getExtensionLoader(Filter.class);
		List<Filter> filters = loader.getActivateExtension(url, "filters", Constants.PROVIDER);
		for(Filter f : filters){
			if(f instanceof MyFilterA || f instanceof MyFilterB){
				f.invoke(null, null);
			}
		}
	}

}
