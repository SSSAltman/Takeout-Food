package com.ly.programmer.service.admin;

import java.util.*;
import org.springframework.stereotype.Service;

import com.ly.programmer.entity.admin.Authority;

//权限service
@Service
public interface AuthorityService {
	public int add(Authority authority);
	public int deleteByRoleId(Long roleId);
	public List<Authority> findListByRoleId(Long roleId);
}
