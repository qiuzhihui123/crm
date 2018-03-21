package com.qiuhui.web.staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.entity.Staff;
import com.qiuhui.service.StaffService;
import com.qiuhui.web.BasicServlet;

@WebServlet("/staff/edit")
public class StaffEdit extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	StaffService staffService = new StaffService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		int staffId = Integer.parseInt(id);
		Staff staff = staffService.findStaffById(staffId);
		
		req.setAttribute("staff", staff);
		
		forward("staff/edit",req,resp);
	}
}
