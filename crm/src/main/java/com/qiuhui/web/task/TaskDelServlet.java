package com.qiuhui.web.task;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.exception.ForbiddenException;
import com.qiuhui.exception.ServiceException;
import com.qiuhui.service.TaskService;
import com.qiuhui.web.BasicServlet;

@WebServlet("/task/del")
public class TaskDelServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	TaskService taskService = new TaskService();
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String taskId = req.getParameter("taskId");
		int staffId = getCurrStaff(req).getId();
		try{
			taskService.delById(taskId,staffId);
			resp.sendRedirect("/task/list");
		} catch(ServiceException e){
			resp.sendError(404,e.getMessage());
		} catch(ForbiddenException e){
			resp.sendError(403,e.getMessage());
		}
	}

}
