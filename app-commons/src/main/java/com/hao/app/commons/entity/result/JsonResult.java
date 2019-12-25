package com.hao.app.commons.entity.result;

import java.io.Serializable;
import java.util.List;

/**
 * 前端返回结果
 * 
 * @author haoguowei
 *
 * @param <T>
 */
public class JsonResult<T> implements Serializable {

	private static final long serialVersionUID = -6008308696838665401L;

	// 总记录数
	private int total;

	// 结果集
	private List<T> resultList;

	public JsonResult(int total, List<T> resultList) {
		this.total = total;
		this.resultList = resultList;
	}

	public JsonResult() {

	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List<T> resultList) {
		this.resultList = resultList;
	}

}
