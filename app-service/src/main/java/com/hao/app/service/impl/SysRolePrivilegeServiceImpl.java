package com.hao.app.service.impl;

import com.hao.app.dao.SysRolePrivilegeMapper;
import com.hao.app.pojo.SysRolePrivilege;
import com.hao.app.service.SysRolePrivilegeService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysRolePrivilegeServiceImpl implements SysRolePrivilegeService {
	
	@Autowired
	private SysRolePrivilegeMapper sysRolePrivilegeMapper;

	@Override
	@Transactional
	public Boolean saveRolePrivileges(int roleId, String priIds) {
		if(roleId > 0){
			//1.删除该角色所有权限
			sysRolePrivilegeMapper.deletePrivilegesByRoleId(roleId);
			
			//2.添加新权限
			if(StringUtils.isNotBlank(priIds)){
				String[] str = priIds.split(",");
				for(String tmp : str){
					int p = NumberUtils.toInt(tmp);
					if(p > 0){
						SysRolePrivilege rp = new SysRolePrivilege();
						rp.setRoleId(roleId);
						rp.setPrivilegeId(p);
						sysRolePrivilegeMapper.insert(rp);
					}
				}
			}
			return true;
		}
		return false;
	}

}
