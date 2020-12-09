package com.ly.programmer.entity;
import java.util.*;

import org.springframework.stereotype.Component;

import com.ly.programmer.entity.Food;
/*
*菜品分类信息实体
*@author LiYing
*@version 2020年11月3日 上午10:38:26
*
*/
@Component
public class FoodCategory {
	private Long id;
	private String name;//菜品分类名称
	private List<Food> foodList;//该分类下的所有菜品信息
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Food> getFoodList() {
		return foodList;
	}
	public void setFoodList(List<Food> foodList) {
		this.foodList = foodList;
	}
	
}
