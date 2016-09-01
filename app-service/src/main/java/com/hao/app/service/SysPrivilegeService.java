package com.hao.app.service;

import java.util.List;
import java.util.Set;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.pojo.SysPrivilege;

public interface SysPrivilegeService {
	
	/**
	 * 查询所有权限
	 * @return
	 */
	List<SysPrivilege> queryAllPrivilege();
	
	
	/**
	 * 查询所有权限URL
	 * @return
	 */
	Set<String> queryAllPrivilegeUrls();
	
	/**
	 * 查询角色对应的权限
	 * @param roleId
	 * @return
	 */
	List<SysPrivilege> queryPrivilegeByRoleId(Integer roleId);

	/**
	 * 查询角色对应的权限URL
	 * @param roleId
	 * @return
	 */
	List<String> queryPrivilegeURLByRoleId(Integer roleId);

	/**
	 * 查询角色没有的权限
	 * @param roleId
	 * @return
	 */
	JsonResult<SysPrivilege> queryNoPrivilegesByRoleId(int roleId);
	
	/**
	 * 查询menu下的设置的所有权限
	 * @param roleId
	 * @return
	 */
	List<SysPrivilege> queryPrivilegeByMenuId(Integer menuId);

	/**
	 * 根据id获取权限
	 * @param id
	 * @return
	 */
	SysPrivilege queryByPrimaryKey(int id);


	/**
	 * 保存权限
	 * @param privilege
	 * @return
	 */
	boolean savePrivilege(SysPrivilege privilege);


	/**
	 * 删除权限
	 * @param privilegeId
	 * @return
	 */
	boolean deletePrivilege(int privilegeId);

}
