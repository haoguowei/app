package com.hao.app.manager;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.hao.app.commons.entity.result.JsonResultAjax;
import com.hao.app.commons.error.AppAjaxRuntimeException;
import com.hao.app.commons.error.AppRuntimeException;

/**
 * 统一异常处理
 * @author haoguowei
 *
 */
public class AppHandlerExceptionResolver implements HandlerExceptionResolver {
	
	private final Logger logger = LoggerFactory.getLogger(AppHandlerExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		try {
			if (ex instanceof AppRuntimeException) {
				return new ModelAndView("error/500");
			}
			else if (ex instanceof AppAjaxRuntimeException) {
				//错误提示
				JsonResultAjax result = new JsonResultAjax(false, ex.getMessage());
				
				PrintWriter pw = response.getWriter();
				pw.write(JSONObject.toJSONString(result));
				pw.flush();
				
				return null;
			}
			else{
				return new ModelAndView("error/500");
			}
		}
		catch (Exception handlerException) {
			logger.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception", handlerException);
		}
		return null;
	}

}
