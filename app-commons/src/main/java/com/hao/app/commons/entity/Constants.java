package com.hao.app.commons.entity;

import java.util.Set;

public class Constants {
	
	private Constants(){
		
	}
	
	//请求后缀
	public static final String URLSUFFIX = ".do";
	
	//1M
	public static final long _1M = 1024 * 1024;
	
	//系统启动时加载的所有权限,用于权限验证
	public static Set<String> allPrivilegeSet = null;

	//状态值
	public static final int VALID = 1;//有效
	public static final int UNVALID = 0;//无效
	
	
	//当前登录用户信息
	public static final String CURRENT_LOGIN_USER = "CurrentLoginUser";
	
	
}
