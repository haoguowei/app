package com.hao.app.service;

public interface SysRolePrivilegeService {
	
	/**
	 * 设置角色于权限的对应关系
	 * @param roleId
	 * @param priIds
	 * @return
	 */
	Boolean saveRolePrivileges(int roleId, String priIds);
	
}
