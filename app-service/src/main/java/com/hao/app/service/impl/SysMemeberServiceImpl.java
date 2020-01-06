package com.hao.app.service.impl;

import com.hao.app.commons.entity.param.QueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.dao.SysMemberMapper;
import com.hao.app.pojo.SysMember;
import com.hao.app.service.SysMemeberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysMemeberServiceImpl implements SysMemeberService {
	
	@Autowired
	private SysMemberMapper sysMemberMapper;
	
	@Override
	public SysMember queryByPrimaryKey(int id) {
		return sysMemberMapper.queryByPrimaryKey(id);
	}

	@Override
	public JsonResult<SysMember> queryMemberPageList(int start, int limit) {
		QueryParam queryParam = new QueryParam(start, limit);
		int count = sysMemberMapper.queryMembersCount();
		List<SysMember> list = sysMemberMapper.queryMembersPageList(queryParam);

		if (list != null) {
			for (SysMember s : list) {
				if (s.getProjectsId() == -1) {
					s.setProjectsName("总公司");
				}
			}
		}

		return new JsonResult<SysMember>(count, list);
	}

	@Override
	public ResultCodeEnum insertMember(SysMember member) {
		//新注册用户
		SysMember tmp = sysMemberMapper.queryMemberByName(member.getName());
		if(tmp != null){
			return ResultCodeEnum.FAIL_REGISTERED;
		}
		
		sysMemberMapper.insert(member);
		return ResultCodeEnum.SUCCESS;
	}

	@Override
	public ResultCodeEnum updateMember(SysMember member) {
		sysMemberMapper.updateByPrimaryKey(member);
		return ResultCodeEnum.SUCCESS;
	}

	@Override
	public SysMember getMemberByName(String userName) {
		return sysMemberMapper.queryMemberByName(userName);
	}

	@Override
	public boolean updateMemberValid(int id, int valid) {
		sysMemberMapper.updateMemberValid(id, valid);
		return true;
	}

	@Override
	public int updateMemberImgs(int id, String imgs) {
		return sysMemberMapper.updateMemberImgs(id, imgs);
	}

}
