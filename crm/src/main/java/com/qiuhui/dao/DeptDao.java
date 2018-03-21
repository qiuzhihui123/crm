package com.qiuhui.dao;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qiuhui.entity.Dept;
import com.qiuhui.util.DbHelp;

public class DeptDao {

	public Dept findByDeptName(String deptName) {
	
		String sql = "select * from t_dept where deptname = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Dept.class), deptName);
	}

	public void save(Dept dept) {
		String sql = "insert into t_dept (deptname,pid) values (?,?)";
		DbHelp.executeUpdate(sql, dept.getDeptName(),dept.getPId());
	}

	public List<Dept> findAllDepts() {
		String sql = "select * from t_dept ";
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Dept.class));
	}

}
