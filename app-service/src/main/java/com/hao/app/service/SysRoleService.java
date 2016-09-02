package com.hao.app.service;

import java.util.List;

import com.hao.app.pojo.SysRole;

public interface SysRoleService {

	List<SysRole> queryAllRoles();

	SysRole queryByPrimaryKey(int roleId);

	boolean deleteRole(int roleId);

	boolean saveRole(SysRole role);

}
