package com.ly.programmer.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ly.programmer.dao.AccountDao;
import com.ly.programmer.entity.Account;
import com.ly.programmer.service.AccountService;

/*
*客户信息实现类
*@author LiYing
*@version 2020年11月4日 下午8:08:38
*
*/
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	@Override
	public int add(Account account) {
		// TODO Auto-generated method stub
		return accountDao.add(account);
	}

	@Override
	public int edit(Account account) {
		// TODO Auto-generated method stub
		return accountDao.edit(account);
	}

	@Override
	public List<Account> findList(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return accountDao.findList(queryMap);
	}

	@Override
	public Integer getTotal(Map<String, Object> queryMap) {
		// TODO Auto-generated method stub
		return accountDao.getTotal(queryMap);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return accountDao.delete(id);
	}

	@Override
	public Account findByName(String name) {
		// TODO Auto-generated method stub
		return accountDao.findByName(name);
	}
	
	

}
