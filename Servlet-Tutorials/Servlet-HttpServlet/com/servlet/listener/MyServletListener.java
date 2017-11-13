package com.servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Listener destroyed");
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Listener initialized");
		
	}

}
