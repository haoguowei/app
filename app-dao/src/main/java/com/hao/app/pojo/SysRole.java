package com.hao.app.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SysRole implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 6916722107421779478L;

    private Integer id;

    private String name;

    private String intro;

}