package com.servlet.cookie;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns= {"/cookie"}
		)
public class MyCookie extends HttpServlet {

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
		
		context.log("Servlet -cookie");
		
		final String user = request.getParameter("user");
		final String password = request.getParameter("pwd");
		out.println("welcome : " + user);
		
		/*
		 * create a cookie 
		 */
		Cookie cookie = new Cookie("username", user);
		cookie.setComment("testing purpose");
		cookie.setVersion(1);
		
		/*
		 * set newly created cookie with response.
		 */
		response.addCookie(cookie);
		
		/*
		 * redirect to cookie display page where cookie data will be read, once user clicks on below link 
		 */
		out.println("<a href='listcookie'>click here to see the cookie information</a> ");
		out.close();
	}
}
