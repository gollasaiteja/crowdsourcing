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

@WebServlet("/ViewProjectsClient")

public class ViewProjectsClient extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    
    public ViewProjectsClient(){
    	
    }

	//@see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email = request.getParameter("email");
		
		try{			
			// Establish Connection
			DBConnection obj = new DBConnection();
			Connection conn = null;
			conn = obj.DBConnect();
			
			// SQL Query
			PreparedStatement login = conn.prepareStatement(" select id, name, status_client from test.projects where client=? ");
			login.setString(1,email);
			
			ResultSet result = login.executeQuery();
			ArrayList Rows = new ArrayList();
			String dbID = "null";
			String dbName = "null";
			String dbStatusClient = "null";
			while(result.next()){
				dbID = result.getString("id");
				dbName = result.getString("name");
				dbStatusClient = result.getString("status_client");
				System.out.println(dbID + dbName + dbStatusClient);
				ArrayList row = new ArrayList();
			    for (int i = 1; i <= 1 ; i++){
			    	row.add(result.getString("id"));
			    	row.add(result.getString("name"));
			    	row.add(result.getString("status_client"));
			    }
			    Rows.add(row);
			}
			request.setAttribute("projectList", Rows);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view-projects-client.jsp");
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