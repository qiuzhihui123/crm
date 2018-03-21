package com.qiuhui.web.staff;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.qiuhui.entity.Staff;
import com.qiuhui.service.StaffService;
import com.qiuhui.util.AjaxResult;
import com.qiuhui.util.Page;
import com.qiuhui.web.BasicServlet;

@WebServlet("/staff/list.json")
public class StaffListJsonServlet extends BasicServlet{

	StaffService staffService = new StaffService();
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deptId = req.getParameter("deptId");
		String p = req.getParameter("p");

		int pageNo = 1;
		
		if(StringUtils.isNumeric(p)) {
			pageNo = Integer.parseInt(p);
		}
		
		Page<Staff> pages = staffService.findStaffByPage(deptId, pageNo);
		AjaxResult result = AjaxResult.success(pages);
		sendJson(result,resp);
		
	}

}
