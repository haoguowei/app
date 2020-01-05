package com.hao.app.commons.entity.param;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class QueryParam implements Serializable{

	private static final long serialVersionUID = -4344414163652660985L;

	private int pageStart = 0;

	private int pageLimit = 20;

	public QueryParam(int pageStart, int pageLimit) {
		this.pageStart = pageStart;
		this.pageLimit = pageLimit;
	}

	public QueryParam() {
	}

}
