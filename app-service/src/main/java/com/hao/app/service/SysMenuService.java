package com.hao.app.service;

import com.hao.app.commons.entity.TreeNodeMode;
import com.hao.app.pojo.SysMenu;

import java.util.List;

public interface SysMenuService {

    /**
     * 查找菜单树
     *
     * @return
     */
    List<TreeNodeMode> getMenuTree();

    /**
	 * 菜单列表
	 * @return
	 */
	List<SysMenu> getMenuList();

	SysMenu queryByPrimaryKey(int menuId);

	boolean saveMenu(SysMenu menu);

	boolean deleteMenu(int menuId);

}
