package com.hao.app.commons.entity.param;

import java.io.Serializable;

public class QueryParam implements Serializable{
	
	private static final long serialVersionUID = -4344414163652660985L;

	private int pageStart = 0;
	
	private int pageLimit = 20;
	
	public QueryParam(int pageStart, int pageLimit){
		this.pageStart = pageStart;
		this.pageLimit = pageLimit;
	}

	public int getPageStart() {
		return pageStart;
	}

	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}
}
