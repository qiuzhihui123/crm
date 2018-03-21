package com.qiuhui.web.staff;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiuhui.entity.Staff;
import com.qiuhui.exception.ServiceException;
import com.qiuhui.service.StaffService;
import com.qiuhui.util.Config;
import com.qiuhui.web.BasicServlet;


@WebServlet("/login")
public class LoginServlet extends BasicServlet{

	
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(LoginServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		for(Cookie cookie : cookies){
			if("staffName".equals(cookie.getName())){
				req.setAttribute("staffName", cookie.getValue());
			}
		}
		
		forward("staff/login",req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String staffName = req.getParameter("username");
		String password = req.getParameter("password");
		String remember = req.getParameter("remember");
		System.out.println("登录帐号为" +staffName);
		
		StaffService staffService = new StaffService();
		Map<String,Object> res = new HashMap<>();
		try{
		Staff staff = staffService.login(staffName,password);
		
		
		
		HttpSession session = req.getSession();
		session.setAttribute("staff", staff);
		/*staffName = new String(staffName.getBytes("UTF-8"),"ISO8859-1");*/
		logger.info("{}登录了",staff.getStaffname());
		String ip = Config.getConfig("cookie.ip");
		
		if(StringUtils.isNotEmpty(remember)){
			logger.info("{}选择记住密码",staff.getStaffname());
			Cookie cookie = new Cookie("staffName",staffName);
			cookie.setDomain(ip);
			cookie.setPath("/");
			cookie.setMaxAge(60*60*24*30);
			cookie.setHttpOnly(true);
			
			resp.addCookie(cookie);
			
		} else {
			
			Cookie[] cookies = req.getCookies();
			for(Cookie cookie : cookies){
				if("staffName".equals(cookie.getName())){
					cookie.setDomain(ip);
					cookie.setPath("/");
					cookie.setMaxAge(0);
					
					resp.addCookie(cookie);
				}
			}
		}
		
			res.put("state", 0);
			res.put("data", staff);
			sendJson(res,resp);
		
		} catch(ServiceException e) {
			logger.error("帐号或密码错误");
			res.put("state", 40);
			res.put("message", e.getMessage());
			sendJson(res,resp);
		}
	}

}
