package com.qiuhui.web.dept;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.entity.Dept;
import com.qiuhui.service.DeptService;
import com.qiuhui.web.BasicServlet;

@WebServlet("/dept/list")
public class DeptListServlet extends BasicServlet{

	DeptService deptService = new DeptService();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Dept> deptList = deptService.findAllDepts();
		
		sendJson(deptList,resp);
	}
}
