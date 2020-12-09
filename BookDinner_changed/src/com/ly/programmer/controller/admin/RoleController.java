package com.ly.programmer.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ly.programmer.entity.admin.Authority;
import com.ly.programmer.entity.admin.Menu;
import com.ly.programmer.entity.admin.Role;
import com.ly.programmer.page.admin.Page;
import com.ly.programmer.service.admin.AuthorityService;
import com.ly.programmer.service.admin.MenuService;
import com.ly.programmer.service.admin.RoleService;

import java.util.*;

//角色Role控制器
@RequestMapping("/admin/role")
@Controller
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private MenuService menuService;
	
	/**
	 * 角色列表页面
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list(ModelAndView model){
		model.setViewName("/role/list");
		return model;
	}
	
	
	/**
	 * 获取角色列表
	 */
	@RequestMapping(value="/list",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getList(Page page,
			@RequestParam(name="name",required=false,defaultValue="") String name
			){
		Map<String, Object> ret = new HashMap<String, Object>();
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("name", name);
		queryMap.put("offset", page.getOffset());
		queryMap.put("pageSize", page.getRows());
		ret.put("rows", roleService.findList(queryMap));
		ret.put("total", roleService.getTotal(queryMap));
		return ret;
	}
	
	/**
	 * 角色添加
	 */
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> add(Role role){
		Map<String, String> ret = new HashMap<String, String>();
		if(role == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的角色信息！");
			return ret;
		}
		if(role.getName()==null){
			ret.put("type", "error");
			ret.put("msg", "请填写角色名称！");
			return ret;
		}
		if(roleService.add(role) <= 0){
			ret.put("type", "error");
			ret.put("msg", "角色添加失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "角色添加成功！");
		return ret;
	}
	
	/**
	 * 角色修改
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> edit(Role role){
		Map<String, String> ret = new HashMap<String, String>();
		if(role == null){
			ret.put("type", "error");
			ret.put("msg", "请填写正确的角色信息！");
			return ret;
		}
		if(role.getName()==null){
			ret.put("type", "error");
			ret.put("msg", "请填写角色名称！");
			return ret;
		}
		if(roleService.edit(role) <= 0){
			ret.put("type", "error");
			ret.put("msg", "角色修改失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "角色修改成功！");
		return ret;
	}
	
	/**
	 * 删除角色信息
	 */
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> delete(Long id){
		Map<String, String> ret = new HashMap<String, String>();
		if(id == null){
			ret.put("type", "error");
			ret.put("msg", "请选择要删除的角色！");
			return ret;
		}
		try {
			if(roleService.delete(id) <= 0){
				ret.put("type", "error");
				ret.put("msg", "删除失败，请联系管理员！");
				return ret;
			}
		} catch (Exception e) {
			// TODO: handle exception
			ret.put("type", "error");
			ret.put("msg", "该角色下存在权限或者用户信息，不能删除！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "角色删除成功！");
		return ret;
	}
	
	/**
	 * 获取所有的菜单信息
	 */
	@RequestMapping(value="/get_all_menu",method=RequestMethod.POST)
	@ResponseBody
	public List<Menu> getAllMenu(){
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("offset", 0);
		queryMap.put("pageSize", 99999);
		return menuService.findList(queryMap);
	}
	
	/**
	 * 添加权限
	 */
	@RequestMapping(value="/add_authority",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> addAuthority(
			@RequestParam(name="ids",required=true) String ids,
			@RequestParam(name="roleId",required=true) Long roleId
			){
		Map<String,String> ret = new HashMap<String, String>();
		if(ids==null){
			ret.put("type", "error");
			ret.put("msg", "请选择相应的权限！");
			return ret;
		}
		if(roleId == null){
			ret.put("type", "error");
			ret.put("msg", "请选择相应的角色！");
			return ret;
		}
		if(ids.contains(",")){
			ids = ids.substring(0,ids.length()-1);
		}
		String[] idArr = ids.split(",");
		if(idArr.length > 0){
			authorityService.deleteByRoleId(roleId);
		}
		for(String id:idArr){
			Authority authority = new Authority();
			authority.setMenuId(Long.valueOf(id));
			authority.setRoleId(roleId);
			authorityService.add(authority);
		}
		ret.put("type", "success");
		ret.put("msg", "权限编辑成功！");
		return ret;
	}
	
	/**
	 * 获取某个角色的所有权限
	 */
	@RequestMapping(value="/get_role_authority",method=RequestMethod.POST)
	@ResponseBody
	public List<Authority> getRoleAuthority(
			@RequestParam(name="roleId",required=true) Long roleId
		){
		List<Authority> result = authorityService.findListByRoleId(roleId);
//		for(Authority authority:result) {
//			System.out.println(authority.toString());
//		}数据库查询没有问题
		return result;
	}
}
