package com.ly.programmer.service.admin;

import org.springframework.stereotype.Service;

import com.ly.programmer.entity.admin.User;

import java.util.*;

//User用户
@Service
public interface UserService {
	public User findByUsername(String username);
	public int add(User user);
	public int edit(User user);
	public int editPassword(User user);
	public int delete(String ids);
	public List<User> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
}
