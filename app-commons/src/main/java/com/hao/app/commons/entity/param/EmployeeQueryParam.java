package com.hao.app.commons.entity.param;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

/**
 * 查询对象
 *
 * @author haoguowei
 */
@Data
@ToString
public class EmployeeQueryParam extends QueryParam implements Serializable {

	private static final long serialVersionUID = 4737617179006429526L;

	private Integer projectsId;
	private Integer status;

	private String name;
	private String idCard;

	private String entryDateStart;
	private String entryDateEnd;

	private String leaveDateStart;
	private String leaveDateEnd;

	private Set<Integer> jobTypes;

	public EmployeeQueryParam(int pageStart, int pageLimit) {
		super(pageStart, pageLimit);
	}


}
