package com.hao.app.manager.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.entity.result.JsonResultAjax;
import com.hao.app.pojo.SysMenu;
import com.hao.app.pojo.SysPrivilege;
import com.hao.app.service.SysMenuService;
import com.hao.app.service.SysPrivilegeService;
import com.hao.app.service.SysRolePrivilegeService;

/**
 * 权限管理
 * @author haoguowei
 *
 */
@Controller
public class SysPrivilegeController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(SysPrivilegeController.class);
	
	@Autowired
	private SysPrivilegeService sysPrivilegeService;
	
	@Autowired
	private SysRolePrivilegeService sysRolePrivilegeService;
	
	@Autowired
	private SysMenuService sysMenuService;
	

	@RequestMapping("/initPrivileges.do")
	public String initPrivileges(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int menuId = NumberUtils.toInt(request.getParameter("menuId"));
		SysMenu menu = sysMenuService.queryByPrimaryKey(menuId);
		request.setAttribute("menu", menu);
		return "jsp/privileges";
	}
	
	@RequestMapping("/searchPrivileges.do")
	public void searchPrivileges(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int menuId = NumberUtils.toInt(request.getParameter("menuId"));
		List<SysPrivilege> ls = sysPrivilegeService.queryPrivilegeByMenuId(menuId);
		JsonResult<SysPrivilege> result = new JsonResult<SysPrivilege>(ls.size(), ls);
		writeResponse(response, result);
	}
	
	/**
	 * 查询角色没有的权限
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/searchPrivilegesWithoutRoles.do")
	public void searchPrivilegesWithoutRoles(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int roleId = NumberUtils.toInt(request.getParameter("role"));
		JsonResult<SysPrivilege> result = sysPrivilegeService.queryNoPrivilegesByRoleId(roleId);
		writeResponse(response, result);
	}
	
	@RequestMapping("/searchRolePrivileges.do")
	public void searchPrivilegesList(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int role = NumberUtils.toInt(request.getParameter("role"));
		List<SysPrivilege> ls = sysPrivilegeService.queryPrivilegeByRoleId(role);
		
		JsonResult<SysPrivilege> result = new JsonResult<SysPrivilege>(ls.size(), ls);
		writeResponse(response, result);
	}
	
	@RequestMapping("/saveRolePrivileges.do")
	public void saveRolePrivileges(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		
		int role = NumberUtils.toInt(request.getParameter("role"));
		String priIds = request.getParameter("priIds");
		
		boolean result = sysRolePrivilegeService.saveRolePrivileges(role, priIds);
		sysLogsService.writeLog(getCurrentUserName(request),"设置角色权限，角色："+role+";权限："+priIds);
		
		writeResponse(response, new JsonResultAjax(result));
	}
	
	@RequestMapping("/getPrivilegeById.do")
	public void getPrivilegeById(HttpServletRequest request,HttpServletResponse response) throws IOException {
		int privilegeId = NumberUtils.toInt(request.getParameter("privilegeId"));
		SysPrivilege privilege = sysPrivilegeService.queryByPrimaryKey(privilegeId);
		writeResponse(response, privilege);
	}
	
	@RequestMapping("/savePrivilege.do")
	public void savePrivilege(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SysPrivilege privilege = new SysPrivilege();
		privilege.setId(NumberUtils.toInt(request.getParameter("pId")));
		privilege.setMenuId(NumberUtils.toInt(request.getParameter("menuId")));
		privilege.setName(request.getParameter("pName"));
		privilege.setUrl(request.getParameter("pUrl"));
		
		boolean result = sysPrivilegeService.savePrivilege(privilege);
		
		sysLogsService.writeLog(getCurrentUserName(request), "保存权限记录，result : " + result + ";privilege：" + privilege.toString());

		writeResponse(response, new JsonResultAjax(result));
	}
	
	@RequestMapping("/deletePrivilege.do")
	public void deletePrivilege(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int privilegeId = NumberUtils.toInt(request.getParameter("privilegeId"));
		boolean result = sysPrivilegeService.deletePrivilege(privilegeId);
		sysLogsService.writeLog(getCurrentUserName(request), "删除权限记录，result : " + result + ";privilegeId：" + privilegeId);
		writeResponse(response, new JsonResultAjax(result));
	}

	
}
