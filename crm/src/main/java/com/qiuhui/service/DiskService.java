package com.qiuhui.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.qiuhui.dao.DiskDao;
import com.qiuhui.entity.Disk;
import com.qiuhui.exception.ServiceException;
import com.qiuhui.util.Config;
import com.qiuhui.util.DbHelp;

public class DiskService {

	DiskDao diskDao = new DiskDao();
	String filePath = Config.getConfig("file.upload.path");
	
	public void saveFolder(String folderName, int pId, int staffId) {
		Disk disk = new Disk();
		disk.setName(folderName);
		disk.setPid(pId);
		disk.setStaffId(staffId);
		disk.setType(Disk.DISK_TYPE_FOLDER);
		
		diskDao.saveFolder(disk);
	}

	public List<Disk> findDisksByPid(int pid) {
		return diskDao.findDisksByPid(pid);
	}

	public Disk findDiskById(int pid) {
		return diskDao.findDiskById(pid);
	}

	public void saveNewFile(InputStream input, String name, long fileSize, int pId, int staffId) {
		Disk disk = new Disk();
		disk.setName(name);
		disk.setPid(pId);
		disk.setStaffId(staffId);
		//用io包中的工具把大小变为人类可阅读的大小
		disk.setFileSize(FileUtils.byteCountToDisplaySize(fileSize));
		
		disk.setDownloadCount(Disk.INIT_DOWNLOAD_COUNT); 
		disk.setType(Disk.DISK_TYPE_FILE);

		
		String saveName = UUID.randomUUID() + name.substring(name.lastIndexOf("."));
		String filePath = Config.getConfig("file.upload.path");
		try {
			File file = new File(filePath);
			
			if(!file.exists()){
				file.mkdirs();
			}
			
			OutputStream out = new FileOutputStream(new File(filePath,saveName));
			IOUtils.copy(input, out);
			out.flush();
			out.close();
			input.close();
		} catch (IOException e) {
			throw new ServiceException("文件上传失败");
		}
		
		
		disk.setSaveName(saveName);
		diskDao.save(disk);
	}

	public Disk findDiskByDiskId(int diskId) {
		String sql = "select * from t_disk where id = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Disk.class, new BasicRowProcessor(new GenerousBeanProcessor())), diskId);
	}

	public void diskRename(String diskId, String diskName) {
		diskDao.diskRename(diskId,diskName);
	}

	public int deleteDiskById(String id) {
		if(!StringUtils.isNumeric(id)) {
			throw new ServiceException("参数异常");
		}
		
		Disk disk = diskDao.findDiskById(Integer.parseInt(id));
		if(disk == null) {
			throw new ServiceException("参数异常");
		}
		
		deleteDisk(disk);
		return disk.getPid();
	}

	private void deleteDisk(Disk disk) {
		List<Disk> diskList = new ArrayList<>();

		diskList = diskDao.findDisksByPid(disk.getId());
		
		for(Disk d : diskList) {
			deleteDisk(d);
		}
		
		diskDao.deleteById(disk.getId());
		if("file".equals(disk.getType())) {
			File file = new File(filePath,disk.getSaveName());
			if(file.exists()) {
				file.delete();
			}
		}
	}

}
