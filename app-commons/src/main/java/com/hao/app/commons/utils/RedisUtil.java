package com.hao.app.commons.utils;

import java.nio.charset.Charset;

public class RedisUtil {
	
	public static final Charset charset = Charset.forName("UTF8");
	
	private RedisUtil(){
		
	}
	
	public static byte[][] serializeMulti(String[] keys) {
		if (keys == null) {
			return new byte[0][];
		}

		byte[][] ret = new byte[keys.length][];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = (keys[i] == null ? null : keys[i].getBytes(charset));
		}

		return ret;
	}

}
