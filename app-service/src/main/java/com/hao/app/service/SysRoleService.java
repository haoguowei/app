package com.hao.app.service;

import com.hao.app.pojo.SysRole;

import java.util.List;

public interface SysRoleService {

    List<SysRole> queryAllRoles();

    SysRole queryByPrimaryKey(int roleId);

    boolean deleteRole(int roleId);

    boolean saveRole(SysRole role);

}
