package com.qiuhui.web.staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.entity.Staff;
import com.qiuhui.exception.ForbiddenException;
import com.qiuhui.service.StaffService;
import com.qiuhui.util.AjaxResult;
import com.qiuhui.web.BasicServlet;
@WebServlet("/staff/changePassword")
public class StaffChangePassword extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StaffService staffService = new StaffService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		forward("staff/changePassword", req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String oldPass = req.getParameter("oldPass");
		String newPass = req.getParameter("newPass");
		
		/*Map<String,String> maps = new HashMap<>();
		maps.put("oldPass", oldPass);
		maps.put("newPass", newPass);*/
		
		Staff staff = getCurrStaff(req);
		
		try{
			staffService.checkOutOldPassSetNewPass(staff,oldPass,newPass);
			sendJson(AjaxResult.success(),resp);
			
		} catch(ForbiddenException e){
			/*maps.put("message", e.getMessage());*/
			sendJson(AjaxResult.error(e.getMessage()),resp);
		}
		
	}
}
