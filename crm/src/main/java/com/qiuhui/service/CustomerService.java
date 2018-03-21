package com.qiuhui.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.qiuhui.dao.CustomerDao;
import com.qiuhui.dao.StaffDao;
import com.qiuhui.entity.Customer;
import com.qiuhui.entity.Staff;
import com.qiuhui.exception.ForbiddenException;
import com.qiuhui.exception.ServiceException;
import com.qiuhui.util.Config;
import com.qiuhui.util.Page;

public class CustomerService {

	CustomerDao customerDao = new CustomerDao();
	StaffDao staffDao = new StaffDao();
	public List<String> findAllSources() {
		String sourceStr = Config.getConfig("customer.source"); 
		String[] sourceArray = sourceStr.split(",");
		return Arrays.asList(sourceArray);
		
	}

	public List<String> findAllTrades() {
		String tradeStr = Config.getConfig("customer.trade"); 
		String[] tradeArray = tradeStr.split(",");
		return Arrays.asList(tradeArray);
	}

	public void addMyCustomer(String custname, String sex, String jobtitle, String mobile, String address,
			String source, String trade, String level, String mark, int staffId) {
		
		Customer customer = new Customer(custname, sex, jobtitle,address, mobile, trade, source, level, mark, staffId);
		customer.setReminder("员工添加");
		
		customer.setLastContactTime(new Timestamp(System.currentTimeMillis()));
		customer.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		customerDao.add(customer);
	}

	public Page<Customer> findCustomerByPage(int pageNo, int staffId) {
		int count = customerDao.count(staffId);
		
		Page<Customer> page = new Page<>(pageNo,count);
		List<Customer> customerList = customerDao.findCustomerListByPage(staffId,page.getStart(),page.getPageSize());
		
		page.setItems(customerList);
		return page;
		
	}

	public Customer findCustomerById(String custId, int staffId) throws ForbiddenException {
		Customer cust = checkCustomer(custId,staffId); 
		return cust;
	}

	private Customer checkCustomer(String custId, int staffId) throws ForbiddenException {
		if(!StringUtils.isNumeric(custId)){
			throw new ServiceException("参数异常");
		}
		
		Customer cust = customerDao.findCustomerById(custId);
		if(cust == null){
			throw new ServiceException("参数异常");
		}
		
		if(cust.getStaffId() == staffId ){
			return cust;
		} else {
			throw new ForbiddenException("拒绝访问");
		}
	}

	public void edit(String custId, String custname, String sex, String jobtitle, String address, String mobile,
			String source, String trade, String level, String mark) {
		Customer cust = customerDao.findCustomerById(custId);
		cust.setCustName(custname);
		cust.setSex(sex);
		cust.setJobTitle(jobtitle);
		cust.setAddress(address);
		cust.setMobile(mobile);
		cust.setSource(source);
		cust.setTrade(trade);
		cust.setLevel(level);
		cust.setMark(mark);
		
		cust.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		
		customerDao.update(cust);
	}

	public void delByCustId(String custId, int staffId) throws ForbiddenException {
		customerDao.del(custId);
	}

	public void transByCustId(String custId, int currStaffId, String toStaffId) throws ForbiddenException {
		Customer cust = checkCustomer(custId, currStaffId);
		Staff currStaff = staffDao.findByStaffId(currStaffId);
		
		Staff toStaff = checkStaff(toStaffId);
		
		cust.setStaffId(toStaff.getId());
		cust.setReminder(cust.getReminder() + "," + currStaff.getStaffname() +"转交给" + toStaff.getStaffname());
		customerDao.update(cust);
	}

	private Staff checkStaff(String toStaffId) {
			if(!StringUtils.isNumeric(toStaffId)) {
				throw new ServiceException("参数异常");
			}
			
			Staff staff = staffDao.findByStaffId(Integer.parseInt(toStaffId));
			if(staff == null) {
				throw new ServiceException("参数异常");
			}
			
		
		return staff;
	}

	public void toPublicByCustId(String custId, int staffId) throws ForbiddenException {
		Customer cust = checkCustomer(custId, staffId);
		Staff staff = staffDao.findByStaffId(staffId);
		cust.setStaffId(Config.PUBLIC_ID);
		cust.setReminder(cust.getReminder() + "--"+ staff.getStaffname() + "放入公海");
		
		customerDao.update(cust);
	}

	public void pubToMyCustomer(String custId, Staff staff) {
		Customer cust = customerDao.findCustomerById(custId);
		cust.setStaffId(staff.getId());
		cust.setReminder(cust.getReminder() + "--"+staff.getStaffname()+"认领");
		
		customerDao.update(cust);
	}

	/**
	 * 查找用户星级的统计关系
	 * @return
	 */
	public List<Map<String, Object>> findLevelCount() {
		return customerDao.findLevelCount();
	}

}
