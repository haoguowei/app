package com.hao.app.manager.interceptor;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hao.app.commons.entity.Constants;
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
		logger.info("用户请求URL={}", path);
		
		//系统所有权限
		Set<String> allPrivileges = Constants.allPrivilegeSet;
		if(allPrivileges.contains(path)){
			//当前用户的权限
			SysMember currentUser = (SysMember) request.getSession().getAttribute(Constants.CURRENT_LOGIN_USER);
			if(currentUser != null && currentUser.getPriUrls() != null && currentUser.getPriUrls().contains(path)){
				return true;
			}
		}else{
			//只拦截（allPrivileges）系统所有权限中定义的权限
			return true;
		}
		
		response.sendRedirect("initNoPrivileges.jsp"); 
		return false;
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
