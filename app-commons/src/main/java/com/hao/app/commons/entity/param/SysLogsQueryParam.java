package com.hao.app.commons.entity.param;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 日志查询对象
 * 
 * @author haoguowei
 *
 */
@Data
@ToString
public class SysLogsQueryParam extends QueryParam implements Serializable{

	private static final long serialVersionUID = 4737617179006429526L;
	
	public SysLogsQueryParam(int pageStart, int pageLimit) {
		super(pageStart, pageLimit);
	}

	private String name;

	
}
