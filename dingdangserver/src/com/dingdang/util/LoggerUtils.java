package com.dingdang.util;

import org.slf4j.LoggerFactory;

public class LoggerUtils
{
	static org.slf4j.Logger log = LoggerFactory.getLogger(LoggerUtils.class
			.getName());
	
	public static void info(String str)
	{
		log.info(str);
	}
	
	public static void debug(String str)
	{
		log.debug(str);
	}
	
	public static void error(String str)
	{
		log.error(str);
	}
}
