package com.qiuhui.web.dept;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.exception.ServiceException;
import com.qiuhui.service.DeptService;
import com.qiuhui.util.AjaxResult;
import com.qiuhui.web.BasicServlet;

@WebServlet("/dept/add")
public class DeptAddServlet extends BasicServlet{

	
	private static final long serialVersionUID = 1L;
	DeptService deptService = new DeptService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String deptName = req.getParameter("deptName");
		
		try{
		deptService.addDept(deptName);
		sendJson(AjaxResult.success(),resp);
		
		} catch(ServiceException e){
			sendJson(AjaxResult.error(e.getMessage()),resp);
		}
		
	}
}
