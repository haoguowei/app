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
public class TableQueryParam implements Serializable {

	private static final long serialVersionUID = 4737617179006429526L;

	private Integer projectsId;
	private Integer type1;
	private Integer type2;
	private Integer type3;

	private String enterDateStart;
	private String enterDateEnd;

	public String getTitleName() {
		return this.enterDateStart + " ~" + this.enterDateEnd;
	}


}
