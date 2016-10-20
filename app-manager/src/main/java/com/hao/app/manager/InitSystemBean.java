package com.hao.app.manager;

import java.util.Set;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import com.hao.app.commons.entity.Constants;
import com.hao.app.commons.utils.PropertiesUtil;
import com.hao.app.commons.utils.WebUtils;
import com.hao.app.service.SysPrivilegeService;

/**
 * 系统启动加载
 *
 * @author yanwei
 * @since 1.0.0
 */
public class InitSystemBean implements InitializingBean, ServletContextAware{
	
	private final Logger logger = LoggerFactory.getLogger(InitSystemBean.class);
	
	@Autowired
	private SysPrivilegeService sysPrivilegeService;
	

	@Override
	public void setServletContext(ServletContext servletContext) {
		Set<String> allPrivileges = sysPrivilegeService.reLoadAllPrivilegeSet();
		logger.info("加载系统所有权限={}", allPrivileges);
		
		Constants.IMG_URL = PropertiesUtil.getValue("conf.img.url");
		Constants.IMG_PATH = PropertiesUtil.getValue("conf.img.path");
		
		//设置页面用的全局常量
		servletContext.setAttribute("IMAGEURL", Constants.IMG_URL);
		servletContext.setAttribute("WebUtil", new WebUtils());
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
	}
}
