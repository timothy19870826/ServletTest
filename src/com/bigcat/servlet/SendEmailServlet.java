package com.bigcat.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;
import javax.activation.*;


/**
 * Servlet implementation class SendEmailServlet
 */
@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String from = request.getParameter("from");
		//String user = request.getParameter("user");
		//String password = request.getParameter("password");
		String to = request.getParameter("to");
		String subject = request.getParameter("subject");
		String message = request.getParameter("message");
		
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "localhost");
		properties.put("mail.debug", true);
		Session session = Session.getDefaultInstance(properties);
		MimeMessage emailMsg = new MimeMessage(session);
		try {
			emailMsg.setFrom(new InternetAddress(from));
			emailMsg.setRecipient(RecipientType.TO, new InternetAddress(to));
			emailMsg.setSubject(subject);
			emailMsg.setText(message);
			emailMsg.setSentDate(new Date());
			Transport.send(emailMsg);
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
