package com.qiuhui.service;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.qiuhui.dao.TaskDao;
import com.qiuhui.entity.Task;
import com.qiuhui.exception.ForbiddenException;
import com.qiuhui.exception.ServiceException;
import com.qiuhui.util.Config;

public class TaskService {

	TaskDao taskDao = new TaskDao();
	
	public void saveTask(String taskName, String finishTime, int staffId) {
		Task task = new Task();
		try {
			task.setTitle(taskName);
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			
			Date finishDate = format.parse(finishTime);
			task.setFinishTime(finishDate);
			task.setStatus(Config.TASK_STATUS_UNDONE);
			task.setStaffId(staffId);
			taskDao.saveTask(task);
			
		} catch (ParseException e) {
			
		}
		
	}

	public List<Task> findAllTasksByStaffId(int staffId,String show) {
		if(StringUtils.isNotEmpty(show)){
				
			if(show.equals("undone")){
				int status = Config.TASK_STATUS_UNDONE;
				return taskDao.findTasksByStaffIdAndStatus(staffId,status);
			}
		}
		return taskDao.findAllTasksByStaffId(staffId);
		
	}

	/**
	 * 根据taskId和是否选中更新它的状态
	 * @param taskId
	 * @param status
	 */
	public void updateTaskStatusById(String taskId, String status) {
		Task task = taskDao.findTaskById(taskId);
		task.setStatus(Integer.parseInt(status));
		taskDao.updateTask(task);
	}

	/**
	 * 通过id删除task
	 * @param taskId
	 */
	public void delById(String taskId,int staffId) {
		if(!StringUtils.isNumeric(taskId)) {
			throw new ServiceException("参数异常");
		}
		
		Task task = taskDao.findTaskById(taskId);
		if(task == null){
			throw new ServiceException("参数异常");
		}
		
		if(task.getStaffId() == staffId){
			taskDao.DelById(Integer.parseInt(taskId));
		} else {
			throw new ForbiddenException("无访问权限");
		}
		
	}

	public Task findTaskById(String taskId) {
		return taskDao.findTaskById(taskId);
	}

	public void updateTaskById(String taskId, String taskName, String finishTime) {
		Task task = taskDao.findTaskById(taskId);
		task.setTitle(taskName);
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 
		try {
			Date finishDate = format.parse(finishTime);
			task.setFinishTime(finishDate);
			taskDao.updateTask(task);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
