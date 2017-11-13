package com.servlet.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Config Implementation.
 * @author ravir
 *
 */
public class MyServletConfig extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ServletContext context;
	private ServletConfig config;
	
	@Override
	public void init(ServletConfig config) {
		try {
			super.init(config);
			
			context = getServletContext();
			if(null != context) {
				context.log("log got initialized successfuly");
			}
			/*
			 * initialize servlet configuration file and creates the servletconfig object for this servlet.
			 * Servlet config is per servlet.
			 */
			this.config = getServletConfig();
			if(null != this.config) {
				context.log("config object is now available for this servlet");
			}
			
		} catch (ServletException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Get request will invoke this method.
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		context.log("get method got invoked");
		
		PrintWriter objPw = res.getWriter();
		Enumeration<String> objInitParam = config.getInitParameterNames();
		
		while(objInitParam.hasMoreElements()) {
			String strParamName = objInitParam.nextElement();
			objPw.println("init-param-name : " + strParamName);
			objPw.println("init-apram-value : " + config.getInitParameter(strParamName));
		}
	}
}
