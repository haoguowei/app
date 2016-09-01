package com.hao.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hao.app.pojo.SysPrivilege;

public interface SysPrivilegeMapper {

	/**
	 * 查询所有权限
	 * @return
	 */
	List<SysPrivilege> queryAllPrivilege();

	/**
	 * 查询角色对应的权限
	 * @param roleId
	 * @return
	 */
	List<SysPrivilege> queryPrivilegeByRoleId(@Param("roleId")Integer roleId);

	/**
	 * 查询角色对应的权限url
	 * @param roleId
	 * @return
	 */
	List<String> queryPrivilegeURLByRoleId(@Param("roleId")Integer roleId);

	/**
	 * 查询menu下的设置的所有权限
	 * @param menuId
	 * @return
	 */
	List<SysPrivilege> queryPrivilegeByMenuId(@Param("menuId")Integer menuId);

	/**
	 * 删除关联的权限
	 * @param menuId
	 */
	void deleteRolePrivilegeByMenuId(@Param("menuId")int menuId);
	
	/**
	 * 删除菜单下的权限
	 * @param menuId
	 */
	void deletePrivilegeByMenuId(@Param("menuId")int menuId);
    
}