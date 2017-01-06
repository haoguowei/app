package com.hao.app.service;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.SysMember;

public interface SysMemeberService {

	/**
	 * 根据id获取用户
	 * 
	 * @param id
	 * @return
	 */
	SysMember queryByPrimaryKey(int id);

	/**
	 * 分页查询用户
	 * 
	 * @param start
	 * @param limit
	 * @return
	 */
	JsonResult<SysMember> queryMemberPageList(int start, int limit);

	/**
	 * 新增用户
	 * @param member
	 * @return
	 */
	ResultCodeEnum insertMember(SysMember member);

	/**
	 * 修改用户
	 * @param member
	 * @return
	 */
	ResultCodeEnum updateMember(SysMember member);

	/**
	 *根据name获取用户 
	 * @param username
	 * @return
	 */
	SysMember getMemberByName(String username);
	
	/**
	 * 设置用户是否可用
	 * @param id
	 * @param value
	 * @return
	 */
	boolean updateMemberValid(int id,int value);

	/**
	 * 更新头像
	 * @param id
	 * @param imgs
	 * @return
	 */
	int updateMemberImgs(int id, String imgs);
}
