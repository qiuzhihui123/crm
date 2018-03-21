package com.qiuhui.dao;

import com.qiuhui.entity.StaffDept;
import com.qiuhui.util.DbHelp;

public class StaffDeptDao {



	public void saveStaffDept(StaffDept staffDept) {
		String sql = "insert into t_staff_dept (staff_id,dept_id) values (?,?)";
		DbHelp.executeUpdate(sql, staffDept.getStaffId(),staffDept.getDeptId());
	}

	public void delStaffDeptByStaffId(int staffId) {
		String sql = "delete from t_staff_dept where staff_id = ?";
		DbHelp.executeUpdate(sql, staffId);
		
	}

	
	
}
