package com.hao.app.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class PayDetailDO implements Serializable {
    private Integer id;

    private Integer payId;

    private Integer projects;

    private String projectsName;

    private Date payMonth;

    private Integer empId;

    private String empName;

    private Integer gender;

    private String jobName;

    private BigDecimal fixAmount;

    private BigDecimal jiabanAmount;

    private BigDecimal jixiaoAmount;

    private BigDecimal jiangjinAmount;

    private BigDecimal fakuanAmount;

    private BigDecimal totalAmount;

    private BigDecimal payedAmount;

    private BigDecimal yuAmount;

    private Integer payStatus;

    private String remark;

    private String creater;

    private Date createTime;

    private Date updateTime;
}