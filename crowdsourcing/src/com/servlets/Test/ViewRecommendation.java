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

/** Servlet implementation class ViewRecommendation **/

@WebServlet("/ViewRecommendation")
public class ViewRecommendation extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	// Default constructor
    public ViewRecommendation(){
        // TODO Auto-generated constructor stub
    }

	//@see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String projectID = request.getParameter("project_id");
		String clientFirstName = request.getParameter("user_first");
		String clientEmail = request.getParameter("email");
		
		// Check console if everything is retrieved from previous page.
		System.out.println(projectID+clientFirstName+clientEmail);
		
		try{			
			// Establish Connection
			DBConnection obj = new DBConnection();
			Connection conn = null;
			conn = obj.DBConnect();
			
			// SQL Query
			PreparedStatement getProjectSkill = conn.prepareStatement(" select skill from test.projects where id=? ");
			getProjectSkill.setString(1,projectID);
			ResultSet skill = getProjectSkill.executeQuery();
			String dbProjectSkill = "null";
			while(skill.next()){
				dbProjectSkill = skill.getString("skill");
			}
			
			PreparedStatement getProject = conn.prepareStatement(" select id,first_name,last_name,email,location,rate,availability from test.workers where skill=? ");
			getProject.setString(1,dbProjectSkill);
			ResultSet worker = getProject.executeQuery();
			
			ArrayList Rows = new ArrayList();
			String dbID = "null";
			String dbFirstName = "null";
			String dbLastName = "null";
			String dbEmail = "null";
			String dbLocation = "null";
			String dbRate = "null";
			String dbAvailability = "null";
			
			while(worker.next()){
				dbID = worker.getString("id");
				dbFirstName = worker.getString("first_name");
				dbLastName = worker.getString("last_name");
				dbEmail = worker.getString("email");
				dbLocation = worker.getString("location");
				dbRate = worker.getString("rate");
				dbAvailability = worker.getString("availability");
				
				ArrayList row = new ArrayList();
			    for (int i = 1; i <= 1 ; i++){
			    	row.add(worker.getString("id"));
			    	row.add(worker.getString("first_name"));
			    	row.add(worker.getString("last_name"));
			    	row.add(worker.getString("email"));
			    	row.add(worker.getString("location"));
			    	row.add(worker.getString("rate"));
			    	row.add(worker.getString("availability"));
			    	row.add(projectID);
			    }
			    Rows.add(row);
				
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
			}
			request.setAttribute("recommendation", Rows);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/recommendation.jsp");
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