package com.cibil.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author arunbal.srinivasan
 *
 */
public class AppContext extends ClassPathXmlApplicationContext {
	private AppContext() {

	}

	private static ClassPathXmlApplicationContext instance;

	/**
	 * @return
	 */
	public static ClassPathXmlApplicationContext getInstance() {
		if (instance == null) {
			instance = new ClassPathXmlApplicationContext("/applicationContext.xml");
			return instance;
		}
		return instance;
	}
}
