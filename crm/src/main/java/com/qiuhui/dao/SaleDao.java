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
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.qiuhui.entity.Customer;
import com.qiuhui.entity.SaleChance;
import com.qiuhui.util.DbHelp;

public class SaleDao {

	public int countByStaffId(int staffId) {
		String sql = "select count(*) from sale_chance where staff_id = ?";
		return DbHelp.executeQuery(sql, new ScalarHandler<Long>(), staffId).intValue();
	}

	public List<SaleChance> findListByStaffAndPage(int staffId, int start, int pageSize) {
		String sql = "select * from sale_chance sc inner JOIN t_customer c ON sc.cust_id = c.id where sc.staff_id = ? limit ?,?";

		return DbHelp.executeQuery(sql, new ResultSetHandler<List<SaleChance>>() {

			@Override
			public List<SaleChance> handle(ResultSet rs) throws SQLException {
				List<SaleChance> chanceList = new ArrayList<>();
				while(rs.next()) {
					
					SaleChance chance = new SaleChance();
					chance.setId(rs.getInt("id"));
					chance.setName(rs.getString("name"));
					chance.setWorth(rs.getFloat("worth"));
					chance.setProcess(rs.getString("process"));
					chance.setContent(rs.getString("content"));
					chance.setCreateTime(rs.getTimestamp("create_time"));
					chance.setLastTime(rs.getTimestamp("last_time"));
					chance.setStaffId(rs.getInt("staff_id"));
					
					Customer customer = new Customer();
					
					customer.setCustName(rs.getString("cust_name"));
				
					
					chance.setCustomer(customer);
					chanceList.add(chance);
				}
				return chanceList;
			}
		}, staffId, start, pageSize);
	}

	public int save(SaleChance saleChance) {
		String sql = "insert into sale_chance (name, cust_id, worth,process,content,last_time,staff_id) values (?,?,?,?,?,?,?)";;
		return DbHelp.executeInsert(sql, saleChance.getName(), saleChance.getCustId(),saleChance.getWorth(),saleChance.getProcess(),saleChance.getContent(),saleChance.getLastTime(),saleChance.getStaffId());
	}

	public SaleChance findById(int saleId) {
		String sql = "select * from sale_chance sc inner join t_customer c on sc.cust_id = c.id where sc.id = ?";
		return DbHelp.executeQuery(sql, new ResultSetHandler<SaleChance>() {

			@Override
			public SaleChance handle(ResultSet rs) throws SQLException {
				SaleChance chance = null;
				if(rs.next()) {
					chance = new SaleChance();
					chance.setId(rs.getInt("id"));
					chance.setCustId(rs.getInt("cust_id"));
					chance.setName(rs.getString("name"));
					chance.setWorth(rs.getFloat("worth"));
					chance.setProcess(rs.getString("process"));
					chance.setContent(rs.getString("content"));
					chance.setCreateTime(rs.getTimestamp("create_time"));
					chance.setLastTime(rs.getTimestamp("last_time"));
					chance.setStaffId(rs.getInt("staff_id"));
					
					Customer customer = new Customer();
					customer.setId(rs.getInt("cust_id"));
					customer.setCustName(rs.getString("cust_name"));
					customer.setSex(rs.getString("sex"));
					customer.setAddress(rs.getString("address"));
					customer.setJobTitle(rs.getString("job_title"));
					customer.setMobile(rs.getString("mobile"));
					customer.setTrade(rs.getString("trade"));
					customer.setSource(rs.getString("source"));
					customer.setLevel(rs.getString("level"));
					customer.setMark(rs.getString("mark"));
					customer.setReminder(rs.getString("reminder"));
					
					chance.setCustomer(customer);
				}
				return chance;
			}
		}, saleId);
	}

	public void update(SaleChance chance) {
		String sql = "update sale_chance set worth = ? , process = ?, content = ?, last_time = ? where id = ?";
		DbHelp.executeUpdate(sql, chance.getWorth(),chance.getProcess(),chance.getContent(),chance.getLastTime(),chance.getId());
	}

	public void delById(int id) {
		String sql = "delete from sale_chance where id = ?";
		DbHelp.executeUpdate(sql, id);
	}

	public int countAllProcess() {
		String sql = "select count(*) from sale_chance";
		return DbHelp.executeQuery(sql, new ScalarHandler<Long>()).intValue();
	}

	public List<Map<String, Object>> findProcessCount() {
		String sql = "select count(*) as count,process from sale_chance group by process order by count desc";
		return DbHelp.executeQuery(sql, new ResultSetHandler<List<Map<String, Object>>>(){

			@Override
			public List<Map<String, Object>> handle(ResultSet rs) throws SQLException {
				List<Map<String, Object>> mapList = new ArrayList<>();
				
				while(rs.next()){
					Map<String,Object> maps = new HashMap<>();
					int count = rs.getInt("count");
					String process = rs.getString("process");
					maps.put("count",count);
					maps.put("process", process);
					mapList.add(maps);
				}
				return mapList;
			}
			
		});
	}

	public List<SaleChance> findSaleChanceBYCustId(String custId) {
		String sql = "select * from sale_chance where cust_id = ?";
		return DbHelp.executeQuery(sql, new BeanListHandler<>(SaleChance.class, new BasicRowProcessor(new GenerousBeanProcessor())), custId);
	}

	public int count(String custId) {
		String sql = "select count(*) from sale_chance where cust_id = ?";
		return DbHelp.executeQuery(sql, new ScalarHandler<Long>(), custId).intValue();
	}

	public List<SaleChance> findByPage(String custId, int start, int pageSize) {
		String sql = "select * from sale_chance where cust_id = ? limit ?,?";
		return DbHelp.executeQuery(sql, new BeanListHandler<>(SaleChance.class,new BasicRowProcessor(new GenerousBeanProcessor())), custId,start,pageSize);
	}

}
