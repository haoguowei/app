package com.hao.app.manager.controller;

import com.hao.app.commons.enums.ResultCodeEnum;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AreaController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(AreaController.class);


    @RequestMapping("/initArea.do")
    public String initArea(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "jsp/area/initArea";
    }


    @RequestMapping("/searchArea.do")
    public void searchArea(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		int start = NumberUtils.toInt(request.getParameter(START));
//		int limit = NumberUtils.toInt(request.getParameter(LIMIT), Page.LIMIT);
//
//		JsonResult<SysMember> result = sysMemeberService.queryMemberPageList(start, limit);
//		writeResponse(response, result);
    }

    @RequestMapping("/initAreaEdit.do")
    public String initAreaEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = NumberUtils.toInt(request.getParameter("id"));

//		SysMember member = sysMemeberService.queryByPrimaryKey(id);
//		List<SysRole> roleList = sysRoleService.queryAllRoles();

//		request.setAttribute("roles", roleList);
//		request.setAttribute("member", member);

        return "jsp/area/initAreaEdit";
    }

    @RequestMapping("/saveArea.do")
    public String saveArea(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		int id = NumberUtils.toInt(request.getParameter("hideId"));
//
//		SysMember member = new SysMember();
//		member.setId(id);
//		member.setName(request.getParameter("name"));
//		member.setShowName(request.getParameter("showName"));
//		member.setPhone(request.getParameter("phone"));
//		member.setEmail(request.getParameter("email"));
//		member.setRoleId(NumberUtils.toInt(request.getParameter("roleId")));
//
//		String pwd = request.getParameter("pwd");
//		if(StringUtils.isNotBlank(pwd)){
//			member.setPwd(Md5Util.genMD5Str(pwd));
//		}
//
//		ResultCodeEnum resultCode;
//		if (id == 0) {
//			resultCode = sysMemeberService.insertMember(member);
//		} else {
//			resultCode = sysMemeberService.updateMember(member);
//		}
//
//		if (resultCode.equals(ResultCodeEnum.SUCCESS)) {
//			sysLogsService.writeLog(getCurrentUserName(request), "新增或修改系统用户:" + member.toString());
//			return successResult(request, "用户管理", "initMemberManager.do");
//		} else {
//			return failResult(request, resultCode);
//		}
        return failResult(request, ResultCodeEnum.SUCCESS);
    }
}
