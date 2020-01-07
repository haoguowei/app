package com.hao.app.commons.enums;

public enum EmpStatusEnum {

	INIT(0, "非正式员工"),
	OFFICIAL(1, "正式员工"),
	APPLYING(100, "申请状态"),
	LEAVE(99, "离职状态");


	private int code;

	private String msg;

	private EmpStatusEnum(int code, String msg) {
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
