package com.tgb.mapper;

import java.util.List;

import com.tgb.model.User;

public interface UserMapper {

	void save(User user);
	boolean update(User user);
	boolean delete(String id);
	User findById(String id);
	List<User> findAll();
}
