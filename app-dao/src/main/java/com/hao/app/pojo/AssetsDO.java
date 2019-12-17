package com.hao.app.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class AssetsDO implements Serializable {

    private Integer id;

    private Integer type;

    private String name;

    private String number;

    private String license;

    private String brand;

    private String carType;

    private Date buyTime;

    private String inOut;

    private Integer quantity;

    private Integer quoQuantity;

    private Integer owner;

    private String storageLocation;

    private String engineNumber;

    private Integer engineNumberType;

    private BigDecimal price;

    private Integer staging;

    private BigDecimal purTax;

    private String remark;

    private String creater;

    private Date createTime;

    private Date updateTime;
}