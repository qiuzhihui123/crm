package com.qiuhui.entity;

import java.sql.Timestamp;

public class Customer {

	private int id;
	private String custName;
	private String sex;
	private String jobTitle;
	private String address;
	private String mobile;
	private String trade;
	private String source;
	private String level;
	private String mark;
	private int staffId;
	private Timestamp createTime;
	private Timestamp lastContactTime;
	private Timestamp updateTime;
	private String reminder;

	
	
	public Customer() {
	}

	public Customer(String custName, String sex, String jobTitle, String address, String mobile, String trade,
			String source, String level, String mark, int staffId) {
		this.custName = custName;
		this.sex = sex;
		this.jobTitle = jobTitle;
		this.address = address;
		this.mobile = mobile;
		this.trade = trade;
		this.source = source;
		this.level = level;
		this.mark = mark;
		this.staffId = staffId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int accountId) {
		this.staffId = accountId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	

	public Timestamp getLastContactTime() {
		return lastContactTime;
	}

	public void setLastContactTime(Timestamp lastContactTime) {
		this.lastContactTime = lastContactTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

}
