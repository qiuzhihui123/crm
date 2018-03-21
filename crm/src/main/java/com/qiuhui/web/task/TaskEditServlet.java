package com.qiuhui.web.task;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qiuhui.entity.Task;
import com.qiuhui.service.TaskService;
import com.qiuhui.util.AjaxResult;
import com.qiuhui.web.BasicServlet;

@WebServlet("/task/edit")
public class TaskEditServlet extends BasicServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	TaskService taskService = new TaskService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String taskId = req.getParameter("taskId");
		Task task = taskService.findTaskById(taskId);
		req.setAttribute("task",task);
		forward("task/edit",req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String taskId = req.getParameter("taskId");
		String taskName = req.getParameter("taskName");
		String finishTime = req.getParameter("finishTime"); 
		
		taskService.updateTaskById(taskId, taskName,finishTime);
		sendJson(AjaxResult.success(),resp);
	}
}
