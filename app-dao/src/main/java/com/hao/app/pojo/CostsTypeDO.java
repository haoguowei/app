package com.hao.app.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CostsTypeDO implements Serializable {

    private Integer id;

    private Integer parentId;

    private String name;

}