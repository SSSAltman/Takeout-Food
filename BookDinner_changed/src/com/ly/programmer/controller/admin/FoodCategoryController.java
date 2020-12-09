package com.ly.programmer.controller.admin;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ly.programmer.entity.FoodCategory;
import com.ly.programmer.page.admin.Page;
import com.ly.programmer.service.FoodCategoryService;

/*
* 菜品分类信息后台管理控制器
*@author LiYing
*@version 2020年11月3日 上午10:14:24
*
*/
@RequestMapping("/admin/food_category")
@Controller
public class FoodCategoryController {
	
	@Autowired
	private FoodCategoryService foodCategoryService;
	
	/**
	 * 菜品分类信息列表页面
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("food_category/list");
		return model;
	}
	
	/**
	 * 模糊分页查询菜品信息
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(
			@RequestParam(name="name",defaultValue="") String name,
			Page page
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", foodCategoryService.findList(queryMap));
		ret.put("total", foodCategoryService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 菜品分类信息添加
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(FoodCategory foodCategory){
		Map<String, String> ret = new HashMap<String, String>();
		if(foodCategory == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的分类信息!");
			return ret;
		}
		if(foodCategory.getName() == null){
			ret.put("type", "error");
			ret.put("msg", "菜品分类信息名称不能为空!");
			return ret;
		}
		if(foodCategoryService.add(foodCategory) <= 0){
			ret.put("type", "error");
			ret.put("msg", "菜品分类信息添加失败，请联系管理员!");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		return ret;
	}
	
	/**
	 * 菜品分类信息编辑
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(FoodCategory foodCategory){
		Map<String, String> ret = new HashMap<String, String>();
		if(foodCategory == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的分类信息!");
			return ret;
		}
		if(foodCategory.getName() == null){
			ret.put("type", "error");
			ret.put("msg", "菜品分类信息名称不能为空!");
			return ret;
		}
		if(foodCategoryService.edit(foodCategory) <= 0){
			ret.put("type", "error");
			ret.put("msg", "菜品分类信息修改失败，请联系管理员!");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "添加成功！");
		return ret;
	}
	
	/**
	 * 菜品分类信息删除
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(Long id){
		Map<String, String> ret = new HashMap<String, String>();
		if(id == null){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的分类信息!");
			return ret;
		}
		try {
			if(foodCategoryService.delete(id) <= 0){
				ret.put("type", "error");
				ret.put("msg", "菜品分类信息删除失败，请联系管理员!");
				return ret;
			}
		} catch (Exception e) {
			// TODO: handle exception
			ret.put("type", "error");
			ret.put("msg", "该菜品分类信息下存在菜品信息，请先删除相关菜品信息!");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "删除成功！");
		return ret;
	}
}
