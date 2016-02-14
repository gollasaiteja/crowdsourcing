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

/**
 * Servlet implementation class SignupWorker
**/

@WebServlet("/SignupWorker")
public class SignupWorker extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	// Default constructor
    public SignupWorker(){
        // TODO Auto-generated constructor stub
    }

	//@see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email =request.getParameter("email");
		String password =request.getParameter("password");
		String skill =request.getParameter("skill");
		String location =request.getParameter("location");
		String experience =request.getParameter("experience");
		String rate =request.getParameter("rate");
		String availability =request.getParameter("availabilty");
		String credibility =request.getParameter("credibility");
		int type=0;
		
		try{
			// Establish Connection
			DBConnection obj = new DBConnection();
			Connection conn = null;
			conn = obj.DBConnect();
			
			// SQL Query
			PreparedStatement pst = conn.prepareStatement("insert into workers(first_name, last_name, email, password, skill, location, experience, rate, availability, credibility, type) values(?,?,?,?,?,?,?,?,?,?,?) ");
			pst.setString(1,firstName);
			pst.setString(2,lastName);
			pst.setString(3,email);
			pst.setString(4,password);
			pst.setString(5,skill);
			pst.setString(6,location);
			pst.setString(7,experience);
			pst.setString(8,rate);
			pst.setString(9,availability);
			pst.setString(10,credibility);
			int result = pst.executeUpdate();
			
			if(result==1){
				System.out.println("Data inserted succesfully.");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("home-worker.jsp");
	            requestDispatcher.forward(request, response);
			}
			else{
				System.out.println("Not inserted");
			}
		}
		catch(Exception e){
			System.out.println("Someting went wrong.");
		}
	}

	// @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}