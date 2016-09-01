package com.hao.app.dao;

import org.apache.ibatis.annotations.Param;

import com.hao.app.pojo.SysRolePrivilege;

public interface SysRolePrivilegeMapper {

	/**
	 * 新增记录
	 * @param rp
	 */
	void insert(SysRolePrivilege rp);
	
	/**
	 * 删除角色的所有权限
	 * @param roleId
	 * @return
	 */
	int deletePrivilegesByRoleId(@Param("roleId")int roleId);

	/**
	 * 删除菜单关联的权限
	 * @param menuId
	 */
	void deleteRolePrivilegeByMenuId(@Param("menuId")int menuId);

	/**
	 * 删除关联的权限值
	 * @param privilegeId
	 */
	void deletePrivilegesByPrivilegeId(@Param("privilegeId")int privilegeId);
}