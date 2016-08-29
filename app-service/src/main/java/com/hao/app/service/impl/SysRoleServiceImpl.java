package com.hao.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hao.app.dao.SysRoleMapper;
import com.hao.app.pojo.SysRole;
import com.hao.app.service.SysRoleService;

@Service
public class SysRoleServiceImpl implements SysRoleService {
	
	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public List<SysRole> queryAllRoles() {
		return sysRoleMapper.queryAllRoles();
	}

}
