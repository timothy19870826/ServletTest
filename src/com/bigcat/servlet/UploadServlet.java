package com.bigcat.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.sun.javafx.scene.shape.PathUtils;
import com.sun.org.apache.xml.internal.serialize.Printer;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String userFilePath = "";  
    private String tempPath = "";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	userFilePath = getInitParameter("userFilePath");
    	tempPath = getServletContext().getRealPath("WEB-INF\\temp");
    	File temp = new File(tempPath);
    	if (temp.exists() == false) {
    		temp.mkdirs();
    	}
    	userFilePath = getServletContext().getRealPath(String.format("WEB-INF\\%s", userFilePath));
    	File save = new File(tempPath);
    	if (save.exists() == false) {
    		save.mkdirs();
    	}
    } 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter printer = response.getWriter();
		if (ServletFileUpload.isMultipartContent(request) == false) {
			printer.append("<html>")
			.append("<head>")
			.append("<title>Servlet upload</title>")
			.append("</head>")
			.append("<body>")
			.append("<p>No file uploaded</p>")
			.append("</body>")
			.append("</html>");
			return ;
		}
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		diskFileItemFactory.setSizeThreshold(4*1024*1024);
		diskFileItemFactory.setRepository(new File(tempPath));
		
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setSizeMax(50*1024*1024);
		
		try {
			File file;
			List<FileItem> fileList = servletFileUpload.parseRequest((RequestContext) request);
			for (FileItem fileItem : fileList) {
				if (fileItem.isFormField()) {
					continue;
				}
	            // 获取上传文件的参数
	            String fieldName = fileItem.getFieldName();
	            String fileName = fileItem.getName();
	            String contentType = fileItem.getContentType();
	            boolean isInMemory = fileItem.isInMemory();
	            long sizeInBytes = fileItem.getSize();
	            // 写入文件
	            if( fileName.lastIndexOf("\\") >= 0 ){
	               file = new File( userFilePath + 
	               fileName.substring( fileName.lastIndexOf("\\"))) ;
	            }else{
	               file = new File( userFilePath + 
	               fileName.substring(fileName.lastIndexOf("\\")+1)) ;
	            }
	            fileItem.write( file ) ;
	            response.getWriter().println("Uploaded Filename: " + fileName + "<br>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
