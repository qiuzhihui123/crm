package com.qiuhui.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiuhui.dao.StaffDao;
import com.qiuhui.dao.StaffDeptDao;
import com.qiuhui.entity.Staff;
import com.qiuhui.entity.StaffDept;
import com.qiuhui.exception.ForbiddenException;
import com.qiuhui.exception.ServiceException;
import com.qiuhui.util.Config;
import com.qiuhui.util.Page;


public class StaffService {
	
	Logger logger = LoggerFactory.getLogger(StaffService.class);
	
	StaffDeptDao staffDeptDao = new StaffDeptDao();
	StaffDao staffDao = new StaffDao();
	
	public Staff login(String staffName, String password) {
		logger.info("我要看staffName的值是{}",staffName);
		
		Staff staff = staffDao.findByMobile(staffName);
			
		 password = DigestUtils.md5Hex(password + Config.getConfig("staff.password.salt"));
		
		if(staff != null && password.equals(staff.getPassword())){
			return staff;
			
		} else {
			throw  new ServiceException("帐号或密码错误");
		}
	}

	/**保存员工信息以及所属部门
	 * @param staffName
	 * @param mobile
	 * @param password
	 * @param deptId
	 * @throws ServiceException 手机号码存在时抛出
	 */
	public void saveStaff(String staffName, String mobile, String password, String[] deptIds) throws ServiceException{
			
		
		Staff staff = staffDao.findByMobile(mobile);
		if(staff != null){
			throw new ServiceException("该电话号码已存在");
		}
		//保存员工
		staff =  new Staff();
		staff.setStaffname(staffName);
		password = DigestUtils.md5Hex(password + Config.getConfig("staff.password.salt"));
		staff.setPassword(password);
		staff.setMobile(mobile);
		staff.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		//存入数据库，并返回id
		int staffId = staffDao.saveStaff(staff);
		
		//新增员工和部门
		for(String deptId : deptIds){
			StaffDept staffDept = new StaffDept();
			staffDept.setStaffId(staffId);
			staffDept.setDeptId(Integer.parseInt(deptId));
			
			
			staffDeptDao.saveStaffDept(staffDept);
		}
		
	}

	public Page<Staff> findStaffByPage(String deptId, int pageNo) {
		
		int count = staffDao.count(deptId);	
		Page<Staff> page = new Page<>(pageNo,count);
		List<Staff> staffList = staffDao.findByPage(deptId,page.getStart(),page.getPageSize());
		page.setItems(staffList);
		return page;
	}

	public List<Staff> findAllStaffs() {
		return staffDao.findAllStaffs();
	}


	public void changePassword(Staff staff, String newPassword) {
		newPassword = DigestUtils.md5Hex(newPassword + Config.getConfig("staff.password.salt"));
		staff.setPassword(newPassword);
		staffDao.changePassword(staff);
	}

	/**
	 * 根据id删除staff
	 * @param staffId
	 */
	public void delStaffById(int staffId) {
		
		staffDao.delStaffById(staffId);
		staffDeptDao.delStaffDeptByStaffId(staffId);
	}


	/**
	 * 核对原始密码，正确后执行修改密码
	 * @param staff
	 * @param oldPass
	 * @param newPass
	 */
	public void checkOutOldPassSetNewPass(Staff staff, String oldPass, String newPass) {
		Staff sta = staffDao.findByStaffId(staff.getId());
		oldPass = DigestUtils.md5Hex(oldPass + Config.getConfig("staff.password.salt"));
		if(sta.getPassword().equals(oldPass)){
			newPass = DigestUtils.md5Hex(newPass + Config.getConfig("staff.password.salt"));
			staff.setPassword(newPass);
			staffDao.changePassword(staff);
		} else{
			throw new ForbiddenException("原始密码错误");
		}
	}

	public Staff findStaffById(int staffId) {
		
		Staff staff =  staffDao.findStaffDeptById(staffId);
		
		return staff;
	}

	/**
	 * 用表单提交的值，保存编辑后的员工
	 * @param staffId
	 * @param staffName
	 * @param mobile
	 * @param password
	 * @param deptId
	 */
	public void updateStaff(int staffId,String staffName, String mobile, String password, String[] deptIds) {
		Staff staff = staffDao.findByMobile(mobile);
		if(staff != null && staff.getId() != staffId){
			throw new ServiceException("该电话号码已存在");
		}
		staff = staffDao.findByStaffId(staffId);
		
		staff.setStaffname(staffName);
		password = DigestUtils.md5Hex(password + Config.getConfig("staff.password.salt"));
		staff.setPassword(password);
		staff.setMobile(mobile);
		staff.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		staffDao.updateStaff(staff);
		
		//删除staff和dept关联关系
		staffDeptDao.delStaffDeptByStaffId(staffId);
		
		//增加新的staff和dept关联关系
		for(String deptId : deptIds){
			StaffDept staffDept = new StaffDept();
			staffDept.setStaffId(staffId);
			staffDept.setDeptId(Integer.parseInt(deptId));
			
			staffDeptDao.saveStaffDept(staffDept);
		}
	}
}
