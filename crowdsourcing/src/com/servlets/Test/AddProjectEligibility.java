package com.servlets.Test;
import com.crowdsourcing.DBConnection.*;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

@WebServlet("/AddProjectEligibility")

public class AddProjectEligibility extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    
    public AddProjectEligibility(){
    	
    }

	//@see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String clientEmail = request.getParameter("email");
		String clientFirstName = request.getParameter("userFirst");
		System.out.println(clientEmail + clientFirstName);
		
		try{			
			// Establish Connection
			DBConnection obj = new DBConnection();
			Connection conn = null;
			conn = obj.DBConnect();
            
			// SQL Query
			PreparedStatement login = conn.prepareStatement(" select id, credibility from test.projects where status_client=2 and status_worker=2 and client=? ");
			login.setString(1,clientEmail);
			
			ResultSet result = login.executeQuery();
			String dbID = "null";
			float dbCredibility = 0;
			float nullCredibility = 0;
			
			while(result.next()){
				dbID = result.getString("id");
				dbCredibility = result.getFloat("credibility");
				System.out.println(dbID + "cvcv" + dbCredibility);
				
				if(dbCredibility == nullCredibility){
					System.out.println(dbID + "cvcv" + dbCredibility);
					
					// HTTP session
				    HttpSession session = request.getSession();
		            session.setAttribute("email", clientEmail);
		            session.setAttribute("user", clientFirstName);
		            session.setMaxInactiveInterval(30*60); //session expires in 30 minutes
		            
		            Cookie cookieEmail = new Cookie("email", clientEmail);
		            Cookie cookieUserFirst = new Cookie("user", clientFirstName);
		            cookieEmail.setMaxAge(30*60);
		            cookieUserFirst.setMaxAge(30*60);
		            response.addCookie(cookieEmail);
		            response.addCookie(cookieUserFirst);
					
					RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/add-project-error.jsp");
					requestDispatcher.forward(request,response);
				}
			}
			
			// HTTP session
		    HttpSession session = request.getSession();
            session.setAttribute("email", clientEmail);
            session.setAttribute("user", clientFirstName);
            session.setMaxInactiveInterval(30*60); //session expires in 30 minutes
            
            Cookie cookieEmail = new Cookie("email", clientEmail);
            Cookie cookieUserFirst = new Cookie("user", clientFirstName);
            cookieEmail.setMaxAge(30*60);
            cookieUserFirst.setMaxAge(30*60);
            response.addCookie(cookieEmail);
            response.addCookie(cookieUserFirst);
			
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/add-project.jsp");
			requestDispatcher.forward(request,response);
		}
		
		catch(Exception e){
			System.out.println("Something went wrong. Please contact system admin.");
			System.err.println(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}