package com.ly.programmer.controller.home;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ly.programmer.entity.Food;
import com.ly.programmer.entity.FoodCategory;
import com.ly.programmer.service.FoodCategoryService;

/*
*前台首页控制器
*@author LiYing
*@version 2020年11月5日 下午6:39:54
*
*/
@RequestMapping("/home/index")
@Controller
public class IndexController {
	
	@Autowired
	private FoodCategoryService foodCategoryService;
	
	/**
	 * 前台首页
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model){
		model.setViewName("home/index/index");
		return model;
	}
	
	/**
	 * 获取所有的菜品信息
	 */
	@RequestMapping(value="/get_food_list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getFoodList(){
		Map<String, Object> ret = new HashMap<String, Object>();
		List<FoodCategory> findAll = foodCategoryService.findAll();
		Map<String,List<Food>> content = new HashMap<String, List<Food>>();
		for(FoodCategory foodCategory:findAll){
			content.put(foodCategory.getName(), foodCategory.getFoodList());
		}
		ret.put("type", "success");
		ret.put("content", content);
		return ret;
	}
}
