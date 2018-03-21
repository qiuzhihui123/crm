package com.qiuhui.util;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qiuhui.exception.DataAccessException;


public class DbHelp {

	private static QueryRunner runner = new QueryRunner(ConnectionManager.getDatasource());
	private static Logger logger = LoggerFactory.getLogger(DbHelp.class);
	
	
	public static int executeInsert(String sql,Object...params){
		
		try {
			
			logger.info("输出sql为{}",sql);
			System.out.println(sql);
			return runner.insert(sql, new ScalarHandler<Long>(), params).intValue();
		} catch (SQLException e) {
			logger.error("执行{}异常",sql);
		throw new DataAccessException("执行" + sql + "异常",e);
		} 
		
	}
	
	
	public static void executeUpdate(String sql,Object...params){
				
		try {
			
			runner.update(sql, params);
			
		} catch (SQLException e) {
		throw new DataAccessException("执行" + sql + "异常",e);
		} 
		
	}

	public static<T> T executeQuery(String sql,ResultSetHandler<T> rsh,Object...params){
		
		try {
			logger.info("输出sql为{}",sql);
			return runner.query(sql, rsh,params);
		} catch (SQLException e) {
			logger.error("执行{}异常",sql);
			throw new DataAccessException("执行"+ sql + "异常",e);
		} 
	}
	
}
