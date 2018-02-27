package com.bigcat.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String userFilePath = "WEB-INF/XXX";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DownloadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
    	userFilePath = getServletContext().getInitParameter("userFilePath");
    	userFilePath = getServletContext().getRealPath(String.format("WEB-INF\\%s", userFilePath));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
    	throw new ServletException("GET method used with " +
   		     getClass( ).getName( )+": POST method required.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fileName = request.getParameter("fileName");
		String filePath = String.format("%s%s%s", userFilePath, File.separator, fileName);
		System.out.println(filePath);
		File file = new File(filePath);
		if (file.exists() == false) {
			request.setAttribute("message", String.format("%s is not exists", fileName));
			request.getRequestDispatcher("MessageServlet").forward(request, response);
			return;
		}
		
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
        
        FileInputStream in = new FileInputStream(file);
        int len = 0;
        byte buffer[] = new byte[1024];
        OutputStream out = response.getOutputStream();
        while((len=in.read(buffer))>0){
            out.write(buffer, 0, len);
        }
        in.close();
	}

}
