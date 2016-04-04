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

/** Servlet implementation class ViewProject **/

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
			PreparedStatement getProject = conn.prepareStatement(" select name,description,skill,availability,location,rate,credibility,status_client,status_worker,assigned_worker from test.projects where id=? ");
			getProject.setString(1,projectID);
			
			ResultSet project = getProject.executeQuery();
			ArrayList Rows = new ArrayList();
			String dbName = "null";
			String dbDescription = "null";
			String dbSkill = "null";
			String dbAvailability = "null";
			String dbLocation = "null";
			String dbRate = "null";
			String dbCredibility = "null";
			String dbStatusClient = "null";
			String dbStatusWorker = "null";
			String dbAssignedWorker = "null";
			
			while(project.next()){
				dbName = project.getString("name");
				dbDescription = project.getString("description");
				dbSkill = project.getString("skill");
				dbAvailability = project.getString("availability");
				dbLocation = project.getString("location");
				dbRate = project.getString("rate");
				dbCredibility = project.getString("credibility");
				dbStatusClient = project.getString("status_client");
				dbStatusWorker = project.getString("status_worker");
				dbAssignedWorker = project.getString("assigned_worker");
				
				// Must be secured
				PreparedStatement getPaypalCode = conn.prepareStatement(" select email, paypal from test.workers where id=? ");
				getPaypalCode.setString(1,dbAssignedWorker);
				String dbPaypal = "null";
				String dbwemail = "null";
				ResultSet paypal = getPaypalCode.executeQuery();
				
				while(paypal.next()){
					dbPaypal = paypal.getString("paypal");
					dbwemail = paypal.getString("email");
				}
				
				ArrayList<String>row = null;
			    for (int i = 1; i <= 1 ; i++){
			    	row = new ArrayList<String>();
			    	row.add(project.getString("name"));
			    	row.add(project.getString("description"));
			    	row.add(project.getString("skill"));
			    	row.add(project.getString("availability"));
			    	row.add(project.getString("location"));
			    	row.add(project.getString("rate"));
			    	row.add(project.getString("credibility"));
			    	row.add(project.getString("status_client"));
			    	row.add(project.getString("status_worker"));
			    	row.add(project.getString("assigned_worker"));
			    	row.add(dbPaypal);
			    	row.add(projectID);
			    	row.add(dbwemail);
			    	Rows.add(row);
			    	System.out.println(row);
			    }
				
				// HTTP session
				HttpSession session = request.getSession();
	            session.setAttribute("email", email);
	            session.setAttribute("userFirst", firstName);
	            session.setMaxInactiveInterval(30*60); //session expires in 30 minutes
	            
	            Cookie userEmail = new Cookie("email", email);
	            Cookie userFirst = new Cookie("userFirst", firstName);
	            userEmail.setMaxAge(30*60);
	            userFirst.setMaxAge(30*60);
	            response.addCookie(userEmail);
	            response.addCookie(userFirst);
			}
			request.setAttribute("project", Rows);
			request.setAttribute("pID", projectID);
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