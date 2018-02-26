package com.bigcat.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloForm
 */
@WebServlet("/HelloForm")
public class HelloForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		String userName = request.getParameter("user");
		//String password = request.getParameter("pwd");
		if (request.getParameter("login") != null) {
			response.getWriter().println("<h1>Hello, " + userName + "</h1>" + "<p><a href='Hello.html'>Exit</p>");
		} else {
			response.getWriter().println("<h1>Hello Regist</h1> <p><a href='Hello.html'>Exit</p>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Enumeration<String> param = request.getParameterNames();
		String message = "";
		String name;
		while (param.hasMoreElements()) {
			name = (String) param.nextElement();
			message = String.format("%s,%s=%s", 
					message, 
					name, 
					URLDecoder.decode(request.getParameter(name), "utf-8"));		
		}
		response.getWriter().println(message.substring(1));
	}

}
