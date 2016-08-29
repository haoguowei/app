package com.hao.app.manager.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hao.app.commons.entity.Constants;
import com.hao.app.pojo.SysMember;

/**
 * 登录拦截器
 * 
 * @author haoguowei
 * 
 */
public class LogInInterceptor implements HandlerInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(LogInInterceptor.class);

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		SysMember currentUser = (SysMember) request.getSession().getAttribute(Constants.CURRENT_LOGIN_USER);
		if(currentUser != null && StringUtils.isNotBlank(currentUser.getName())){
			return true;
		}
		
		logger.error("Not Login!");
		response.sendRedirect("login.jsp"); 
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
