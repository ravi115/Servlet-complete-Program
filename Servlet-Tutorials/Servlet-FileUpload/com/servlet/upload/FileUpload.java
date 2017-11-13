package com.servlet.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

/**
 * This program is to demonstrate file uploading. 
 * @author ravir
 *
 */
@WebServlet(
			urlPatterns= {"/fileupload"}
		)
public class FileUpload extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String strUpload_Directory = "E:/FILE/";
	
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
		
		context.log("Servlet -file upload");
		
		//check whether request contains any file content.
		boolean bIsMultiPart = ServletFileUpload.isMultipartContent(request);
		
		//process only if multipart content.
		if(bIsMultiPart ) {
			
			//create a factory for disk based file system
			FileItemFactory fileItemFactory = new DiskFileItemFactory();
			
			//create a new file upload handler
			ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
			
			try {
				//parse the file request.
				List<FileItem> multiParts = servletFileUpload.parseRequest(request);
				
				for(FileItem fileItem : multiParts ) {
					if(!fileItem.isFormField() ) {
						
						//read the file name from multiparts
						final String fileName = new File(fileItem.getName()).getName();
						//fetch the file from system disk.
						fileItem.write(new File(strUpload_Directory + File.separator + fileName));
					}
				}
				//file uploaded successfully.
				request.setAttribute("message", "File Uploaded successfully");
				out.println("file uploaded successfully....");
				
			}catch(Exception e) {
				context.log("caught exception is :" + e.getLocalizedMessage());
				request.setAttribute("message", "Failed to Upload File");
			}
			
		}else {
			out.println("failed to upload the file");
		}
	}
}
