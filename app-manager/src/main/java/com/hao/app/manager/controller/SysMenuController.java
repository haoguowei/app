package com.hao.app.manager.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hao.app.commons.entity.TreeNodeMode;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.commons.entity.result.JsonResultAjax;
import com.hao.app.pojo.SysMember;
import com.hao.app.pojo.SysMenu;
import com.hao.app.service.SysMenuService;

@Controller
public class SysMenuController extends BaseController{
	
	private Logger logger = LoggerFactory.getLogger(SysMenuController.class);
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping("/initMenu.do")
	public String initMenu(HttpServletRequest request,HttpServletResponse response) throws IOException {
		return "jsp/menu";
	}
	
	@RequestMapping("/getMenuById.do")
	public void getMenuById(HttpServletRequest request,HttpServletResponse response) throws IOException {
		int menuId = NumberUtils.toInt(request.getParameter("menuId"));
		SysMenu menu = sysMenuService.queryByPrimaryKey(menuId);
		writeResponse(response, menu);
	}
	
	@RequestMapping("/searchMenus.do")
	public void searchMenus(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<SysMenu> ls = sysMenuService.getMenuList();
		JsonResult<SysMenu> result = new JsonResult<SysMenu>(ls.size(), ls);
		writeResponse(response, result);
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
	
	@RequestMapping("/saveMenu.do")
	public void saveMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SysMenu menu = new SysMenu();
		menu.setId(NumberUtils.toInt(request.getParameter("menuId")));
		menu.setName(request.getParameter("menuName"));
		menu.setParent(NumberUtils.toInt(request.getParameter("menuParentId")));
		menu.setSort(NumberUtils.toInt(request.getParameter("menuSort")));
		if(menu.getParent() == 0){
			menu.setUrl("#");
		}else{
			menu.setUrl(request.getParameter("menuUrl"));
		}

		boolean result = sysMenuService.saveMenu(menu);
		
		sysLogsService.writeLog(getCurrentUserName(request), "保存菜单，result : " + result + ";menu：" + menu.toString());

		writeResponse(response, new JsonResultAjax(result));
	}
	
	@RequestMapping("/deleteMenu.do")
	public void deleteMenu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int menuId = NumberUtils.toInt(request.getParameter("menuId"));
		boolean result = sysMenuService.deleteMenu(menuId);
		sysLogsService.writeLog(getCurrentUserName(request), "删除菜单，result : " + result + ";menuId：" + menuId);
		writeResponse(response, new JsonResultAjax(result));
	}
		
}
