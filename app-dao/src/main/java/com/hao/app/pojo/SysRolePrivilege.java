package com.hao.app.pojo;

import java.io.Serializable;

public class SysRolePrivilege implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2102874433102883871L;

	private Integer roleId;

    private Integer privilegeId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }
}