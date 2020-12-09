package com.ly.programmer.dao.admin;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ly.programmer.entity.admin.Log;

/*
*
*@author LiYing
*@version 2020年11月1日 下午3:59:42
*
*/
@Repository
public interface LogDao {
	public int add(Log log);
	public List<Log> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);
}
