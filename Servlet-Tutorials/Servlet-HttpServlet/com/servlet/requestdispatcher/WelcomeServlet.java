package com.servlet.requestdispatcher;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet (
			
			urlPatterns= {"/welcome"}
		)
public class WelcomeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ServletContext context;
	/**
	 * initialize the context log. 
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		context = getServletContext();
		if(null != context) {
			context.log("Successully initialized LOG");
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		context.log("Successfully dispatched to this page");
		
		ServletOutputStream out = res.getOutputStream();
		out.println("you are a valid user");
		out.println("welcome " + req.getParameter("user"));
		
		out.close();
	}
}
