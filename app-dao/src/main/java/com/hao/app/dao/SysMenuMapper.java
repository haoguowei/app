package com.hao.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hao.app.pojo.SysMenu;

public interface SysMenuMapper {

	/**
	 * 根据父节点id找所有子节点
	 * @param parentId
	 * @return
	 */
	List<SysMenu> queryMenuByParentId(@Param("parentId")int parentId);
   
}