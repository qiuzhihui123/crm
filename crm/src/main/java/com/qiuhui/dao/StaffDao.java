package com.qiuhui.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.StringUtils;

import com.qiuhui.entity.Staff;
import com.qiuhui.util.DbHelp;

public class StaffDao {

	public Staff findByName(String staffName) {
			
		String sql = "select * from t_staff where staffname = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Staff.class), staffName);
	}

	public Staff findByMobile(String mobile) {
		String sql = "select * from t_staff where mobile = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Staff.class), mobile);
	}

	public int saveStaff(Staff staff) {
		String sql = "insert into t_staff (staffname,password,mobile,update_time) values (?,?,?,?)";
		return DbHelp.executeInsert(sql, staff.getStaffname(),staff.getPassword(),staff.getMobile(),staff.getUpdateTime());
	}

	public int count(String deptId) {
		if(StringUtils.isNotEmpty(deptId)) {
			String sql = "select count(*) from t_staff s inner join t_staff_dept sd on s.id = sd.staff_id ";
			sql += "where sd.dept_id = ? ";
			return DbHelp.executeQuery(sql, new ScalarHandler<Long>(),deptId).intValue();
		} else {
			String sql = "select count(*) from t_staff";
			return DbHelp.executeQuery(sql, new ScalarHandler<Long>()).intValue();
		}
	}

	public List<Staff> findByPage(String deptId, int start, int pageSize) {
		String sql = "select s.id,s.staffname,s.mobile,d.deptname from t_staff s inner join t_staff_dept sd on s.id = sd.staff_id inner join t_dept d on sd.dept_id = d.id ";
		List<Object> arrays = new ArrayList<>();
		
		if(StringUtils.isNotEmpty(deptId)) {
			sql += "inner join ("
					+ "select id from t_staff "
					+ "inner join t_staff_dept "
					+ "on t_staff.id = t_staff_dept.staff_id "
					+ "where dept_id = ? limit ?,?) "
					+ "as temp on s.id = temp.id  order by s.id";
			arrays.add(deptId);
		} else {
			sql += "inner join (select id from t_staff limit ?,? ) as temp on s.id = temp.id  order by s.id";
		}
		arrays.add(start);
		arrays.add(pageSize); 	
		
		return DbHelp.executeQuery(sql, new ResultSetHandler<List<Staff>>() {
			List<Staff> staffList = new ArrayList<Staff>();
			@Override
			public List<Staff> handle(ResultSet rs) throws SQLException { 
				while(rs.next()) {
					
					Staff staff = new Staff();
					staff.setId(rs.getInt("id"));
					staff.setStaffname(rs.getString("staffname"));
					staff.setMobile(rs.getString("mobile"));
					
					Staff sta = checkStaff(staff);
					
					if(sta != null) {
						
						sta.setDeptName(sta.getDeptName() + " " + rs.getString("deptname"));
					} else {
						
						staff.setDeptName(rs.getString("deptname"));
						staffList.add(staff);
					}
					
					
				}
				return staffList;
			}
			
			private Staff checkStaff(Staff staff){
				for(Staff sta : staffList) {
					if(sta.equals(staff)) {
						return sta;
					}
				}
				
				return null;
			}
			
		}, arrays.toArray());
	}

	public List<Staff> findAllStaffs() {
		String sql = "select * from t_staff";
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Staff.class));
	}

	public Staff findByStaffId(int currStaffId) {
		String sql = "select * from t_staff where id = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Staff.class), currStaffId);
	}

	public void changePassword(Staff staff) {
		String sql = "update t_staff set password = ? where mobile = ?";
		DbHelp.executeUpdate(sql, staff.getPassword(),staff.getMobile());
		
	}

	public void delStaffById(int staffId) {
		String sql = "delete  from t_staff where id = ?";
		DbHelp.executeUpdate(sql, staffId);
	}

	public Staff findStaffDeptById(int staffId) {
		String sql = "select * from t_staff s inner join t_staff_dept sd inner join t_dept d where s.id = sd.staff_id and d.id = sd.dept_id and s.id = ?";
		return DbHelp.executeQuery(sql, new ResultSetHandler<Staff>(){

			@Override
			public Staff handle(ResultSet rs) throws SQLException {
				Staff staff = null;
				
				List<String> deptNames = new ArrayList<>();
				while(rs.next()){
					 staff = new Staff();
					staff.setId(rs.getInt("id"));
					staff.setStaffname(rs.getString("staffname"));
					staff.setMobile(rs.getString("mobile"));
					deptNames.add(rs.getString("deptname"));
					
				}
				String[] deptList =  deptNames.toArray(new String[deptNames.size()]);
				staff.setDeptList(deptList);
				return staff;
			}},staffId); 
		
	}

	public void updateStaff(Staff staff) {
		String sql = "update t_staff set staffname = ?,password = ?,mobile = ?,update_time =? where id = ?";
		DbHelp.executeUpdate(sql, staff.getStaffname(),staff.getPassword(),staff.getMobile(),staff.getUpdateTime(),staff.getId());
		
	}

	

}
