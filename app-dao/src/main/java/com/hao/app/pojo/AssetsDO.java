package com.hao.app.pojo;

import com.hao.app.commons.entity.Dicts;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ToString
public class AssetsDO implements Serializable {

    private Integer id;

    private Integer projects;

    private Integer type;
    private String typeStr;

    private String name;

    private String projectsName;

    private String number;

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

    public void setType(Integer type) {
        this.typeStr = Dicts.assetsTypeMap.get(type);
        this.type = type;
    }
}