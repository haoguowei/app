package com.hao.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hao.app.dao.SysRoleMapper;
import com.hao.app.dao.SysRolePrivilegeMapper;
import com.hao.app.pojo.SysRole;
import com.hao.app.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Autowired
	private SysRolePrivilegeMapper sysRolePrivilegeMapper;

	@Override
	public List<SysRole> queryAllRoles() {
		return sysRoleMapper.queryAllRoles();
	}

	@Override
	public SysRole queryByPrimaryKey(int roleId) {
		return sysRoleMapper.queryByPrimaryKey(roleId);
	}

	@Override
	public boolean deleteRole(int roleId) {
		//1.删除权限表权限
		sysRoleMapper.deleteRole(roleId);
		
		//2.删除权限关系表权限
		sysRolePrivilegeMapper.deletePrivilegesByRoleId(roleId);
		return true;
	}

	@Override
	public boolean saveRole(SysRole role) {
		if(role.getId() > 0){
			//修改
			sysRoleMapper.updateRole(role);
		}else{
			//新增
			sysRoleMapper.insertRole(role);
		}
		return true;
	}

}
