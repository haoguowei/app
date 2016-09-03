package com.hao.app.manager.interceptor;

import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hao.app.commons.entity.Constants;
import com.hao.app.commons.entity.result.JsonResultAjax;
import com.hao.app.pojo.SysMember;

/**
 * 权限拦截器
 * 
 * @author haoguowei
 * 
 */
public class PrivilegeInterceptor implements HandlerInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(PrivilegeInterceptor.class);
	
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		String path = url.substring(url.lastIndexOf("/")+1, url.lastIndexOf(Constants.URLSUFFIX)) + Constants.URLSUFFIX;
		
		//当前用户
		SysMember currentUser = (SysMember) request.getSession().getAttribute(Constants.CURRENT_LOGIN_USER);
		
		//系统所有权限
		Set<String> allPrivileges = Constants.allPrivilegeSet;
		if(allPrivileges.contains(path)){
			if(currentUser != null && currentUser.getPriUrls() != null && currentUser.getPriUrls().contains(path)){
				return true;
			}
		}else{
			//只拦截（allPrivileges）系统权限中定义的权限
			return true;
		}
		
		logger.error("用户{}执行{}权限不足！", currentUser.getName(), path);
		if (!(request.getHeader("accept").contains("application/json") 
				|| (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest") ))) {
			response.sendRedirect("initNoPrivileges.jsp"); 
			return false;
		}else{
			//json错误请求处理
			JsonResultAjax result = new JsonResultAjax(false, "权限不足！");
			
			String json = new Gson().toJson(result);
			PrintWriter pw = response.getWriter();
			pw.write(json);
			pw.flush();
			
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

	
}
