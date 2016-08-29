package com.hao.app.service;

public interface SysRolePrivilegeService {
	
	/**
	 * 设置角色于权限的对应关系
	 * @param role
	 * @param priIds
	 * @return
	 */
	Boolean saveRolePrivileges(int role, String priIds);
	
	/**
	 * 删除角色的所有权限
	 * @param roleId
	 * @return
	 */
	int deletePrivilegesByRoleId(int roleId);

}
