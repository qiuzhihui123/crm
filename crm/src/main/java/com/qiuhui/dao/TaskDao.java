package com.qiuhui.dao;

import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qiuhui.entity.Task;
import com.qiuhui.util.DbHelp;

public class TaskDao {

	public void saveTask(Task task) {
		String sql = "insert into t_task (title,finish_time,status,staff_id) values (?,?,?,?)";
		DbHelp.executeUpdate(sql, task.getTitle(),task.getFinishTime(),task.getStatus(),task.getStaffId());
	}

	public List<Task> findAllTasksByStaffId(int staffId) {
		String sql = "select * from t_task where staff_id = ?";
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Task.class,new BasicRowProcessor(new GenerousBeanProcessor())), staffId);
	}

	public Task findTaskById(String taskId) {
		System.out.println("haha"+ taskId);
		String sql = "select * from t_task where id =?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Task.class,new BasicRowProcessor(new GenerousBeanProcessor())), taskId);
	}

	public void updateTask(Task task) {
		String sql = "update t_task set title = ?,finish_time=?,status=? where id =?";
		DbHelp.executeUpdate(sql, task.getTitle(),task.getFinishTime(),task.getStatus(),task.getId());
	}

	public void DelById(int taskId) {
		
		String sql = "delete from t_task where id = ?";
		DbHelp.executeUpdate(sql, taskId);
	}

	public List<Task> findTasksByStaffIdAndStatus(int staffId, int status) {
		String sql = "select * from t_task where staff_id = ? and status = ?";
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Task.class,new BasicRowProcessor(new GenerousBeanProcessor())), staffId,status);
	}
}
