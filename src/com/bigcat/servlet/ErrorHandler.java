package com.bigcat.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ErrorHandle
 */
@WebServlet("/ErrorHandler")
public class ErrorHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ErrorHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer status_code = (Integer) request.getAttribute("javax.servlet.error.status_code");
		String servlet_name = (String) request.getAttribute("javax.servlet.error.servlet_name");
		if (servlet_name == null) {
			servlet_name = "Unknow";
		}
		String request_uri = (String) request.getAttribute("javax.servlet.error.request_uri");
		String exception_type = (String) request.getAttribute("javax.servlet.error.exception_type");
		String message = (String) request.getAttribute("javax.servlet.error.message");
		//Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");
		
		String error_response = String.format("%s:%s", "status_code", status_code);
		error_response = String.format("%s\n%s:%s", error_response, "servlet_name", servlet_name);
		error_response = String.format("%s\n%s:%s", error_response, "request_uri", request_uri);
		error_response = String.format("%s\n%s:%s", error_response, "exception_type", exception_type);
		error_response = String.format("%s\n%s:%s", error_response, "message", message);
		
		response.getWriter().println(error_response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
