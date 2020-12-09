package com.ly.programmer.dao;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.ly.programmer.entity.Food;

/*
*菜品信息dao
*@author LiYing
*@version 2020年11月3日 下午7:58:17
*
*/
@Repository
public interface FoodDao {
	public int add(Food food);
	public int edit(Food food);
	public List<Food> findList(Map<String, Object> queryMap);
	public Integer getTotal(Map<String, Object> queryMap);
	public int delete(Long id);
	public Food find(Long id);
	public int updateSels(Long id,Long num);
}
