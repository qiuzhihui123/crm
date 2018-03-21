package com.qiuhui.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CustomerDaoTestCase {

	CustomerDao customerDao = new CustomerDao();
	
	@Before
	public void getCustomerDao(){
		customerDao = new CustomerDao();
		
	}
	
	@Test
	public void countCustomersByStaffId(){
		int staffId = 1;
		int count = customerDao.count(staffId);
		Assert.assertEquals(4, count);
	}
	
	@After
	public void after(){
		System.out.println("单元测试结束");
	}
}
