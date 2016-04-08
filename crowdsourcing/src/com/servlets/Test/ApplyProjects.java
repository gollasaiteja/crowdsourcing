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

@WebServlet("/ApplyProjects")

public class ApplyProjects extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    
    public ApplyProjects(){
    	
    }

	//@see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email = request.getParameter("email");
		String userFirst = request.getParameter("email");
		try{			
			// Establish Connection
			DBConnection obj = new DBConnection();
			Connection conn = null;
			conn = obj.DBConnect();
			
			// SQL Query
			PreparedStatement login = conn.prepareStatement(" select id, name, description, skill, client from test.projects where status_client=0 ");
			
			ResultSet result = login.executeQuery();
			ArrayList Rows = new ArrayList();
			String dbID = "null";
			String dbName = "null";
			String dbDescription = "null";
			String dbClient = "null";
			String dbSkill = "null";
			
			while(result.next()){
				dbID = result.getString("id");
				dbName = result.getString("name");
				dbDescription = result.getString("description");
				dbClient = result.getString("client");
				dbClient = result.getString("skill");
				System.out.println(dbID + dbName + dbDescription);
				
				ArrayList row = new ArrayList();
			    for (int i = 1; i <= 1 ; i++){
			    	row.add(result.getString("id"));
			    	row.add(result.getString("name"));
			    	row.add(result.getString("description"));
			    	row.add(result.getString("client"));
			    	row.add(result.getString("skill"));
			    	row.add(email);
			    }
			    Rows.add(row);
			}
			request.setAttribute("projectList", Rows);
			
			// HTTP session
			HttpSession session = request.getSession();
            session.setAttribute("email", email);
            session.setAttribute("user", userFirst);
            session.setMaxInactiveInterval(30*60); //session expires in 30 minutes
            
            Cookie userEmail = new Cookie("email", email);
            Cookie user = new Cookie("user", userFirst);
            userEmail.setMaxAge(30*60);
            user.setMaxAge(30*60);
            response.addCookie(userEmail);
            response.addCookie(user);
			
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/apply-projects.jsp");
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