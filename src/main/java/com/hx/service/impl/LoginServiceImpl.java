package com.hx.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.bean.User;
import com.hx.dao.UserDao;
import com.hx.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	UserDao userDao;

    @Override
    public User getUserByName(String getMapByName) {
    	User user = getUserByNameFromDB(getMapByName);
    	return user;
    }
    
    private User getUserByNameFromDB(String userName) {
    	return userDao.getUserByName(userName);
    }

	@Override
	public User getUser() {
		Subject subject= SecurityUtils.getSubject();
    	String username = subject.getPrincipal().toString();
    	return this.getUserByNameFromDB(username);
	}
}
