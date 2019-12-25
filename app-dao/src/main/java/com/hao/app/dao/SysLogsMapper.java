package com.hao.app.dao;

import java.util.List;

import com.hao.app.commons.entity.param.SysLogsQueryParam;
import com.hao.app.pojo.SysLogs;

/**
 * 对应表：sys_logs
 * 
 * @author haoguowei
 *
 */
public interface SysLogsMapper {

	/**
	 * 插入记录
	 * @param sysLogs
	 * @return
	 */
    int insert(SysLogs sysLogs);

    /**
     * 分页查询日志记录
     * @param queryParam
     * @return
     */
    List<SysLogs> queryLogsPageList(SysLogsQueryParam queryParam);
    
    /**
     * 查询日志记录count
     * @param queryParam
     * @return
     */
    int queryLogsCount(SysLogsQueryParam queryParam);
}