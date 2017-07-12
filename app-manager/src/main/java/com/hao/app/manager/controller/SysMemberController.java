package com.hao.app.manager.controller;

import com.hao.app.commons.entity.Page;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.entity.result.JsonResultAjax;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.commons.utils.Md5Util;
import com.hao.app.pojo.SysMember;
import com.hao.app.pojo.SysRole;
import com.hao.app.service.SysMemeberService;
import com.hao.app.service.SysRoleService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class SysMemberController extends BaseController {
	
	@Autowired
	private SysMemeberService sysMemeberService;

	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping("/initMemberManager.do")
	public String initMemberManager(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "jsp/member";
	}

	@RequestMapping("/initEditMember.do")
	public String initEditMember(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = NumberUtils.toInt(request.getParameter("id"));

		SysMember member = sysMemeberService.queryByPrimaryKey(id);
		List<SysRole> roleList = sysRoleService.queryAllRoles();

		request.setAttribute("roles", roleList);
		request.setAttribute("member", member);

		return "jsp/memberEdit";
	}

	@RequestMapping("/initEditMemberPWD.do")
	public String initEditMemberPWD(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = NumberUtils.toInt(request.getParameter("id"));

		SysMember member = sysMemeberService.queryByPrimaryKey(id);
		request.setAttribute("member", member);

		return "jsp/memberEditPWD";
	}

	@RequestMapping("/searchMembers.do")
	public void searchMembers(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int start = NumberUtils.toInt(request.getParameter(START));
		int limit = NumberUtils.toInt(request.getParameter(LIMIT), Page.LIMIT);

		JsonResult<SysMember> result = sysMemeberService.queryMemberPageList(start, limit);
		writeResponse(response, result);
	}

	@RequestMapping("/saveMember.do")
	public String saveClasses(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = NumberUtils.toInt(request.getParameter("hideId"));

		SysMember member = new SysMember();
		member.setId(id);
		member.setName(request.getParameter("name"));
		member.setShowName(request.getParameter("showName"));
		member.setPhone(request.getParameter("phone"));
		member.setEmail(request.getParameter("email"));
		member.setRoleId(NumberUtils.toInt(request.getParameter("roleId")));

		String pwd = request.getParameter("pwd");
		if(StringUtils.isNotBlank(pwd)){
			member.setPwd(Md5Util.genMD5Str(pwd));
		}

		ResultCodeEnum resultCode;
		if (id == 0) {
			resultCode = sysMemeberService.insertMember(member);
		} else {
			resultCode = sysMemeberService.updateMember(member);
		}
		
		if (resultCode.equals(ResultCodeEnum.SUCCESS)) {
			sysLogsService.writeLog(getCurrentUserName(request), "新增或修改系统用户:" + member.toString());
			return successResult(request, "用户管理", "initMemberManager.do");
		} else {
			return failResult(request, resultCode);
		}
	}

	@RequestMapping("/saveMemberPwd.do")
	public String saveMemberPwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = NumberUtils.toInt(request.getParameter("hideId"));
		String oldpwd = request.getParameter("oldpwd");
		String pwd = request.getParameter("pwd");
		
		SysMember member = sysMemeberService.queryByPrimaryKey(id);

		String oldpwdMd5 = Md5Util.genMD5Str(oldpwd);
		String pwdMd5 = Md5Util.genMD5Str(pwd);

		if (member != null && !member.getPwd().equals(oldpwdMd5)) {
			return failResult(request, ResultCodeEnum.FAIL_OLDPWD);
		}
		
		if (member != null && member.getPwd().equals(oldpwdMd5)) {
			member.setPwd(pwdMd5);
			sysMemeberService.updateMember(member);
			sysLogsService.writeLog(getCurrentUserName(request), "修改用户密码:" + member.toString());
			return successResult(request, "用户管理", "initMemberManager.do");
		}
		
		return "WEB-INF/error/fail";
	}
	
	@RequestMapping("/saveMemberImgs.do")
	public String saveMemberImgs(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = NumberUtils.toInt(request.getParameter("id"));
		String imgs = request.getParameter("imgs");
		
		try {
			if(id > 0 && StringUtils.isNotBlank(imgs)){
				sysMemeberService.updateMemberImgs(id, imgs);
				sysLogsService.writeLog(getCurrentUserName(request), "修改用户头像:id=" + id + ",imgs=" + imgs);
				return successResult(request, "用户管理", "initMemberManager.do");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "WEB-INF/error/fail";
	}

	@RequestMapping("/initEditMemberValid.do")
	public void initEditMemberValid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = NumberUtils.toInt(request.getParameter("id"));
		int valid = NumberUtils.toInt(request.getParameter("value"));
		
		boolean result = sysMemeberService.updateMemberValid(id, valid);
		writeResponse(response, new JsonResultAjax(result));
	}
}
