package com.hao.app.pojo;

import java.io.Serializable;
import java.util.Date;

public class SysLogs implements Serializable{
	
	private static final long serialVersionUID = -3984543850705972217L;

	private Integer id;

    private Date operatorTime;

    private String operator;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Date operatorTime) {
        this.operatorTime = operatorTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}