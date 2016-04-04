package com.servlets.Test;
import com.crowdsourcing.DBConnection.*;
import java.sql.*;
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

/** Servlet implementation class DeleteProject **/

@WebServlet("/DeleteProject")
public class DeleteProject extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	// Default constructor
    public DeleteProject(){
        // TODO Auto-generated constructor stub
    }

	//@see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String projectID = request.getParameter("project_id");
		String firstName = request.getParameter("user_first");
		String email = request.getParameter("email");
		
		// Check console if everything is retrieved from previous page.
		System.out.println(projectID);
		
		try{			
			// Establish Connection
			DBConnection obj = new DBConnection();
			Connection conn = null;
			conn = obj.DBConnect();
			
			// SQL Query
			PreparedStatement deleteProject = conn.prepareStatement(" delete from test.projects where id=? ");
			deleteProject.setString(1,projectID);			
			int project = deleteProject.executeUpdate();
			
			if(project == 1){
				// HTTP session
				HttpSession session = request.getSession();
	            session.setAttribute("email", email);
	            session.setAttribute("user", firstName);
	            session.setMaxInactiveInterval(30*60); //session expires in 30 minutes
	            
	            Cookie userEmail = new Cookie("email", email);
	            Cookie userFirst = new Cookie("user", firstName);
	            userEmail.setMaxAge(30*60);
	            userFirst.setMaxAge(30*60);
	            response.addCookie(userEmail);
	            response.addCookie(userFirst);
	            
	            RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home-client.jsp");
				requestDispatcher.forward(request,response);
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