package com.hao.app.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class IncomeDO implements Serializable {

    private Integer id;

    private Integer projects;

    private String projectsName;

    private Date incomeDay;

    private BigDecimal incomeAmount;


    private String contractNumb;
    private BigDecimal contractAmount;

    private String jiafang;

    private String jiafangInfo;

    private String remark;

    private String creater;

    private Date createTime;

    private Date updateTime;

}