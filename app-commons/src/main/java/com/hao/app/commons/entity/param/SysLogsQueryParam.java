package com.hao.app.commons.entity.param;

import java.io.Serializable;

/**
 * 日志查询对象
 * 
 * @author haoguowei
 *
 */
public class SysLogsQueryParam extends QueryParam implements Serializable{

	private static final long serialVersionUID = 4737617179006429526L;
	
	public SysLogsQueryParam(int pageStart, int pageLimit) {
		super(pageStart, pageLimit);
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SysLogsQueryParam [name=" + name + ", getPageStart()=" + getPageStart() + ", getPageLimit()=" + getPageLimit() + "]";
	}
	
}
