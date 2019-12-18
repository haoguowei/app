package com.hao.app.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;


@Data
@ToString
public class AreaDO implements Serializable {
    private Integer id;

    private String name;

    private String remark;

    private String creater;

    private Date createTime;

    private Date updateTime;

}