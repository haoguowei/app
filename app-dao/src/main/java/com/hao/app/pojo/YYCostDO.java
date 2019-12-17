package com.hao.app.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@ToString
public class YYCostDO implements Serializable {

    private Integer id;

    private Date enterDate;

    private String license;

    private Integer status;

    private Integer projects;

    private Integer startMileage;

    private Integer endMileage;

    private Integer workload;

    private Integer fuel;

    private BigDecimal fuelAmount;

    private BigDecimal baoyangAmount;

    private BigDecimal fixAmount;

    private String fixFactory;

    private Integer shiguTimes;

    private BigDecimal shiguAmount;

    private BigDecimal shiguOutAmount;

    private BigDecimal baoxianAmount;

    private BigDecimal yearCheckAmount;

    private String remark;

    private String creater;

    private Date createTime;

    private Date updateTime;

}