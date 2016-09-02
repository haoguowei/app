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
	 * 删除菜单下的权限
	 * @param menuId
	 */
	void deletePrivilegeByMenuId(@Param("menuId")int menuId);

	/**
	 * 根据id获取权限
	 * @param id
	 * @return
	 */
	SysPrivilege queryByPrimaryKey(@Param("id")int id);

	/**
	 * 修改
	 * @param privilege
	 */
	void updatePrivilege(SysPrivilege privilege);

	/**
	 * 新增
	 * @param privilege
	 */
	void insertPrivilege(SysPrivilege privilege);

	/**
	 * 删除
	 * @param privilegeId
	 */
	void deletePrivilege(@Param("id")int id);

	/**
	 * 获取角色已经有的权限Id
	 * @param roleId
	 * @return
	 */
	List<Integer> queryPrivilegeIdByRoleId(@Param("roleId")int roleId);
    
}