package com.hao.app.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.dao.SysPrivilegeMapper;
import com.hao.app.pojo.SysPrivilege;
import com.hao.app.service.SysPrivilegeService;

@Service
public class SysPrivilegeServiceImpl implements SysPrivilegeService {
	
	@Autowired
	private SysPrivilegeMapper sysPrivilegeMapper;
	
	/**
	 * 查询所有权限
	 */
	@Override
	public List<SysPrivilege> queryAllPrivilege() {
		return sysPrivilegeMapper.queryAllPrivilege();
	}

	/**
	 * 查询角色对应的权限
	 */
	@Override
	public List<SysPrivilege> queryPrivilegeByRoleId(Integer roleId) {
		return sysPrivilegeMapper.queryPrivilegeByRoleId(roleId);
	}
	
	/**
	 * 查询角色对应的权限url
	 */
	@Override
	public List<String> queryPrivilegeURLByRoleId(Integer roleId) {
		return sysPrivilegeMapper.queryPrivilegeURLByRoleId(roleId);
	}

	/**
	 * 查询角色没有的权限
	 */
	@Override
	public JsonResult<SysPrivilege> queryNoPrivilegesByRoleId(int roleId) {
		List<SysPrivilege> ls = queryNoPrivilegesList(roleId);
		return new JsonResult<SysPrivilege>(ls.size(), ls);
	}
	
	/**
	 * 查询角色没有的权限list
	 * @param roleId
	 * @return
	 */
	public List<SysPrivilege> queryNoPrivilegesList(int roleId) {
		List<SysPrivilege> all = queryAllPrivilege();
		List<SysPrivilege> list = queryPrivilegeByRoleId(roleId);
		if (list == null || list.size() == 0) {
			return all;
		}

		List<Integer> ids = new ArrayList<Integer>();
		for (SysPrivilege sp : list) {
			ids.add(sp.getId());
		}

		List<SysPrivilege> resList = new ArrayList<SysPrivilege>();
		for (SysPrivilege sp : all) {
			if (!ids.contains(sp.getId())) {
				resList.add(sp);
			}
		}
		return resList;
	}

	/**
	 * 查询所有权限URL
	 */
	@Override
	public Set<String> queryAllPrivilegeUrls() {
		List<SysPrivilege> allPrivilege = queryAllPrivilege();
		if(allPrivilege == null){
			return null;
		}
		
		Set<String> urls = new HashSet<String>();
		for(SysPrivilege p : allPrivilege){
			urls.add(p.getUrl());
		}
		return urls;
	}

}
