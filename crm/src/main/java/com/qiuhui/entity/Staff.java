package com.qiuhui.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Administrator
 *
 */
public class Staff implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private int id;
	private String staffname;
	private String password;
	private String mobile;
	private Timestamp createTime;
	private Timestamp updateTime;
	private String deptName;
	
	private String[] deptList;
	
	public String[] getDeptList() {
		return deptList;
	}
	public void setDeptList(String[] deptList) {
		this.deptList = deptList;
	}
	
	
	
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
		result = prime * result + ((staffname == null) ? 0 : staffname.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Staff other = (Staff) obj;
		if (id != other.id)
			return false;
		if (mobile == null) {
			if (other.mobile != null)
				return false;
		} else if (!mobile.equals(other.mobile))
			return false;
		if (staffname == null) {
			if (other.staffname != null)
				return false;
		} else if (!staffname.equals(other.staffname))
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStaffname() {
		return staffname;
	}
	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
