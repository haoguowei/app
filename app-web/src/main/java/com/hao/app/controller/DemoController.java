package com.hao.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hao.app.commons.entity.result.JsonResultAjax;
import com.hao.app.commons.error.AppRuntimeException;

@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController{
	
	private final Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	@RequestMapping(value = "/main.do") 
	public String main(String name, Model model) throws AppRuntimeException {
		model.addAttribute("demo", "123");
		return "demo/main";
	}
	
	@ResponseBody
    @RequestMapping("/testAjax.json")
    public JsonResultAjax testAjax(){
		JsonResultAjax json = new JsonResultAjax();
		json.setData("你好，json");
		return json;
		
    }
}
