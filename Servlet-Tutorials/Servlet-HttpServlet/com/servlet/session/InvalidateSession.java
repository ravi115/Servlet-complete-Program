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
		urlPatterns= {"/checkout"}
		)
public class InvalidateSession extends HttpServlet {

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
		
		context.log("invalidate the session");
		//set the response type to HTML.
		response.setContentType("text/html");
		ServletOutputStream out = response.getOutputStream();
		HttpSession session = request.getSession(false);
		session.invalidate();
		
		/*
		 * check if session is valid. 
		 */
		if(request.isRequestedSessionIdValid() ) {
			out.println("Session is invalidate for this request.");
			out.println("username : " + session.getAttribute("name"));
			out.println("password : " + session.getAttribute("pwd"));
			out.println("session-Id for this request : " + session.getId());
			
		}else {
			out.println("Successflly checked out");
			out.println("<a href='login.html'>click here to login again</a> ");
		}
		
		
		out.close();
	}
}
