package com.hx.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.bean.Page;
import com.hx.dao.UserDao;
import com.hx.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao userDao;

	@Override
	public String getUserList(Map<String, Object> params) {
		Page<Map<String, Object>> pageVO = new Page<Map<String, Object>>();
		List<Map<String, Object>> users = userDao.getUserList(params);
		pageVO.setRows(users);
		pageVO.setTotal(users.size());
		return pageVO.toString();
	}

	@Override
	public String deleteUser(Object id) {
		userDao.deleteUser(id);
		return "";
	}

	@Override
	public String saveAccessLog(Map<String, Object> params) {
		params.put("userId", SecurityUtils.getSubject().getPrincipal());
		userDao.saveAccessLog(params);
		return "";
	}

	@Override
	public String saveUser(Map<String, Object> params) throws Exception {
		String NYUUSYA_DATE = params.get("NYUUSYA_DATE").toString();
		Date date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(NYUUSYA_DATE);
		String formatDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
		params.put("NYUUSYA_DATE", formatDate);
		userDao.saveUser(params);
		return "";
	}

}
