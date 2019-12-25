package com.hao.app.service;

import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.pojo.SysLogs;

/**
 * 系统日志处理
 * 
 * @author haoguowei
 *
 */
public interface SysLogsService {

	/**
	 * 分页查询日志记录
	 * @param name
	 * @param start
	 * @param limit
	 * @return
	 */
	JsonResult<SysLogs> searchLogs(String name, int start, int limit);

	/**
	 * 写日志
	 * @param user
	 * @param string
	 */
	void writeLog(String user, String string);

}
