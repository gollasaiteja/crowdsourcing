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

/**
 * Servlet implementation class ViewProject
**/

@WebServlet("/ViewProject")
public class ViewProject extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	// Default constructor
    public ViewProject(){
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
			PreparedStatement projectDetails = conn.prepareStatement(" select name,description,skill,availability,location,rate,credibility,status_client,status_worker,assigned_worker from test.projects where id=? ");
			projectDetails.setString(1,projectID);
			
			ResultSet Project = projectDetails.executeQuery();
			ArrayList Rows = new ArrayList();
			String dbName = "null";
			String dbDescription = "null";
			String dbSkill = "null";
			String dbAvailability = "null";
			String dbLocation = "null";
			String dbRate = "null";
			String dbStatusClient = "null";
			String dbStatusWorker = "null";
			String dbAssignedWorker = "null";
			
			while(Project.next()){
				dbName = Project.getString("name");
				dbDescription = Project.getString("description");
				dbSkill = Project.getString("skill");
				dbAvailability = Project.getString("availability");
				dbLocation = Project.getString("location");
				dbRate = Project.getString("rate");
				dbStatusClient = Project.getString("status_client");
				dbStatusWorker = Project.getString("status_worker");
				dbAssignedWorker = Project.getString("assigned_worker");
				
				ArrayList row = new ArrayList();
			    for (int i = 1; i <= 1 ; i++){
			    	row.add(Project.getString("name"));
			    	row.add(Project.getString("description"));
			    	row.add(Project.getString("availability"));
			    	row.add(Project.getString("location"));
			    	row.add(Project.getString("rate"));
			    	row.add(Project.getString("status_client"));
			    	row.add(Project.getString("status_worker"));
			    	row.add(Project.getString("assigned_worker"));
			    }
			    Rows.add(row);
				
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
			}
			request.setAttribute("projectList", Rows);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/project.jsp");
			requestDispatcher.forward(request,response);	
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