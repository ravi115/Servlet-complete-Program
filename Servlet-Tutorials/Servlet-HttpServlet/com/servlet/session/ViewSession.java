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
			urlPatterns= {"/view"}
		)
public class ViewSession extends HttpServlet {

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//set the response type to HTML.
		response.setContentType("text/html");
		ServletOutputStream out = response.getOutputStream();
		
		context.log("Servlet -session - retrieval");
	
		/*
		 * retrieve the session if exists. 
		 */
		
		HttpSession session = request.getSession(false);
		out.println("<h2>welcome User : " + session.getAttribute("name") + "</h2>");
		out.println("your password is : " + session.getAttribute("pwd"));
		out.println("Session id : " + session.getId());
		
		out.println("<a href='checkout'>want to do checkout</a>");
		out.close();
	}
}
