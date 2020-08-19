package com.hx.service;

import java.util.Map;

public interface UserService {
	String getUserList(Map<String, Object> params);

	String deleteUser(Object id);

	String saveAccessLog(Map<String, Object> params);
}
