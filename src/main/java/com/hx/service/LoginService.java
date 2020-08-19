package com.hx.service;

import com.hx.bean.User;

public interface LoginService{

    User getUserByName(String userName);

	User getUser();
}
