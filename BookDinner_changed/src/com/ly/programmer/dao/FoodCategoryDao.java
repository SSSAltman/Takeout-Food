package com.ly.programmer.dao;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.ly.programmer.entity.FoodCategory;

/*
*菜品分类信息dao
*@author LiYing
*@version 2020年11月3日 上午10:49:18
*
*/
@Repository
public interface FoodCategoryDao {
	public int add(FoodCategory foodCategory);
	public int edit(FoodCategory foodCategory);
	public List<FoodCategory> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(Long id);
	public List<FoodCategory> findAll();
}
