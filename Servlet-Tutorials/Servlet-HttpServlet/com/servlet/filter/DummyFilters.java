package com.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * This filter is inserted to just test the functionality of servlet filter chain.
 * This filters will be defined in deployment descriptor(web.xml) files.
 *  
 * @author ravir
 *
 */
public class DummyFilters implements Filter {

	private ServletContext context;
	@Override
	public void init(FilterConfig config) throws ServletException {
		context = config.getServletContext();
		if(null != context) {
			context.log("Successully initialized LOG for filters");
			context.log("Filter name- " + config.getFilterName());
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		context.log("invoked - second filtering");
		context.log("This filter path is : " + request.getRealPath("/"));
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		context.log("Destroying filters");
	}

}
