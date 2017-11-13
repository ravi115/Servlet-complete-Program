package com.servlet.generic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 1. This class provide a servlet service by extending GenericServlet class.
 * 2. GenericServlet class is an abstract class and also a protocol independent.
 * 3. Service method in GenericServlet class is abstract, so this is method we have to override it and provide the
 * implementation.
 * 4. This service method will be invoked whenever a new request is made by client.
 *  
 * @author ravir
 *
 */
public class MyGenericServlet extends GenericServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * 
	 * This is GenericServlet service method. 
	 * whenever a request is made by client this method will get invoked.
	 * 
	 */
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		/*
		 * First use of servletContext is to get the logger for this class. 
		 * 
		 */
		ServletContext  context = getServletContext();
		
		final PrintWriter pw = response.getWriter();
		
		context.log("invoked method -  service ");
		pw.append(request.getProtocol()).append("\n");
		pw.append(request.getContentType()).append("\n");
		pw.append(request.getRemoteHost()).append("\n");
		pw.append(request.getServerName()).append("\n");	
	}

}
