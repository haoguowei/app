package com.hao.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hao.app.pojo.SysRole;

/**
 * 角色表
 * 
 * @author haoguowei
 *
 */
public interface SysRoleMapper {

    List<SysRole> queryAllRoles();

	SysRole queryByPrimaryKey(@Param("id")int id);

	void updateRole(SysRole role);

	void insertRole(SysRole role);

	void deleteRole(@Param("id")int id);
}