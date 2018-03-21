package com.qiuhui.web.staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.exception.ServiceException;
import com.qiuhui.service.StaffService;
import com.qiuhui.web.BasicServlet;


@WebServlet("/staff/edit/save")
public class StaffSaveEditServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	StaffService staffService = new StaffService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String staffId = req.getParameter("staffId");
		String staffName = req.getParameter("staffName");
		String mobile = req.getParameter("mobile");
		String password = req.getParameter("password");
		String[] deptIds = req.getParameterValues("deptId");
		int staId = Integer.parseInt(staffId);
		try{
			staffService.updateStaff(staId,staffName,mobile,password,deptIds);
			resp.sendRedirect("/staff/list");
		} catch(ServiceException e){
			resp.sendError(403,e.getMessage());
		}
		
		
	}
}
