package com.servlet.context;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServletContext extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ServletContext context;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		context = getServletContext();
		if(null != context) {
			context.log("Successully initialized LOG");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		context.log("Invoked method- Get");
		
		ServletOutputStream out = response.getOutputStream();
		/*
		 * read the context parameter using SercletContext. 
		 */
		Enumeration<String> objContextParam = context.getInitParameterNames();
		while(objContextParam.hasMoreElements() ) {
			String strContext = objContextParam.nextElement();
			out.println("Servlet-context-param-name  : " + strContext);
			out.println("Servlet-context-param-value  : " + context.getInitParameter(strContext));
		}
		
		/*
		 * we can also set the attribute of context which is available to all servlet for this web-container[Servlet-XML-based].  
		 */
		context.setAttribute("car", "BMW");
		
		/*
		 * we can also get the attribute.
		 */
		out.println("The attribute for this servlet is : " + context.getAttribute("car"));
		
		out.println("Servlet-info : " + context.getServerInfo());
		
		out.println("Servlet-path : " + context.getContextPath());
	}
}
