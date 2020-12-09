package com.ly.programmer.dao;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.ly.programmer.entity.Account;

/*
* 客户信息dao
*@author LiYing
*@version 2020年11月4日 下午8:07:43
*
*/
@Repository
public interface AccountDao {
	public int add(Account account);
	public int edit(Account account);
	public List<Account> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(Long id);
	public Account findByName(String name);
}
