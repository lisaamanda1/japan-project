package com.hx.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hx.service.UserService;
import com.hx.utils.Utils;


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping("/getUserList")
	public String getUserList(HttpServletRequest request) {
		Map<String, Object> params = Utils.getParams(request);
		return userService.getUserList(params);
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(HttpServletRequest request) throws Exception {
		Map<String, Object> params = Utils.getParams(request);
		userService.saveUser(params);
		return "";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request) {
		Map<String, Object> params = Utils.getParams(request);
		userService.deleteUser(params.get("id"));
		return "";
	}
	
	@RequestMapping("/saveAccessLog")
	public String saveAccessLog(HttpServletRequest request) {
		Map<String, Object> params = Utils.getParams(request);
		return userService.saveAccessLog(params);
	}
	
}
