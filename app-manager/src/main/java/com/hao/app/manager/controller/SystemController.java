package com.hao.app.manager.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hao.app.commons.entity.Constants;
import com.hao.app.commons.entity.TreeNodeMode;
import com.hao.app.commons.utils.Md5Util;
import com.hao.app.pojo.SysMember;
import com.hao.app.service.SysMemeberService;
import com.hao.app.service.SysMenuService;
import com.hao.app.service.SysPrivilegeService;


@Controller
public class SystemController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(SystemController.class);
	
	@Autowired
	private SysMemeberService sysMemeberService;
	
	@Autowired
	private SysPrivilegeService sysPrivilegeService;
	
	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * 进入主页
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/initMain.do")
	public String initMain(HttpServletRequest request,HttpServletResponse response) throws IOException {
		return "main";
	}
	
	/**
	 * 提交登录，记录登录信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login.do")
	public String login(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String username = request.getParameter("loginName");
		String pwd = request.getParameter("loginPwd");
		
		SysMember member = sysMemeberService.getMemberByName(username);
		
		//登录成功
		if(member != null && member.getValid() == 0 && member.getPwd().equals(Md5Util.genMD5Str(pwd))){
			logger.info("{},登录成功!", username);
			
			//当前用户的权限
			List<String> priUrls = sysPrivilegeService.queryPrivilegeURLByRoleId(member.getRoleId());
			member.setPriUrls(priUrls);
			
			//保存当前用户信息到session
			request.getSession().setAttribute(Constants.CURRENT_LOGIN_USER, member);
		
			sysLogsService.writeLog(getCurrentUserName(request), "用户登录");
			response.sendRedirect("initMain.do");
			return null;
		}else{
			//登录失败
			request.setAttribute("info", "用户名或密码错误！");
			return "login";
		}
	}
	
	/**
	 * 退出系统
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/quitSystem.do")
	public String quitSystem(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.getSession().setAttribute(Constants.CURRENT_LOGIN_USER, null);
		return "login";
	}
	
	/**
	 * 左侧菜单树的数据
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/getSysMenuTree.do")
	public void getSysMenuTree(HttpServletRequest request,HttpServletResponse response) throws IOException {
		SysMember user = getCurrentUser(request);
		if(user == null){
			logger.error("currentUser is null!");
			return;
		}
		
		//得到用户的权限url
		List<String> priList = user.getPriUrls();
		
		//得到所有目录树数据
		List<TreeNodeMode> all = sysMenuService.getMenuTree();
		
		//只返回有权限的节点
		List<TreeNodeMode> result = new ArrayList<TreeNodeMode>();
		
		for(TreeNodeMode node : all){
			if(!node.isLeaf()){
				List<TreeNodeMode> childs = node.getChildren();
				List<TreeNodeMode> newChilds = new ArrayList<TreeNodeMode>();
				for(TreeNodeMode tmp : childs){
					if(priList.contains(tmp.getHref())){
						newChilds.add(tmp);
					}
				}
				if(newChilds.size() > 0){
					node.setChildren(newChilds);
					result.add(node);
				}
			}else{
				if(priList.contains(node.getHref())){
					result.add(node);
				}
			}
		}
		writeResponse(response, result);
	}
		
}
