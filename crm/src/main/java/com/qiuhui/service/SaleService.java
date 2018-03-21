package com.qiuhui.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.qiuhui.dao.CustomerDao;
import com.qiuhui.dao.SaleDao;
import com.qiuhui.dao.SaleRecordDao;
import com.qiuhui.entity.Customer;
import com.qiuhui.entity.SaleChance;
import com.qiuhui.entity.SaleChanceRecord;
import com.qiuhui.exception.ForbiddenException;
import com.qiuhui.exception.ServiceException;
import com.qiuhui.util.Config;
import com.qiuhui.util.Page;

public class SaleService {
	
	CustomerDao custDao = new CustomerDao();
	SaleDao saleDao = new SaleDao();
	SaleRecordDao saleRecordDao = new SaleRecordDao();

	public Page<SaleChance> findSaleListByPage(int pageNo, int staffId) {
		int count = saleDao.countByStaffId(staffId);
		Page<SaleChance> page = new Page<>( pageNo,count);
		List<SaleChance> saleChanceList = saleDao.findListByStaffAndPage(staffId,page.getStart(), page.getPageSize());
		page.setItems(saleChanceList);
		return page;
	}

	public List<String> findAllProcess() {
		String processString = Config.getConfig("sales.progress");
		String[] processArray = processString.split(",");
		return Arrays.asList(processArray);
	}

	public List<Customer> findAllCustomersByStaffId(int staffId) {
		return custDao.findByStaffId(staffId);
	}

	public void saveSaleChane(SaleChance saleChance) {
		Customer cust = custDao.findCustomerById(Integer.toString(saleChance.getCustId()));
		cust.setLastContactTime(new Timestamp(System.currentTimeMillis()));
		custDao.update(cust);
		
		saleChance.setLastTime(new Timestamp(System.currentTimeMillis()));
		int saveId = saleDao.save(saleChance);
		
		if(StringUtils.isNotEmpty(saleChance.getContent())) {
			SaleChanceRecord record = new SaleChanceRecord();
			record.setContent(saleChance.getContent());
			record.setSaleId(saveId);
			saleRecordDao.save(record);
		}
	}

	public SaleChance findSaleChanceById(String saleId, int staffId) {
		SaleChance saleChance = checkSaleChance(saleId,staffId);
		
		return saleChance;
	}

	private SaleChance checkSaleChance(String saleId, int staffId) {
		if(!StringUtils.isNumeric(saleId)) {
			throw new ServiceException("参数异常");
		}
		
		SaleChance saleChance = saleDao.findById(Integer.parseInt(saleId));
		if(saleChance == null) {
			throw new ServiceException("参数异常");
		}
		
		if(saleChance.getStaffId() == staffId) {
			return saleChance;
		} else {
			throw new ForbiddenException("拒绝访问");
		}
	}

	public List<SaleChanceRecord> findRecordListBySaleId(String saleId) {
		return saleRecordDao.findListBySaleId(saleId);
	}

	public void updateSaleChaneProcess(String saleId, String process, int staffId) {
				SaleChance chance = saleDao.findById(Integer.parseInt(saleId));
				chance.setProcess(process);
				Timestamp currTime = new Timestamp(System.currentTimeMillis());
				chance.setLastTime(currTime);
				
				saleDao.update(chance);
				SaleChanceRecord record = new SaleChanceRecord();
				record.setSaleId(Integer.parseInt(saleId));
				record.setContent("修改当前进度为[ " + process + " ]");
				saleRecordDao.save(record);
				
				Customer cust = custDao.findCustomerById(Integer.toString(chance.getCustId()));
				cust.setLastContactTime(currTime);
				custDao.update(cust);
		
	}

	public void addRecord(SaleChanceRecord record) {
		saleRecordDao.save(record);

		SaleChance chance = saleDao.findById(record.getSaleId());
		Timestamp currTime = new Timestamp(System.currentTimeMillis());
		chance.setLastTime(currTime);
		
		saleDao.update(chance);

		Customer cust = chance.getCustomer(); 
		cust.setLastContactTime(currTime);
		cust.setUpdateTime(currTime);
		custDao.update(cust);
	}

	public void delBySaleId(String saleId, int staffId) {
		SaleChance chance = checkSaleChance(saleId, staffId);

		saleRecordDao.delBySaleId(chance.getId());

		saleDao.delById(chance.getId());
	}

	public List<Map<String, Object>> findProcessCount() {
		int counts = saleDao.countAllProcess();
		List<Map<String, Object>> mapList = saleDao.findProcessCount();
		Map<String, Object> map = new HashMap<>();
		map.put("counts", counts);
		mapList.add(map);
		
		return mapList;
	}

	public List<SaleChance> findSaleChanceBYCustId(String custId) {
		return saleDao.findSaleChanceBYCustId(custId);
	}

	

	public Page<SaleChance> findCustomerRecordByPage(String custId, int pageNo) {
		int count = saleDao.count(custId);	
		Page<SaleChance> page = new Page<>(pageNo,count);
		List<SaleChance> saleChanceList = saleDao.findByPage(custId,page.getStart(),page.getPageSize());
		page.setItems(saleChanceList);
		return page;
	}

}
