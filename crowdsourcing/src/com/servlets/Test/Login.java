package com.servlets.Test;
import com.crowdsourcing.DBConnection.*;
import java.sql.*;
import java.sql.Connection;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
**/

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	// Default constructor
    public Login(){
        
    }

	//@see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		
		// Check console if everything is retrieved from previous page.
		System.out.println(email + password + type);
		
		try{			
			// Establish Connection
			DBConnection obj = new DBConnection();
			Connection conn = null;
			conn = obj.DBConnect();
			
			// SQL Query
			PreparedStatement login = conn.prepareStatement("select from test.users(email, password, type)" + "where(email=?,password=?,type=?)");
			
			login.setString(1,email);
			login.setString(2,password);
			login.setString(3,type);
			
			ResultSet result = login.executeQuery();
			
			while(result.next())
			{
				String dbEmail = result.getString("email");
				String dbPassword = result.getString("password");
				String dbType = result.getString("type");
				//request.setAttribute("email",formEmail );
				
				if(email == dbEmail && password == dbPassword && type == dbType){
				// HTTP session
				HttpSession session = request.getSession();
	            session.setAttribute("email", email);
	            session.setAttribute("password", password);
	            session.setMaxInactiveInterval(30*60); //session expires in 30 minutes
	            
	            Cookie userEmail = new Cookie("email", email);
	            Cookie userPassword = new Cookie("password", password);
	            userEmail.setMaxAge(30*60);
	            userPassword.setMaxAge(30*60);
	            response.addCookie(userEmail);
	            response.addCookie(userPassword);
	            
	            RequestDispatcher requestDispatcher = request.getRequestDispatcher("home-client.jsp");
	            requestDispatcher.forward(request, response);
				}
				     
				else{
					System.out.println("Mismatch");
				}
			}
		}
		catch(Exception e){
			System.out.println("Something went wrong. Please contact system admin.");
			System.err.println(e.getMessage());
		}
	}

	// @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}