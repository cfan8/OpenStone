package com.linangran.openstone.framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 1/21/14.
 */
public class GameLogger {
	private static Logger applicationLogger = LogManager.getLogger(GameLogger.class.getName());

	public static void error(Throwable throwable)
	{
		applicationLogger.error(throwable);
	}

	public static void error(String msg, Throwable throwable)
	{
		applicationLogger.error(msg, throwable);
	}

	public static Logger getLogger(Class<?> clazz)
	{
		return LogManager.getLogger(clazz);
	}
}
