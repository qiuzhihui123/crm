package com.qiuhui.web.task;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.entity.Task;
import com.qiuhui.service.TaskService;
import com.qiuhui.web.BasicServlet;


@WebServlet("/task/list")
public class TaskListServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TaskService taskService = new TaskService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String show = req.getParameter("show");
		
		int staffId = getCurrStaff(req).getId();
		List<Task> taskList = taskService.findAllTasksByStaffId(staffId,show);
		req.setAttribute("taskList", taskList);
		
		forward("task/list",req,resp);
	}
	
	
}
