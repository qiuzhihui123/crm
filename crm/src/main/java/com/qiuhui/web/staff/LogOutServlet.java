package com.qiuhui.web.staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiuhui.entity.Staff;
import com.qiuhui.web.BasicServlet;

@WebServlet("/logout")
public class LogOutServlet extends BasicServlet{

	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(LogOutServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Staff staff = getCurrStaff(req);
		req.getSession().invalidate();
		logger.info("{}退出登录",staff.getStaffname());
		
		
		Cookie[] cookies = req.getCookies();
		for(Cookie cookie : cookies ){
			if("staffName".equals(cookie.getName())){
				req.setAttribute("staffName", cookie.getValue());
			}
		}
		forward("staff/login",req,resp);
	}
}
