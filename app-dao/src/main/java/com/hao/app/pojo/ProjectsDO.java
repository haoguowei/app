package com.hao.app.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@Data
@ToString
public class ProjectsDO implements Serializable {

    private Integer id;

    private Integer areaId;

    private String name;

    private String areaName;

    private String remark;

    private String creater;

    private Date createTime;

    private Date updateTime;
}