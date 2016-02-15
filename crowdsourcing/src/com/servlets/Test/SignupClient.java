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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SignupClient
**/

@WebServlet("/SignupClient")
public class SignupClient extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	// Default constructor
    public SignupClient(){
        // TODO Auto-generated constructor stub
    }

	//@see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String email = request.getParameter("email");
		String passwordConfirmation = request.getParameter("password_confirmation");
		String cardHolderName = request.getParameter("card_holder_name");
		String cardNumber = request.getParameter("card_number");
		String expiryMonth = request.getParameter("expiry_month");
		String expiryYear = request.getParameter("expiry_year");
		String cvv = request.getParameter("cvv");
		int type = 1;
		
		// Check console if everything is retrieved from previous page.
		System.out.println(firstName + lastName + email + passwordConfirmation + cardHolderName + cardNumber + expiryMonth + expiryYear + cvv + type);
		
		try{			
			// Establish Connection
			DBConnection obj = new DBConnection();
			Connection conn = null;
			conn = obj.DBConnect();
			
			// SQL Query
			PreparedStatement sognupClient = conn.prepareStatement("insert into test.clients(first_name, last_name, email, password, card_holder_name, card_number, expiry_month, expiry_year, cvv, type)" + "values(?,?,?,?,?,?,?,?,?,?) ");
			
			sognupClient.setString(1,firstName);
			sognupClient.setString(2,lastName);
			sognupClient.setString(3,email);
			sognupClient.setString(4,passwordConfirmation);
			sognupClient.setString(5,cardHolderName);
			sognupClient.setString(6,cardNumber);
			sognupClient.setString(7,expiryMonth);
			sognupClient.setString(8,expiryYear);
			sognupClient.setString(9,cvv);
			sognupClient.setInt(10,type);
			int result = sognupClient.executeUpdate();
			
			if(result==1){
				System.out.println("Data inserted succesfully.");
	            
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
	            
	            RequestDispatcher requestDispatcher = request.getRequestDispatcher("home-client.jsp");
	            requestDispatcher.forward(request, response);
			}
			else{
				System.out.println("Database operation unsuccessful.");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("registration-error.jsp");
	            requestDispatcher.forward(request, response);
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