package com.hao.app.commons.error;

import com.hao.app.commons.enums.ResultCodeEnum;

/**
 * ajax 自定义运行时异常
 *
 */
public class AppAjaxRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private ResultCodeEnum errorCode;
	
	public AppAjaxRuntimeException(ResultCodeEnum errorCode, Throwable cause){
		super(errorCode.msg(), cause);
		this.errorCode = errorCode;
	}
	
	public AppAjaxRuntimeException(String message){
		super(message);
		this.errorCode = ResultCodeEnum.SERVER_ERROR;
	}
	
	public AppAjaxRuntimeException(ResultCodeEnum errorCode, String message){
		super(message);
		this.errorCode = errorCode;
	}
	
	public AppAjaxRuntimeException(ResultCodeEnum errorCode, String message, Throwable cause){
		super(message, cause);
		this.errorCode = errorCode;
	}

	public ResultCodeEnum getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ResultCodeEnum errorCode) {
		this.errorCode = errorCode;
	}

	
	
}
