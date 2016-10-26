package com.hao.app.commons.entity;

public class Constants {

	private Constants() {

	}

	// 图片地址配置的key
	public static final String CONFIG_KEY_UPFILE_URL = "conf.upfile.url";
	// 图片存储路径配置的key
	public static final String CONFIG_KEY_UPFILE_PATH = "conf.upfile.path";

	// 请求后缀
	public static final String URLSUFFIX = ".do";

	// 1M
	public static final long _1M = 1024 * 1024;

	// 状态值
	public static final int VALID = 1;// 有效
	public static final int UNVALID = 0;// 无效

	// 当前登录用户信息
	public static final String CURRENT_LOGIN_USER = "CurrentLoginUser";

}
