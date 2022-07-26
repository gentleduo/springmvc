package com.gentleduo.springmvc.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommonController {

	static Logger logger = LogManager.getLogger("com.gentleduo");

	@RequestMapping(value = "/error403", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String error403() {

		return "{\"msg\":\"您无权访问此页面！\",\"code\":\"10001\"}";
	}

	@RequestMapping(value = "/error404", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String error404() {

		return "{\"msg\":\"您查找的页面不存在！\",\"code\":\"10002\"}";
	}

	@RequestMapping(value = "/error500", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String error500() {

		return "{\"msg\":\"服务器内部错误！\",\"code\":\"10003\"}";
	}
}
