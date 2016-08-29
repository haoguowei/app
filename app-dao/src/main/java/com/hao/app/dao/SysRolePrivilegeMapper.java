package com.hao.app.dao;

import org.apache.ibatis.annotations.Param;

import com.hao.app.pojo.SysRolePrivilege;

public interface SysRolePrivilegeMapper {

	/**
	 * 删除角色的所有权限
	 * @param roleId
	 * @return
	 */
	int deletePrivilegesByRoleId(@Param("roleId")int roleId);

	void insert(SysRolePrivilege rp);
}