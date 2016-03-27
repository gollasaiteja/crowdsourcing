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
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String skill = request.getParameter("skill");
		String location = request.getParameter("location");
		String paypal = request.getParameter("paypal");
		String rate = request.getParameter("rate");
		String availability = request.getParameter("availability");
		int type = 0;
		System.out.println(firstName + lastName + email + password + skill + location + paypal + rate + availability + type);
		DBConnection obj = new DBConnection();
		Connection conn = null;
		try{
			conn = obj.DBConnect();
			
			PreparedStatement pst = conn.prepareStatement("insert into test.workers(first_name, last_name, email, password, skill, location, rate, availability, paypal, type)" + "values(?,?,?,?,?,?,?,?,?,?)");
			pst.setString(1,firstName);
			pst.setString(2,lastName);
			pst.setString(3,email);
			pst.setString(4,password);
			pst.setString(5,skill);
			pst.setString(6,location);
			pst.setString(7,rate);
			pst.setString(8,availability);
			pst.setString(9,paypal);
			pst.setInt(10,type);
			int result1 = pst.executeUpdate();
			
			
			try{
				if(null != pst){
					pst.close();
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			
			PreparedStatement pst1 = conn.prepareStatement("insert into test.users( email, password, type)" + "values(?,?,?)");
			pst1.setString(1,email);
			pst1.setString(2,password); 
			pst1.setInt(3,type);
			int result2 = pst1.executeUpdate();
			try{
				if(null != pst){
					pst1.close();
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			if(result1 == 1 && result2 == 1){
				System.out.println("Data inserted succesfully.");
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
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("home-worker.jsp");
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