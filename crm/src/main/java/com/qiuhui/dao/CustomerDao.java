package com.qiuhui.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.qiuhui.entity.Customer;
import com.qiuhui.util.DbHelp;

public class CustomerDao {

	

	public void add(Customer customer) {
		String sql = "insert into t_customer (cust_name,sex,job_title,mobile,address,trade,source,level,mark,staff_id,last_contact_time,update_time,reminder) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		DbHelp.executeUpdate(sql, customer.getCustName(),customer.getSex(),customer.getJobTitle(),customer.getMobile(),customer.getAddress(),customer.getTrade(),customer.getSource(),customer.getLevel(),customer.getMark(),customer.getStaffId(),customer.getLastContactTime(),customer.getUpdateTime(),customer.getReminder());
	}

	public int count(int staffId) {
			String sql = "select count(*) from t_customer where staff_id = ?";
			return DbHelp.executeQuery(sql, new ScalarHandler<Long>(), staffId).intValue();
	}

	public List<Customer> findCustomerListByPage(int staffId, int start, int pageSize) {
		String sql = "select * from t_customer where staff_id = ? limit ?,?";
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Customer.class, new BasicRowProcessor(new GenerousBeanProcessor())), staffId, start, pageSize);
	}

	public Customer findCustomerById(String custId) {
		String sql = "select * from t_customer where id = ?";
		return DbHelp.executeQuery(sql, new BeanHandler<>(Customer.class,new BasicRowProcessor(new GenerousBeanProcessor()) ), custId);
	}

	public void update(Customer cust) {
		String sql = "update t_customer set cust_name = ?, sex = ?, job_title=?, mobile = ?, address=?, trade=?, source=?,level=?,mark=?, staff_id=?, last_contact_time =?, update_time=?, reminder=? where id=?";
		DbHelp.executeUpdate(sql, cust.getCustName(),cust.getSex(),cust.getJobTitle(),cust.getMobile(),cust.getAddress(),cust.getTrade(),cust.getSource(),cust.getLevel(),cust.getMark(),cust.getStaffId(),cust.getLastContactTime(),cust.getUpdateTime(),cust.getReminder(),cust.getId());
	}

	public void del(String custId) {
		String sql = "delete from t_customer where id = ?";
		DbHelp.executeUpdate(sql, custId);
	}

	public void pubToMyCustomer(String custId, int id) {
		String sql = "update t_customer set staff_id = ? where id = ?";
		DbHelp.executeUpdate(sql, id,custId);
		
	}

	public List<Map<String, Object>> findLevelCount() {
		String sql = "select count(*) as count,level from t_customer group by level";
		return DbHelp.executeQuery(sql, new ResultSetHandler<List<Map<String, Object>>>(){

			@Override
			public List<Map<String, Object>> handle(ResultSet rs) throws SQLException {
				List<Map<String, Object>> mapList = new ArrayList<>();
				
				while(rs.next()){
					Map<String,Object> maps = new HashMap<>();
					int count = rs.getInt("count");
					String level = rs.getString("level");
					maps.put("count",count);
					maps.put("level", level);
					mapList.add(maps);
				}
				return mapList;
			}
			
		});
	}

	public List<Customer> findByStaffId(int staffId) {
		String sql = "select * from t_customer where staff_id = ?";
		return DbHelp.executeQuery(sql, new BeanListHandler<>(Customer.class, new BasicRowProcessor(new GenerousBeanProcessor())), staffId);
	}

}
