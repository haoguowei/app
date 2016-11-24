package com.hao.app.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hao.app.commons.entity.param.SysLogsQueryParam;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.dao.SysLogsMapper;
import com.hao.app.pojo.SysLogs;
import com.hao.app.service.SysLogsService;

/**
 * 系统日志处理
 * 
 * @author haoguowei
 *
 */
@Service
public class SysLogsServiceImpl implements SysLogsService{
	
	@Autowired
	private SysLogsMapper sysLogsMapper;

	/**
	 * 分页查询日志记录
	 */
	@Override
	public JsonResult<SysLogs> searchLogs(String name, int start, int limit) {
		SysLogsQueryParam queryParam = new SysLogsQueryParam(start, limit);
		
		if(StringUtils.isNotBlank(name)){
			queryParam.setName(name);
		}
		
		int count = sysLogsMapper.queryLogsCount(queryParam);
		List<SysLogs> list = sysLogsMapper.queryLogsPageList(queryParam);
		
		return new JsonResult<SysLogs>(count, list);
	}

	/**
	 * 写日志
	 * @param user
	 * @param logs
	 */
	@Override
	public void writeLog(String user, String logs){
		SysLogs sysLogs = new SysLogs();
		sysLogs.setOperator(user);
		sysLogs.setOperatorTime(new Date());
		sysLogs.setDescription(logs);
		sysLogsMapper.insert(sysLogs);
	}

}
