package com.hao.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hao.app.commons.entity.TreeNodeMode;
import com.hao.app.dao.SysMenuMapper;
import com.hao.app.pojo.SysMenu;
import com.hao.app.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {

	@Autowired
	private SysMenuMapper sysMenuMapper;

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

}
