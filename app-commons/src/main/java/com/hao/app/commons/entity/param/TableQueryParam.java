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

	private String enterDateStart;
	private String enterDateEnd;

	private String fromYear;
	private String fromMonth;
	private String toYear;
	private String toMonth;

	public String getTitleName() {
		return this.fromYear + "年" + this.fromMonth + "月 ~" + this.toYear + "年" + this.toMonth + "月";
	}


}
