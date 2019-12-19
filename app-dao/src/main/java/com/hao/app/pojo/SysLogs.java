package com.hao.app.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class SysLogs implements Serializable{

    private static final long serialVersionUID = -3984543850705972217L;

    private Integer id;

    private Date operatorTime;

    private String operator;

    private String description;
}