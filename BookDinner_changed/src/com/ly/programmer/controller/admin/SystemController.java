package com.ly.programmer.controller.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.servlet.http.*;

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
import com.ly.programmer.entity.admin.User;
import com.ly.programmer.service.admin.*;
import com.ly.programmer.util.CpachaUtil;
import com.ly.programmer.util.MenuUtil;

/*系统操作类控制器W
* */
@Controller
@RequestMapping("/system")
public class SystemController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private LogService logService;
	
	//系统登陆后欢迎页
    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public ModelAndView welcome(ModelAndView model){
        model.setViewName("system/welcome");
        System.out.println("爷好好的，别nm催了");
    	return model;
    }
    //系统登陆后主页
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(ModelAndView model,HttpServletRequest request){
    	List<Menu> userMenus = (List<Menu>)request.getSession().getAttribute("userMenus");
		model.addObject("topMenuList", MenuUtil.getAllTopMenu(userMenus));
		model.addObject("secondMenuList", MenuUtil.getAllSecondMenu(userMenus));
    	model.setViewName("system/index");
        model.addObject("name","model啥事没有");
        System.out.println("爷好好的，别nm催了");
    	return model;
    }
    
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login(ModelAndView model) {
    	model.setViewName("system/login");
    	return model;
    }
    
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> loginAct(User user,String cpacha,HttpServletRequest request){
    	
    	Map<String,String> ret = new HashMap<String,String>();
    	
    	//System.out.println(user.toString());
    	if(user == null) {
    		ret.put("type", "error");
    		ret.put("msg", "填写用户信息");
    		return ret;
    	}
    	if(cpacha==null) {
    		ret.put("type", "error");
    		ret.put("msg", "填写验证码");
    		return ret;
    	}
    	if(user.getUsername()==null) {
    		ret.put("type", "error");
    		ret.put("msg", "填写用户名");
    		return ret;
    	}
    	if(user.getPassword()==null) {
    		ret.put("type", "error");
    		ret.put("msg", "填写密码");
    		return ret;
    	}
    	Object loginCpacha = request.getSession().getAttribute("loginCpacha");
    	if(loginCpacha == null) {
    		ret.put("type", "error");
    		ret.put("msg", "会话超时，请刷新页面");
    		return ret;
    	}
    	if(!cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())) {
    		ret.put("type", "error");
    		ret.put("msg", "验证码错误");
    		logService.add("用户名为"+user.getUsername()+"的用户登录时输入验证码错误!");
    		return ret;
    	}
    	User findByUsername = userService.findByUsername(user.getUsername());
    	//System.out.println(findByUsername.getPassword()+ "--"+user.getPassword());
    	if(findByUsername == null) {
    		ret.put("type", "error");
    		ret.put("msg", "该用户名不存在");
    		logService.add("登录时，用户名为"+user.getUsername()+"的用户不存在!");
    		return ret;
    	}
    	if(!findByUsername.getPassword().equals( user.getPassword())) {
    		ret.put("type", "error");
    		ret.put("msg", "当前用户密码错误");
    		logService.add("用户名为"+user.getUsername()+"的用户登录时输入密码错误!");
    		return ret;
    	}
    	//用户名密码都正确
    	//此时需要查询用户的角色权限
    	Role role = roleService.find(findByUsername.getRoleId());
    	List<Authority> authorityList = authorityService.findListByRoleId(role.getId());//根据角色获取权限列表
    	String menuIds = "";
    	for(Authority authority:authorityList){
			menuIds += authority.getMenuId() + ",";
		}
    	if(menuIds != null){
			menuIds = menuIds.substring(0,menuIds.length()-1);
		}
    	List<Menu> userMenus = menuService.findListByIds(menuIds);
    	//把角色信息、菜单信息放到session中
    	request.getSession().setAttribute("admin", findByUsername);
    	request.getSession().setAttribute("role", role);
		request.getSession().setAttribute("userMenus", userMenus);
    	ret.put("type", "success");
    	ret.put("msg", "登陆成功 !");
    	logService.add("用户名为{"+user.getUsername()+"}，角色为{"+role.getName()+"}的用户登录成功!");
    	return ret;
    }
    
    /**
	 * 后台退出注销功能
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("admin", null);
		session.setAttribute("role", null);
		request.getSession().setAttribute("userMenus", null);
		return "redirect:login";
	}
	
	/**
	 * 修改密码页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit_password",method=RequestMethod.GET)
	public ModelAndView editPassword(ModelAndView model){
		model.setViewName("system/edit_password");
		return model;
	}
	
	@RequestMapping(value="/edit_password",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> editPasswordAct(String newpassword,String oldpassword,HttpServletRequest request){
		Map<String, String> ret = new HashMap<String, String>();
		if(newpassword == null){
			ret.put("type", "error");
			ret.put("msg", "请填写新密码！");
			return ret;
		}
		User user = (User)request.getSession().getAttribute("admin");
		if(!user.getPassword().equals(oldpassword)){
			ret.put("type", "error");
			ret.put("msg", "原密码错误！");
			return ret;
		}
		user.setPassword(newpassword);
		if(userService.editPassword(user) <= 0){
			ret.put("type", "error");
			ret.put("msg", "密码修改失败，请联系管理员！");
			return ret;
		}
		ret.put("type", "success");
		ret.put("msg", "密码修改成功！");
		logService.add("用户名为{"+user.getUsername()+"}，的用户成功修改密码!");
		return ret;
	} 
    
    /*验证码配置
     * cpachaType:验证码类型
     * */
    @RequestMapping(value = "/get_cpacha",method = RequestMethod.GET)
    public void generateCpacha(
    		@RequestParam(name= "vl",required = false,defaultValue = "4") Integer vcodeLen,
    		@RequestParam(name= "w",required = false,defaultValue = "100") Integer width,
    		@RequestParam(name= "h",required = false,defaultValue = "30") Integer height,
    		@RequestParam(name= "type",required = true,defaultValue = "loginCpacha") String cpachaType,
    		HttpServletRequest request,HttpServletResponse response) {
    	
    	CpachaUtil cpachaUtil = new CpachaUtil(vcodeLen, width, height);
    	String generatorVCode = cpachaUtil.generatorVCode();
    	request.getSession().setAttribute(cpachaType, generatorVCode);
    	BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
    	try {
			ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
