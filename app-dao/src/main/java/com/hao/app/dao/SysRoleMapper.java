package com.hao.app.dao;

import java.util.List;

import com.hao.app.pojo.SysRole;

/**
 * 角色表
 * 
 * @author haoguowei
 *
 */
public interface SysRoleMapper {

    List<SysRole> queryAllRoles();
}