package com.bigcat.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class DataBaseAssess
 */
@WebServlet("/DataBaseAssess")
public class DataBaseAssess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String JDBC_DRIVER="com.mysql.jdbc.Driver"; 
	private static final String DB_URL="jdbc:mysql://localhost/TEST";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataBaseAssess() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println(getServletContext().getRealPath(""));
			Class.forName(JDBC_DRIVER);
			Connection connection = DriverManager.getConnection(DB_URL,"root","admin");
			Statement statement = connection.createStatement();
			String cmd = "SELECT id, userName FROM UserInfo";
			ResultSet resultSet = statement.executeQuery(cmd);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String userName = resultSet.getString("userName");
				response.getWriter().append(String.format("id:%s,name:%s\n", id, userName));				
			}
			statement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().append(e.getMessage());
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
