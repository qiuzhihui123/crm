package com.qiuhui.util;

import java.io.IOException;
import java.util.Properties;

public class Config {

	public static final int COMPANTY_ID = 1;
	public static final int PUBLIC_ID = 0;
	public static final int TASK_STATUS_UNDONE = 0;  
	public static final int TASK_STATUS_DONE = 1;  
	private static Properties property = new Properties();
	
	static{
		try {
			property.load(Config.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param key 给一个键，返回config配置中的相应值
	 */
	public static String getConfig(String key){
		return property.getProperty(key);
		
	}
}
