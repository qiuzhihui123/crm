package com.qiuhui.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.qiuhui.dao.DeptDao;
import com.qiuhui.entity.Dept;
import com.qiuhui.exception.ServiceException;
import com.qiuhui.util.Config;

public class DeptService {

	DeptDao deptDao = new DeptDao();
	public void addDept(String deptName) {
		if(StringUtils.isEmpty(deptName)){
			throw new ServiceException("名称不能为空");
		}
		Dept dept = deptDao.findByDeptName(deptName);
		if(dept!= null){
			throw new ServiceException("该部门已存在");
		}
		
		dept = new Dept();
		dept.setDeptName(deptName);
		dept.setPId(Config.COMPANTY_ID);
		deptDao.save(dept);
		
		
	}
	public List<Dept> findAllDepts() {
		return deptDao.findAllDepts();
	}

	
	
}
