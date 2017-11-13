package com.servlet.http;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHttpServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServletContext context;

	@Override
	public void init(ServletConfig config) {
		try {
			super.init(config);
			context = getServletContext();
			if(null != context) {
				context.log("log got initialized successfuly");
			}
		} catch (ServletException e) {
			e.printStackTrace();
		}

	}
	/*
	 * Post request will invoke this method.
	 * 
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException{
		context.log("post method got invoked");
	}

	/*
	 * Get request will invoke this method.
	 * 
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException{
		context.log("get method got invoked");
	}

	/*
	 * Delete request will invoke this method.
	 * 
	 */
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException{
		context.log("delete method got invoked");
	}

	/*
	 * Put request will invoke this method.
	 * 
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException{
		context.log("put method got invoked");
	}
}
