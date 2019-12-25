package com.hao.app.commons.enums;

/**
 * 结果返回码
 *
 */
public enum ResultCodeEnum {
	
	SERVER_ERROR(500, "服务器内部错误"),
	
	SUCCESS(200, "成功"),
	
	FAIL(4000,"系统错误！"),
	FAIL_REGISTERED(4001,"用户已被注册，请选用其他登录名！"),
	FAIL_OLDPWD(4002,"旧密码验证失败！"),
	
	NOTFOUND(404, "NOT FOUND");
	

	private int code;
	
	private String msg;
	
	public String toString(){
		return this.code + ":" + this.msg;
	}
	
	private ResultCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int code() {
		return code;
	}

	public String msg() {
		return msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}
