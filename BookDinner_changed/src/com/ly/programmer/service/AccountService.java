package com.ly.programmer.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.ly.programmer.entity.Account;

/*
*客户信息service
*@author LiYing
*@version 2020年11月4日 下午8:06:40
*
*/
@Service
public interface AccountService {
	public int add(Account account);
	public int edit(Account account);
	public List<Account> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(Long id);
	public Account findByName(String name);
}
