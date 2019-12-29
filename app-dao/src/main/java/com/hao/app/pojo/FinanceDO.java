package com.hao.app.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@ToString
public class FinanceDO implements Serializable {

    private Integer id;

    private Integer projects;

    private String projectsName;

    private BigDecimal incomeAmount;

    private BigDecimal payoutAmount;

    private BigDecimal profit;

    private Date upDay;

    private String remark;

    private String creater;

    private Date createTime;

    private Date updateTime;

}