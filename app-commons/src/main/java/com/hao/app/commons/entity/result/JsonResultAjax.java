package com.hao.app.commons.entity.result;

import java.io.Serializable;

import com.hao.app.commons.enums.ResultCodeEnum;

/**
 * 前端返回结果ajax用
 * 
 * @author haoguowei
 *
 */
public class JsonResultAjax implements Serializable {

	private static final long serialVersionUID = 6059518073716169394L;

	private boolean success;

	private ResultCodeEnum resultCode;

	private Object data;

	public JsonResultAjax() {

	}

	public JsonResultAjax(boolean success) {
		this.success = success;
	}
	
	public JsonResultAjax(ResultCodeEnum resultCode) {
		this.success = resultCode.equals(ResultCodeEnum.SUCCESS);
		this.resultCode = resultCode;
	}

	public JsonResultAjax(boolean success, ResultCodeEnum resultCode) {
		this.success = success;
		this.resultCode = resultCode;
	}
	
	public JsonResultAjax(boolean success, Object data) {
		this.success = success;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public ResultCodeEnum getResultCode() {
		return resultCode;
	}

	public void setResultCode(ResultCodeEnum resultCode) {
		this.resultCode = resultCode;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JsonResultAjax [success=" + success + ", resultCode=" + resultCode + ", data=" + data + "]";
	}

}
