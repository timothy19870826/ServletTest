package com.bigcat.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

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
    	tempPath = getServletContext().getRealPath("WEB-INF\\temp");
    	File temp = new File(tempPath);
    	if (temp.exists() == false) {
    		temp.mkdirs();
    	}
    	userFilePath = getServletContext().getInitParameter("userFilePath");
    	userFilePath = getServletContext().getRealPath(String.format("WEB-INF\\%s", userFilePath));
    	File save = new File(tempPath);
    	if (save.exists() == false) {
    		save.mkdirs();
    	}
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	throw new ServletException("GET method used with " +
		     getClass( ).getName( )+": POST method required.");
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
		//servletFileUpload.setHeaderEncoding("UTF-8");
		//request.getHeader("charset")
		System.out.println(request.getHeader("charset"));
		try {
			File file;
			int leng = 0;
			byte[] buff = new byte[256];
			FileItemIterator fileItemIterator = servletFileUpload.getItemIterator(request);
			while (fileItemIterator.hasNext()) {
				FileItemStream fileItem = fileItemIterator.next();
				if (fileItem.isFormField()) {
					continue;
				}
	            // 获取上传文件的参数
	            //String fieldName = fileItem.getFieldName();
	            String fileName = fileItem.getName();
	            //String contentType = fileItem.getContentType();
	            // 写入文件
	            InputStream inputStream = fileItem.openStream();
	            if (inputStream == null) {
	            	continue;
	            }
	            if(fileName.lastIndexOf("\\") >= 0 ){
	               file = new File( userFilePath + "\\" +
	               fileName.substring( fileName.lastIndexOf("\\")+1)) ;
	            }else{
	               file = new File( userFilePath + "\\" + fileName) ;
	            }
	            System.out.println(file.getPath());
	            leng = 0;
	            FileOutputStream fileOutputStream = new FileOutputStream(file);
	            while ((leng = inputStream.read(buff)) > 0) {
	            	fileOutputStream.write(buff, 0, leng);
				}
	            fileOutputStream.close();
	            inputStream.close();
	            response.getWriter().println("Uploaded Filename: " + fileName + "<br>");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
