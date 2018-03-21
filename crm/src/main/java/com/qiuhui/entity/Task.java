package com.qiuhui.entity;

import java.sql.Timestamp;
import java.util.Date;

import org.joda.time.DateTime;

public class Task {

	private int id;
	private String title;
	private Date finishTime;
	private int status;
	private int staffId;
	private Timestamp createTime;
	
	public boolean isOverTime(){
		DateTime dateTime = new DateTime(getFinishTime());
		return dateTime.isBeforeNow();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	
	
}
