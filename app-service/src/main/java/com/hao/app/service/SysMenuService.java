package com.hao.app.service;

import java.util.List;

import com.hao.app.commons.entity.TreeNodeMode;

public interface SysMenuService {

	/**
	 * 查找菜单树
	 * @return
	 */
	List<TreeNodeMode> getMenuTree();

}
