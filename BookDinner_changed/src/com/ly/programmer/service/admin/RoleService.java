package com.ly.programmer.service.admin;

import java.util.*;

import com.ly.programmer.entity.admin.Role;

//角色role service
public interface RoleService {
	public int add(Role role);
	public int edit(Role role);
	public int delete(Long id);
	public List<Role> findList(Map<String,Object> queryMap);
	public int getTotal(Map<String,Object> queryMap);
	public Role find(Long id);
}
