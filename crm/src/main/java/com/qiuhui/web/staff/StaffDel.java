package com.qiuhui.web.staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.service.StaffService;
import com.qiuhui.util.AjaxResult;
import com.qiuhui.web.BasicServlet;
@WebServlet("/staff/del")
public class StaffDel extends BasicServlet{

	StaffService staffService = new StaffService();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		int staffId = Integer.parseInt(id);
		
		staffService.delStaffById(staffId);
		AjaxResult res = AjaxResult.success();
		sendJson(res, resp);
	}
}
