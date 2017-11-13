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

@WebServlet(urlPatterns= {"/listcookie"})
public class CookieDisplay extends HttpServlet {

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
		
		context.log("Servlet -display cookie");
		
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies ) {
			out.println("cookie name is : " + cookie.getName());
			out.println("cookie value  is : " + cookie.getValue());
			out.println("cookie comment is : " + cookie.getComment());
			out.println("cookie version is : " + cookie.getVersion());
			
		}
		
		out.println("all the cookie has been read successfully...");
		out.close();
	}

}
