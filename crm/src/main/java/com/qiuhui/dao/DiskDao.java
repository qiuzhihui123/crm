package com.qiuhui.dao;

import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qiuhui.entity.Disk;
import com.qiuhui.util.DbHelp;

public class DiskDao {

	public void saveFolder(Disk disk) {
		String sql = "insert into t_disk (name,pid,staff_id,type) values (?,?,?,?)";
		DbHelp.executeUpdate(sql, disk.getName(),disk.getPid(),disk.getStaffId(),disk.getType());
		
	}

	public List<Disk> findDisksByPid(int pid) {
		String sql = "select * from t_disk where pid = ?";
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Disk.class,new BasicRowProcessor(new GenerousBeanProcessor())) , pid);
	}

	public Disk findDiskById(int pid) {
		String sql = "select * from t_disk where id = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Disk.class,new BasicRowProcessor(new GenerousBeanProcessor())), pid);
	}

	public void save(Disk disk) {
		String sql = "insert into t_disk (name, pid, type, staff_id, file_size, download_count, save_name) values (?,?,?,?,?,?,?)";
		DbHelp.executeUpdate(sql, disk.getName(), disk.getPid(), disk.getType(), disk.getStaffId(), disk.getFileSize(), disk.getDownloadCount(), disk.getSaveName());
	}

	public void diskRename(String diskId, String diskName) {
		String sql = "update t_disk set name = ? where id = ?";
		DbHelp.executeUpdate(sql, diskName,diskId);
	}

	public void deleteById(int id) {
		String sql = "delete from t_disk where id = ?";
		DbHelp.executeUpdate(sql, id);
	}

}
