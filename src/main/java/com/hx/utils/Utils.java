package com.hx.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Utils {
	public static Map<String, Object> getParams(HttpServletRequest request) {
		Map<String, Object> paramMap = new HashMap<String,Object>();
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()){
			String name = names.nextElement();
			paramMap.put(name, request.getParameter(name));
		}
		return paramMap;
	}
}
