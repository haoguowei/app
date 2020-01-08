package com.hao.app.commons.entity.param;

import com.hao.app.commons.utils.DateUtil;
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
	private Integer status; //

	private String name;
	private String idCard;

	private String entryDateStart;
	private String entryDateEnd;

	private String leaveDateStart;
	private String leaveDateEnd;

	private Set<Integer> jobTypes;


	//超龄
	private String birthDay = DateUtil.getPassAgeDate();
	private Integer passAge; //0-所有；1-未超龄；2-超龄

	public EmployeeQueryParam(int pageStart, int pageLimit) {
		super(pageStart, pageLimit);
	}


}
