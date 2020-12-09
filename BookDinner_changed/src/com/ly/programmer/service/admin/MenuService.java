package com.ly.programmer.service.admin;

import java.util.*;

import org.springframework.stereotype.Service;

import com.ly.programmer.entity.admin.Menu;

//菜单管理
@Service
public interface MenuService {
	public int add(Menu menu);
	public List<Menu> findList(Map<String,Object> queryMap);
	public List<Menu> findTopList();
	public int getTotal(Map<String,Object> queryMap);
	public int edit(Menu menu);
	public int delete(Long id);
	public List<Menu> findChildrenList(Long parentId);
	public List<Menu> findListByIds(String ids);
}
