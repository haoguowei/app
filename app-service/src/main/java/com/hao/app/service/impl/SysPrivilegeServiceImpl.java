package com.hao.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hao.app.commons.entity.TreeNodeMode;
import com.hao.app.dao.SysPrivilegeMapper;
import com.hao.app.dao.SysRolePrivilegeMapper;
import com.hao.app.pojo.SysPrivilege;
import com.hao.app.service.SysPrivilegeService;

@Service
public class SysPrivilegeServiceImpl implements SysPrivilegeService {
	
	@Autowired
	private SysPrivilegeMapper sysPrivilegeMapper;
	
	@Autowired
	private SysRolePrivilegeMapper sysRolePrivilegeMapper;
	
	/**
	 * 查询所有权限
	 */
	@Override
	public List<SysPrivilege> queryAllPrivilege() {
		return sysPrivilegeMapper.queryAllPrivilege();
	}

	/**
	 * 查询角色对应的权限url
	 */
	@Override
	public List<String> queryPrivilegeURLByRoleId(Integer roleId) {
		return sysPrivilegeMapper.queryPrivilegeURLByRoleId(roleId);
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

	/**
	 * 查询menu下的设置的所有权限
	 */
	@Override
	public List<SysPrivilege> queryPrivilegeByMenuId(Integer menuId) {
		return sysPrivilegeMapper.queryPrivilegeByMenuId(menuId);
	}

	/**
	 * 根据id获取权限
	 */
	@Override
	public SysPrivilege queryByPrimaryKey(int id) {
		return sysPrivilegeMapper.queryByPrimaryKey(id);
	}

	/**
	 * 保存权限
	 */
	@Override
	public boolean savePrivilege(SysPrivilege privilege) {
		if(privilege.getId() > 0){
			//修改
			sysPrivilegeMapper.updatePrivilege(privilege);
		}else{
			//新增
			sysPrivilegeMapper.insertPrivilege(privilege);
		}
		return true;
	}

	/**
	 * 删除权限
	 */
	@Override
	@Transactional
	public boolean deletePrivilege(int privilegeId) {
		//1.删除权限表权限
		sysPrivilegeMapper.deletePrivilege(privilegeId);
		
		//2.删除权限关系表权限
		sysRolePrivilegeMapper.deletePrivilegesByPrivilegeId(privilegeId);
		return true;
	}

	/**
	 * 获取每个菜单对应的权限列表
	 * @return
	 */
	@Override
	public Map<Integer, List<TreeNodeMode>> getMenuPrivilegeMap(int roleId) {
		Map<Integer, List<TreeNodeMode>> map = new HashMap<Integer, List<TreeNodeMode>>();
		
		//获取所有的权限
		List<SysPrivilege> allPrivilege = sysPrivilegeMapper.queryAllPrivilege();
		if(allPrivilege == null){
			return map;
		}
		
		//获取角色已经有的权限Id
		List<SysPrivilege> rolePriIds = sysPrivilegeMapper.queryPrivilegeIdByRoleId(roleId);
		
		for(SysPrivilege p : allPrivilege){
			Integer menuId = p.getMenuId() * 1000;
			
			List<TreeNodeMode> pNodes = null;
			if(map.containsKey(menuId)){
				pNodes = map.get(menuId);
			}else{
				pNodes = new ArrayList<TreeNodeMode>();
			}
			
			TreeNodeMode node = genNodeByPri(p,rolePriIds);
			pNodes.add(node);
			map.put(menuId, pNodes);
		}
		
		return map;
	}
	
	private TreeNodeMode genNodeByPri(SysPrivilege p,List<SysPrivilege> rolePriIds){
		TreeNodeMode node = new TreeNodeMode();
		node.setId(p.getId());
		node.setText(p.getUrl() + "  " + p.getName());
		node.setParentId(p.getMenuId() * 1000);
		node.setAttributes(p);
		node.setLeaf(true);
		node.setChecked(false);
		if(rolePriIds != null && rolePriIds.contains(p.getId())){
			node.setChecked(true);
		}
		
		return node;
	}

}
