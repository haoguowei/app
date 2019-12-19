package com.hao.app.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SysPrivilege implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6407803666512759200L;

    private Integer id;

    private Integer menuId;

    private String name;

    private String url;

}