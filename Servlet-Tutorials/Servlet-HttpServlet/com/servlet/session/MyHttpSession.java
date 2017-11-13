package com.servlet.session;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(
			urlPatterns= {"/track"}
		)
public class MyHttpSession extends HttpServlet {

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
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//set the response type to HTML.
		response.setContentType("text/html");
		ServletOutputStream out = response.getOutputStream();
		
		context.log("Servlet -session");
		
		final String user = request.getParameter("user");
		final String password = request.getParameter("pwd");
		out.println("welcome : " + user);
		
		/*
		 * create a session, since this is new request for this user. 
		 */
		HttpSession session = request.getSession();
		session.setAttribute("name", user);
		session.setAttribute("pwd", password);
		
		out.println("<a href='view'>Visit here to see your password</a> ");
		out.close();
	}
}
