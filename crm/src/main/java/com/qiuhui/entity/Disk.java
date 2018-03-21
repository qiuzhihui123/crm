package com.qiuhui.entity;

import java.sql.Timestamp;

public class Disk {

	public static final String DISK_TYPE_FOLDER = "dir";
	public static final String DISK_TYPE_FILE = "file";
	public static final  int INIT_DOWNLOAD_COUNT = 0;
	
	private int id;
	private String name;
	private String type;
	private int pid;
	private String fileSize;
	private Timestamp updateTime;
	private int staffId;
	private int downloadCount;
	private String password;
	private String saveName;
	private String md5;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public static int getInitDownloadCount() {
		return INIT_DOWNLOAD_COUNT;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public static String getDiskTypeFolder() {
		return DISK_TYPE_FOLDER;
	}
	public static String getDiskTypeFile() {
		return DISK_TYPE_FILE;
	}
	
	
	
	
	
	
	
	
	
}
