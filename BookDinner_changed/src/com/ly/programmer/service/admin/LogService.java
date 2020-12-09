package com.ly.programmer.service.admin;

import java.util.*;

import org.springframework.stereotype.Service;

import com.ly.programmer.entity.admin.Log;
/**
 * 日志接口
 * @author LiYing
 *
 */
@Service
public interface LogService {
	public int add(Log log);
	public int add(String content);
	public List<Log> findList(Map<String, Object> queryMap);
	public int getTotal(Map<String, Object> queryMap);
	public int delete(String ids);
}
