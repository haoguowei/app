package com.hao.app.commons.error;

import com.hao.app.commons.enums.ResultCodeEnum;

/**
 * 自定义运行时异常
 *
 */
public class AppRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private ResultCodeEnum errorCode;
	
	public AppRuntimeException(ResultCodeEnum errorCode, Throwable cause){
		super(cause);
		this.errorCode = errorCode;
	}
	
	public AppRuntimeException(ResultCodeEnum errorCode, String message){
		super(message);
		this.errorCode = errorCode;
	}
	
	public AppRuntimeException(ResultCodeEnum errorCode, String message, Throwable cause){
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
