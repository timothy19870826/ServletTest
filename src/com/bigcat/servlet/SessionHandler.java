package com.bigcat.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionHandler
 */
@WebServlet("/SessionHandler")
public class SessionHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String userName = "";
		if (session != null) {
			userName = (String) session.getAttribute("userName");
		}
		response.setContentType("text/html");
		if (userName == null || userName.isEmpty()) {
			response.getWriter().println("<form action='LoginServlet'>");
			response.getWriter().println("<input type='hidden' name='location' value='SessionHandler'>");
			response.getWriter().println("<input type='text' name='userName'>");
			response.getWriter().println("<input type='submit' name='login'>");
			response.getWriter().println("</form>");
		}
		else {
			response.getWriter().println("<h2>Hello ," + userName + "</h2><p><a href='ExitServlet?location=SessionHandler'>Exit</a></p>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
