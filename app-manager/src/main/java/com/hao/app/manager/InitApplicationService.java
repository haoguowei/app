package com.hao.app.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.hao.app.commons.entity.Constants;
import com.hao.app.service.SysPrivilegeService;

/**
 * spring启动后执行
 * 
 * 应用初始化工作
 * 
 * @author haoguowei
 *
 */
@Service
public class InitApplicationService implements ApplicationListener<ContextRefreshedEvent> {
	
	private final Logger logger = LoggerFactory.getLogger(AppHandlerExceptionResolver.class);
	
	@Autowired
	private SysPrivilegeService sysPrivilegeService;
	
	private boolean isStart = false;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(!isStart){
			Constants.allPrivilegeSet = sysPrivilegeService.queryAllPrivilegeUrls();
			logger.info("加载系统所有权限：{}", Constants.allPrivilegeSet);
			isStart = true;
		}
	}

}
