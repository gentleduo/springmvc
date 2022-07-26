package com.gentleduo.springmvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gentleduo.springmvc.entity.User;
import com.gentleduo.springmvc.service.UserMgrService;

@RestController
@RequestMapping("/usermgr")
public class UserMgrController {

	private static Logger logger = LogManager.getLogger("com.gentleduo");

	@Resource(name = "userMgrServiceImpl")
	private UserMgrService userMgrService;

	@ResponseBody
	@RequestMapping("/adduser")
	public Map<String, String> addUser(@RequestBody User user) {

		logger.info("=======================add user start=======================");
		int res = userMgrService.addUser(user);

		Map<String, String> retMap = new HashMap<String, String>();

		retMap.put("retCode", String.valueOf(res));
		logger.info("=======================add user end=======================");
		return retMap;
	}
}
