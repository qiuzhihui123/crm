package com.qiuhui.web.staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.exception.ServiceException;
import com.qiuhui.service.StaffService;
import com.qiuhui.util.AjaxResult;
import com.qiuhui.web.BasicServlet;

@WebServlet("/satff/add")
public class StaffAddServlet extends BasicServlet{

	StaffService staffService = new StaffService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String staffName = req.getParameter("staffName");
		String mobile = req.getParameter("mobile");
		String password = req.getParameter("password");
		String[] deptId = req.getParameterValues("deptId");
	
		
		try{
			staffService.saveStaff(staffName,mobile,password,deptId);
			
			sendJson(AjaxResult.success(deptId),resp);
		} catch(ServiceException e){
			sendJson(new AjaxResult().error(e.getMessage()),resp);
		}
		
	
	}
}
