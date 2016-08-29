package com.hao.app.manager.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hao.app.commons.entity.Page;
import com.hao.app.commons.entity.result.JsonResult;
import com.hao.app.pojo.SysLogs;

/**
 * 系统日志
 * 
 * @author haoguowei
 *
 */
@Controller
@RequestMapping
public class SysLogsController extends BaseController{
	
	@RequestMapping("/initLog.do")
	public String initLog(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "jsp/log";
	}
	
	@RequestMapping("/searchLogs.do")
	public void searchLogs(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		int start = NumberUtils.toInt(request.getParameter("start"));
		int limit = NumberUtils.toInt(request.getParameter("limit"), Page.LIMIT);
		
		JsonResult<SysLogs> result = sysLogsService.searchLogs(name,start,limit);
		writeResponse(response, result);
	}
	
}
