package com.hao.app.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Data
@ToString
public class CostsTypeDO implements Serializable {

    private Integer id;

    private Integer parentId;

    private String name;


    //////////////////////////////////////////
    private String name1;

    private String name2;

    private String name3;

    private Set<Integer> leafIds;

    private int rowspan = 1;

    private int colpan = 1;

    private String background;

}