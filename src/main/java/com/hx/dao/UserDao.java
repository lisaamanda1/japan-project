package com.hx.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hx.bean.User;

public interface UserDao {
	List<Map<String, Object>> getUserList(@Param("params")Map<String, Object> params);

	@Delete("DELETE FROM syain_main WHERE SYAIN_ID = #{arg0}")
	int deleteUser(Object id);

	@Select("SELECT * FROM user_auth WHERE BINARY user_id = #{arg0}")
	User getUserByName(String userName);

	void saveAccessLog(@Param("params")Map<String, Object> params);

	void saveUser(@Param("params")Map<String, Object> params);
}
