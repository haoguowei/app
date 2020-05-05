package com.hao.app.service;

import com.hao.app.commons.entity.TreeNodeMode;
import com.hao.app.pojo.SysPrivilege;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SysPrivilegeService {
	
	/**
	 * 加载所有系统权限
	 * 
	 * @author yanwei
	 */
	Set<String> reLoadAllPrivilegeSet();
	
	/**
	 * 加载所有系统权限
	 * 
	 * @author yanwei
	 */
	Set<String> getAllPrivilegeSet();
	
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
	 * 查询角色对应的权限URL
	 * @param roleId
	 * @return
	 */
	List<String> queryPrivilegeURLByRoleId(Integer roleId);

	
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


	/**
	 * 获取每个菜单对应的权限列表
	 * @param roleId 
	 * @return
	 */
	Map<Integer, List<TreeNodeMode>> getMenuPrivilegeMap(int roleId);

}
