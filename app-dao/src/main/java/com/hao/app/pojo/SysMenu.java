package com.hao.app.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SysMenu implements Serializable{

    private static final long serialVersionUID = -5615754741766804666L;

    private Integer id;

    private Integer parent;

    private String name;

    private String url;

    private Integer sort;

}