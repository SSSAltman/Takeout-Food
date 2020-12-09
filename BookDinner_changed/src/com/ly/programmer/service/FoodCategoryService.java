package com.ly.programmer.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.ly.programmer.entity.FoodCategory;
/*
*菜品分类信息service
*@author LiYing
*@version 2020年11月3日 上午10:45:36
*
*/
@Service
public interface FoodCategoryService {
	public int add(FoodCategory foodCategory);
	public int edit(FoodCategory foodCategory);
	public List<FoodCategory> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(Long id);
	public List<FoodCategory> findAll();
}
