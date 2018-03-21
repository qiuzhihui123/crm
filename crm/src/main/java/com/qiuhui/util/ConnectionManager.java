package com.qiuhui.util;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.qiuhui.exception.DataAccessException;



public class ConnectionManager {

	private  static String DRIVER;
	private  static String URL ;
	private  static String USER;
	private  static String PASSWORD;
	//创建个连接池对象 dataSource
	private static BasicDataSource dataSource = new BasicDataSource();
	//创建一个properties的键值对映射集合用这个对象获取配置文件
	private static Properties prop = new Properties();
	static{
		try {
			prop.load(ConnectionManager.class.getClassLoader().getResourceAsStream("config.properties"));
			DRIVER = prop.getProperty("jdbc.driver");
			URL = prop.getProperty("jdbc.url");
			USER = prop.getProperty("jdbc.username");
			PASSWORD = prop.getProperty("jdbc.password");
			System.out.println(URL);
		} catch (IOException e) {
			throw new DataAccessException("获得数据库配置文件异常",e);
		}
	
			dataSource.setDriverClassName(DRIVER);
			dataSource.setUrl(URL);
			dataSource.setUsername(USER);
			dataSource.setPassword(PASSWORD);
			
			dataSource.setInitialSize(5);
			dataSource.setMaxIdle(20);
			dataSource.setMinIdle(5);
			dataSource.setMaxWaitMillis(5000);
	
	}
	public static DataSource getDatasource(){
		return dataSource;
		
	}	
	
}
