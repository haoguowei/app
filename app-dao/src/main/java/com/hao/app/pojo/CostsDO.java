package com.hao.app.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class CostsDO implements Serializable {

    private Integer id;

    private Date enterDate;
    private Integer status;

    private Integer projects;
    private String projectsName;


    private Integer type1;
    private Integer type2;
    private Integer type3;

    private BigDecimal amount;
    private String numb;
    private String useful;

    private String remark;
    private String creater;

    private Date createTime;
    private Date updateTime;

}