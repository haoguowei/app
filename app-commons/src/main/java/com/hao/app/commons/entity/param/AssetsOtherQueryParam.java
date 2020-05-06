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
public class AssetsOtherQueryParam extends QueryParam implements Serializable {

	private static final long serialVersionUID = 4737617179006429526L;

	private Integer projectsId;
	private Integer type;

	private Integer carType;
	private Integer brand;

	private Integer nameId;

	private String buyTimeStart;
	private String buyTimeEnd;

	public AssetsOtherQueryParam(int pageStart, int pageLimit) {
		super(pageStart, pageLimit);
	}


}
