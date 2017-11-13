package com.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

/**
 * This class shows the implementation of filters.
 * filters are interceptor and independent of servlet.
 * once filter is defines into web-application can be applied to any servlet.
 * 
 * @author ravir
 *
 */
@WebFilter(
		urlPatterns= {"/*"},
		displayName="My Login Filters",
		filterName="My Login Filters",
		//servletNames="Examples Login servlet",
		//asyncSupported= false,
		
		initParams= @WebInitParam(
				description= "This is init param of filters",
				name="user",
				value="ravi"
				)
		)
public class MyLoginFilters implements Filter {

	private ServletContext context;
	@Override
	public void init(FilterConfig config) throws ServletException {
		context = config.getServletContext();
		if(null != context) {
			
			context.log("Successully initialized LOG for filters");
			context.log("init-name " + config.getInitParameter("user"));
			context.log("Filter name- " + config.getFilterName());
		}
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		context.log("Start logging filtering");
		response.setContentType("text/html");
		
		ServletOutputStream out = response.getOutputStream();
		out.println("IP Address - " + request.getRemoteAddr());
		context.log("IP Address : " + request.getRemoteAddr());
		
		final String password = request.getParameter("pwd");
		
		if(password.equals("1234") ) {
			context.log("valid password");
			chain.doFilter(request, response);
			
		}else {
			context.log("invalid password");
			out.println("<h4 style='color:red;'>invalid credentials</h4>");
			RequestDispatcher rd = request.getRequestDispatcher("signin.html");
			rd.include(request, response);
		}
	}

	@Override
	public void destroy() {
		context.log("Destroying filters");
	}

}
