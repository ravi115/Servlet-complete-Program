package com.servlet.life;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * This class is to test all the life cycle method of servlet interface.
 * @author ravir
 *
 */
public class ServletLifeCycle implements Servlet {

	String strDestroy;
	String strServletConfig;
	String strServletInfo;
	String strInit;
	
	@Override
	public void destroy() {
		System.out.println("Destroy method is called");
		strDestroy ="Destroying servlet";
		
	}

	@Override
	public ServletConfig getServletConfig() {
		
		System.out.println("ServletConfig method is called");
		strServletConfig = "got all the config";
		return null;
	}

	@Override
	public String getServletInfo() {
		System.out.println("Servlet-info method is called");
		strServletInfo= "Servlet information";
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("init method is called");
		strInit = "Servlet is initialized";
		
	}

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		System.out.println("service is callled method is called");
		PrintWriter obj = response.getWriter();
		obj.append("init=").append(strInit).append(" ").append("ServletConfig =").append(strServletConfig).append("ServletInfo")
			.append(strServletInfo).append("Destroy = ").append(strDestroy);
		
	}

}
