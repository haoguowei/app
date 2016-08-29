package com.hao.app.manager.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.hao.app.commons.entity.Constants;
import com.hao.app.commons.enums.ResultCodeEnum;
import com.hao.app.pojo.SysMember;
import com.hao.app.service.SysLogsService;

public class BaseController {
	
	@Autowired
	protected SysLogsService sysLogsService;
	
	//管理员角色ID
	private static final int ADMINROLEID = 1;
	
	/**
	 * 得到登录用户名
	 * @param request
	 * @return
	 */
	public String getCurrentUserName(HttpServletRequest request){
		SysMember user = getCurrentUser(request);
		return user == null ? "" : user.getName();
	}
	
	/**
	 * 得到登录用户
	 * @param request
	 * @return
	 */
	public SysMember getCurrentUser(HttpServletRequest request){
		return (SysMember) request.getSession().getAttribute(Constants.CURRENT_LOGIN_USER);
	}
	
	/**
	 * 得到系统路径
	 * @param request
	 * @return
	 */
	public String getSrcPath(HttpServletRequest request){
		return request.getServletContext().getRealPath("");
	}
	
	/**
	 * 输出提示
	 * @param request
	 * @param msg
	 */
	public String failResult(HttpServletRequest request, ResultCodeEnum resultCode){
		if(resultCode != null){
			request.setAttribute("msg", resultCode);
		}
		return "WEB-INF/error/fail";
	}
	
	/**
	 * 输出提示
	 * @param request
	 * @param msg
	 */
	public void setErrorAttribute(HttpServletRequest request, String msg){
		if(msg != null){
			request.setAttribute("msg", msg);
		}
	}
	
	/**
	 * 成功后返回提示
	 * @param request
	 * @param url
	 * @param title
	 * @return
	 */
	public String successResult(HttpServletRequest request, String title, String url){
		if(StringUtils.isNotBlank(title)){
			request.setAttribute("title", title);
		}
		if(StringUtils.isNotBlank(url)){
			request.setAttribute("url", url);
		}
		return "WEB-INF/error/success";
	}
	
	
	
	
	/**
	 * response写到page
	 * @param response
	 * @param obj
	 * @throws IOException
	 */
	public void writeResponse(HttpServletResponse response, Object obj) throws IOException{
		writeResponse(response, new Gson().toJson(obj));
	}
	
	/**
	 * response写到page
	 * @param response
	 * @param obj
	 * @throws IOException
	 */
	public void writeResponse(HttpServletResponse response, String obj) throws IOException{
		response.getWriter().write(obj);
	}
	
}
