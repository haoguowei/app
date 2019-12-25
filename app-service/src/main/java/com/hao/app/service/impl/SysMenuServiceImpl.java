package com.hao.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hao.app.commons.entity.TreeNodeMode;
import com.hao.app.dao.SysMenuMapper;
import com.hao.app.dao.SysPrivilegeMapper;
import com.hao.app.dao.SysRolePrivilegeMapper;
import com.hao.app.pojo.SysMenu;
import com.hao.app.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Autowired
	private SysPrivilegeMapper sysPrivilegeMapper;
	
	@Autowired
	private SysRolePrivilegeMapper sysRolePrivilegeMapper;

	/**
	 * 查找菜单树
	 */
	@Override
	public List<TreeNodeMode> getMenuTree() {
		List<SysMenu> ls = getMenuByParentId(0);
		return getChilds(ls);
	}
	
	/**
	 * 菜单列表
	 * @return
	 */
	@Override
	public List<SysMenu> getMenuList() {
		List<SysMenu> ls = getMenuByParentId(0);
		return getChildList(ls);
	}
	
	public List<SysMenu> getChildList(List<SysMenu> list) {
		List<SysMenu> ls = new ArrayList<SysMenu>();
		for (SysMenu menu : list) {
			ls.add(menu);
			
			List<SysMenu> childList = getMenuByParentId(menu.getId());
			ls.addAll(childList);
		}
		return ls;
	}

	/**
	 * 根据父节点id找所有子节点
	 * 
	 * @param parentId
	 * @return
	 */
	private List<SysMenu> getMenuByParentId(int parentId) {
		return sysMenuMapper.queryMenuByParentId(parentId);
	}

	/**
	 * 得到所有字节点，并转换为node对象
	 * 
	 * @param list
	 * @return
	 */
	public List<TreeNodeMode> getChilds(List<SysMenu> list) {
		List<TreeNodeMode> ls = new ArrayList<TreeNodeMode>();
		try {
			for (SysMenu menu : list) {
				TreeNodeMode node = new TreeNodeMode();
				node.setId(menu.getId());
				node.setText(menu.getName());
				node.setChecked(null);
				node.setParentId(menu.getParent());
				node.setAttributes(menu);

				List<SysMenu> childList = getMenuByParentId(menu.getId());
				if (childList.size() > 0) {
					node.setLeaf(false);
					node.setChildren(getChilds(childList));
				} else {
					node.setLeaf(true);
					node.setHref(menu.getUrl());
				}
				ls.add(node);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public SysMenu queryByPrimaryKey(int menuId) {
		return sysMenuMapper.queryByPrimaryKey(menuId);
	}

	@Override
	public boolean saveMenu(SysMenu menu) {
		if(menu.getId() > 0){
			//修改
			sysMenuMapper.updateMenu(menu);
		}else{
			//新增
			sysMenuMapper.insertMenu(menu);
		}
		return true;
	}

	@Override
	@Transactional
	public boolean deleteMenu(int menuId) {
		//如果有子菜单，则删除子菜单的
		List<SysMenu> menuList = getMenuByParentId(menuId);
		if (menuList != null) {
			for (SysMenu menu : menuList) {
				deleteMenuById(menu.getId());
			}
		}
		
		//删除当前菜单的
		deleteMenuById(menuId);
		
		return true;
	}
	
	public boolean deleteMenuById(int menuId) {
		//1.删除菜单及子菜单
		sysMenuMapper.deleteMenu(menuId);
		
		//2.删除菜单关联的权限
		sysRolePrivilegeMapper.deleteRolePrivilegeByMenuId(menuId);
		
		//3.删除菜单下的权限
		sysPrivilegeMapper.deletePrivilegeByMenuId(menuId);
		
		return true;
	}

}
