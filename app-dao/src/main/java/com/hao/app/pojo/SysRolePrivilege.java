package com.hao.app.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SysRolePrivilege implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -2102874433102883871L;

    private Integer roleId;

    private Integer privilegeId;
}