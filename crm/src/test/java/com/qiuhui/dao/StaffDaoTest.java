package com.qiuhui.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qiuhui.entity.Staff;

public class StaffDaoTest {

	
	private StaffDao staffDao = null;
	
	@Before
	public void before(){
		staffDao = new StaffDao();
	}
	
	@Test
	public void findByName(){
		String name = "小西几";
		
		Staff staff = staffDao.findByName(name);
		assertNotNull(staff);
	}
	
	@Test
	public void findAll(){
		List<Staff> staffList = staffDao.findAllStaffs();
		assertEquals(3,staffList.size());
	}
	
	
	
}
