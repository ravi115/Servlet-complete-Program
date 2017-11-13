package com.servlet.redirect;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet will be configured with annotation.
 * @author ravir
 *
 */

@WebServlet(	
		//the name of the servlet.
		name="Annotation Servlet",
		/* url patterns of this servlet.
		 * the comma separated values tells that this servlet will be accessed using either of the pattern.
		 * make sure that URL pattern should be unique among all the servlet classes as well as different from URL
		 * pattern specified in web.xml file
		 */
		urlPatterns= {"/servlet/otherpage","/sendredirect"},
		//servlet will be loaded as soon as server starts.
		loadOnStartup = 15,
		//description of the servlet
		description="This is complete annotation based servlet example",
		//display name of the servlet.
		displayName="Annotation Test Servlet",
		//small icon for this servlet
		smallIcon="https://www.google.co.in/search?q=icon&source=lnms&tbm=isch&sa=X&ved=0ahUKEwiWsL7GxqzXAhUIrY8KHbzBBtQQ_AUICigB&biw=1280&bih=918#imgrc=XEenH8G75i3dzM:",
		//init-param for servlet config. belong to this servlet only.
		initParams= {
						@WebInitParam(name="user", value="ravi ranjan"),
						@WebInitParam(name="test", value="test annotation based servlet")
					},
		//this servlet doen;t support async request.
		asyncSupported=false
		)
public class ServletRedirect extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ServletContext context;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		context = getServletContext();
		if(null != context) {
			context.log("Successully initialized LOG");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		context.log("process - SendReDirect");
		
		ServletConfig config = getServletConfig();
		ServletOutputStream out = res.getOutputStream();
		
		out.println("Servlet-name is : " + config.getServletName());
		out.println("Servlet-description is : " + getServletInfo());
		
		Enumeration<String> objConfig = config.getInitParameterNames();
		while(objConfig.hasMoreElements()) {
			String strConfig = objConfig.nextElement();
			out.println("init-param : " + strConfig);
			out.println("init-value : " + config.getInitParameter(strConfig));
		}
		
		out.println("Servlet-path-info : " + req.getPathInfo());
		out.println("Servlet-request-URL" + req.getRequestURI());
		
		context.log("redirect this servlet to welcome page");
	
		
		/*
		 * redirect this servlet to new page "welcome.html"
		 * a serlvet can be redirected on another servlet, JSP, or HTML. 
		 */
		res.sendRedirect("welcome.html");
	}
}
