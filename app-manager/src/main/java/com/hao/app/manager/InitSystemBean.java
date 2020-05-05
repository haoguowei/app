package com.hao.app.manager;

import com.hao.app.commons.entity.Constants;
import com.hao.app.commons.utils.PropertiesUtils;
import com.hao.app.commons.utils.WebUtils;
import com.hao.app.service.SysPrivilegeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.Set;

/**
 * 系统启动加载
 *
 * @author yanwei
 * @since 1.0.0
 */
public class InitSystemBean implements InitializingBean, ServletContextAware {

	private final Logger logger = LoggerFactory.getLogger(InitSystemBean.class);

	@Autowired
	private SysPrivilegeService sysPrivilegeService;

	@Override
	public void setServletContext(ServletContext servletContext) {
		Set<String> allPrivileges = sysPrivilegeService.reLoadAllPrivilegeSet();
		logger.info("加载系统所有权限:{}", allPrivileges);
		
		// 设置页面用的全局常量
		servletContext.setAttribute("IMAGEURL", PropertiesUtils.getProperty(Constants.CONFIG_KEY_UPFILE_URL));
		servletContext.setAttribute("WebUtils", new WebUtils());
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}
}
