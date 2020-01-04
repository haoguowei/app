package com.hao.app.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@ToString
public class OtherCostDO implements Serializable {

    private Integer id;

    private Integer projects;

    private String projectsName;

    private Date payDay;

    private BigDecimal bangongAmount;

    private BigDecimal zhaodaiAmount;

    private BigDecimal otherAmount;

    private BigDecimal totalAmount;

    private String remark;

    private String creater;

    private Date createTime;

    private Date updateTime;


}