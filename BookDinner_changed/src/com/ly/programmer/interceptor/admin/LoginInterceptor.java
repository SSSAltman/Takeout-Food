package com.ly.programmer.interceptor.admin;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ly.programmer.entity.admin.Menu;
import com.ly.programmer.util.MenuUtil;

import net.sf.json.JSONObject;
//后台登陆拦截器 
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String requestURI = request.getRequestURI();
		Object admin = request.getSession().getAttribute("admin");
		if(admin == null) {
			//登陆失效，需要重定向
			System.out.println(requestURI+"  好小子，被我逮着了吧");
			String header = request.getHeader("X-Requested-With");
			//判断是否是ajax请求
			if("XMLHttpRequest".equals(header)) {
				//表示是ajax请求
				Map<String,String> ret = new HashMap<String,String>();
				ret.put("type", "error");
				ret.put("msg", "登录会话超时或未登录，你自己看着办");
				response.getWriter().write(JSONObject.fromObject(ret).toString());
				return false;
			}
			//普通链接跳转，重定向到登录页面
			response.sendRedirect(request.getServletContext().getContextPath()+"/system/login");
			return false;
		}
		//获取菜单id
				String mid = request.getParameter("_mid");
				if(mid != null){
					List<Menu> allThirdMenu = MenuUtil.getAllThirdMenu((List<Menu>)request.getSession().getAttribute("userMenus"), Long.valueOf(mid));
					request.setAttribute("thirdMenuList", allThirdMenu);
				}
		return true;
	}

}
