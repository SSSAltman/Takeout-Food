package com.ly.programmer.dao.admin;

import java.util.List;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ly.programmer.entity.admin.Role;
//��ɫrole dao
@Repository
public interface RoleDao {
	public int add(Role role);
	public int edit(Role role);
	public int delete(Long id);
	public List<Role> findList(Map<String,Object> queryMap);
	public int getTotal(Map<String,Object> queryMap);
	public Role find(Long id);
}
