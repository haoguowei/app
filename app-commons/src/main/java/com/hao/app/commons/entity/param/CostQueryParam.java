package com.hao.app.commons.entity.param;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 查询对象
 *
 * @author haoguowei
 */
@Data
@ToString
public class CostQueryParam extends QueryParam implements Serializable {

	private static final long serialVersionUID = 4737617179006429526L;

	private Integer projectsId;
	private Integer employeeId;
	private String enterDateStart;
	private String enterDateEnd;

	public CostQueryParam(int pageStart, int pageLimit) {
		super(pageStart, pageLimit);
	}


}
