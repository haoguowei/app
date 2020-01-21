package com.hao.app.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class PayDO implements Serializable {
    private Integer id;

    private Integer projects;

    private String projectsName;

    private Date payMonth;

    private Integer payStatus; //是否全部发放

    private Integer totalMan;

    private BigDecimal totalAmount;

    private BigDecimal payedAmount;

    private BigDecimal yuAmount;

    private String remark;

    private String creater;

    private Date createTime;

    private Date updateTime;
}