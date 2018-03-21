package com.qiuhui.test;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log4jTestCase {

	Logger logger = LoggerFactory.getLogger(Log4jTestCase.class);
	
	@Test
	public void logTest(){
		logger.trace("我是trace");
		logger.debug("我是debug");
		logger.info("{},{},我是info","邱志辉","localhost");
		logger.warn("我是warn");
		logger.error("我是error");
//		logger.fatal("我是fatal");
	}
	
}
