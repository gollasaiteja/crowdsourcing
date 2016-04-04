package com.servlets.Test;
import com.crowdsourcing.DBConnection.*;
import java.sql.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddProject
**/

@WebServlet("/AddProject")
public class AddProject extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	// Default constructor
    public AddProject(){
        // TODO Auto-generated constructor stub
    }

	//@see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String pName = request.getParameter("project_name");
		String pDescription = request.getParameter("project_description");
		String pSkill = request.getParameter("project_skill");
		String pAvailability = request.getParameter("project_availability");
		String pRate = request.getParameter("project_rate");
		String pLocation = request.getParameter("project_location");
		System.out.println(pName + pDescription + pSkill + pAvailability + pRate + pLocation);
		
		HttpSession session = request.getSession();
		String emailAtt = null; String firstAtt = null;
		if(session.getAttribute("email") == null || session.getAttribute("userFirst") == null){
		   response.sendRedirect("login.jsp");
		}
		else{
			emailAtt = (String) session.getAttribute("email");
			firstAtt = (String) session.getAttribute("userFirst");
		}
		
		String userEmail = null;
		String userPassword = null;
		String userFirst = null;
		String sessionID = null;
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null){
			for(Cookie cookie : cookies){
		    	if(cookie.getName().equals("email")) userEmail = cookie.getValue();
		    	if(cookie.getName().equals("userFirst")) userFirst = cookie.getValue();
		    	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
			}
		}
		
		DBConnection obj = new DBConnection();
		Connection conn = null;
		
		try{
			conn = obj.DBConnect(); // Establish Connection
			
			// SQL Query
			PreparedStatement insertProject = conn.prepareStatement("insert into test.projects(name, description, skill, availability, location, rate, client)" + "values(?,?,?,?,?,?,?)");
			insertProject.setString(1,pName);
			insertProject.setString(2,pDescription);
			insertProject.setString(3,pSkill);
			insertProject.setString(4,pAvailability);
			insertProject.setString(5,pLocation);
			insertProject.setString(6,pRate);
			insertProject.setString(7,userEmail);
			
			int result1 = insertProject.executeUpdate();
			
			try{
				if(null != insertProject){
					insertProject.close();
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			if(result1 == 1){
				System.out.println("Data inserted succesfully.");
				
				// HTTP session
				HttpSession newsession = request.getSession();
		        newsession.setAttribute("email", userEmail);
		        newsession.setAttribute("user", userFirst);
		        newsession.setMaxInactiveInterval(30*60); //session expires in 30 minutes
		        
		        Cookie userEmailC = new Cookie("email", userEmail);
		        Cookie userFirstC = new Cookie("user", userFirst);
		        userEmailC.setMaxAge(30*60);
		        userFirstC.setMaxAge(30*60);
		        response.addCookie(userEmailC);
		        response.addCookie(userFirstC);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("home-client.jsp");
				requestDispatcher.forward(request, response);
			}
			else{
				System.out.println("Not inserted");
			}
		}
		catch(Exception e){
			System.out.println("Someting went wrong.");
			System.err.println(e.getMessage());
		}
		finally{
			if(null != conn){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	// @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}