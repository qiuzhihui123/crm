package com.qiuhui.web.task;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.service.TaskService;
import com.qiuhui.web.BasicServlet;

@WebServlet("/task/add")
public class TaskAddServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TaskService taskService = new TaskService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		forward("task/add",req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String taskName = req.getParameter("taskName");
		String finishTime = req.getParameter("finishTime");
		/*System.out.println(taskName);
		System.out.println(finishTime);*/
		int staffId = getCurrStaff(req).getId();
		taskService.saveTask(taskName,finishTime,staffId);
		
	}

}
