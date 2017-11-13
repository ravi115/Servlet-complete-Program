package com.servlet.requestdispatcher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author ravir
 *
 */
@WebServlet (
			name="This Request Dispatcher Servlet",
			urlPatterns = {"/login"},
			loadOnStartup=5
		)
public class MyRequestDispatcher extends HttpServlet {

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
	
	/**
	 * this method shows request dispatcher mechanism. 
	 * @throws IOException 
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		
		res.setContentType("text/html");
		final String user = req.getParameter("user");
		final String password = req.getParameter("pwd");
		
		context.log("User : " + user);
		context.log("Password : " + password);
		
		if(password.equals("1234")) {
			
			context.log("valid User");
			/*
			 * to forward request and response of this servlet to any other resource(servlet, JSP, HTMl),
			 * just spevify the name of other resources. 
			 * 
			 */
			RequestDispatcher rd = req.getRequestDispatcher("welcome");
			rd.forward(req, res);
			
		}else {
			context.log("InValid User");
			
			ServletOutputStream out = res.getOutputStream();
			out.println("<h5>wrong credentials</h5>");
			/*
			 * if we want to include any response message to the any resource, we can do it in the below ways. 
			 * 
			 */
			RequestDispatcher rd = req.getRequestDispatcher("index.html");
			rd.include(req, res);
			
			out.close();
		}
	}
}
