package com.hao.app.commons.enums;

public enum PayStatusEnum {

	NO(0, "否"),
	YES(1, "是");


	private int code;

	private String msg;

	private PayStatusEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String toString() {
		return this.code + ":" + this.msg;
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
