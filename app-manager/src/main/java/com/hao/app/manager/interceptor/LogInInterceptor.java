package com.hao.app.manager.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.hao.app.commons.entity.Constants;
import com.hao.app.commons.entity.result.JsonResultAjax;
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
		
		if (!(request.getHeader("accept").contains("application/json") 
				|| (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").contains("XMLHttpRequest") ))) {
			response.sendRedirect("login.jsp"); 
			return false;
		}else{
			//json错误请求处理
			JsonResultAjax result = new JsonResultAjax(false, "请先登录！");
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
