package com.servlets.Test;
import com.crowdsourcing.DBConnection.*;
import java.sql.*;
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
		String password = request.getParameter("password");
		String cardHolderName = request.getParameter("card_holder_name");
		String cardNumber = request.getParameter("card_number");
		String expiryMonth = request.getParameter("expiry_month");
		String expiryYear = request.getParameter("expiry_year");
		String cvv = request.getParameter("cvv");
		int type = 1;
		
		// Check console if everything is retrieved from previous page.
		System.out.println(firstName + lastName + email + password + cardHolderName + cardNumber + expiryMonth + expiryYear + cvv + type);
		
		try{			
			// Establish Connection
			DBConnection obj = new DBConnection();
			Connection conn = null;
			conn = obj.DBConnect();
			
			// SQL Query
			PreparedStatement signupClient = conn.prepareStatement("insert into test.clients(first_name, last_name, email, password, card_holder_name, card_number, expiry_month, expiry_year, cvv, type)" + "values(?,?,?,?,?,?,?,?,?,?) ");
			
			signupClient.setString(1,firstName);
			signupClient.setString(2,lastName);
			signupClient.setString(3,email);
			signupClient.setString(4,password);
			signupClient.setString(5,cardHolderName);
			signupClient.setString(6,cardNumber);
			signupClient.setString(7,expiryMonth);
			signupClient.setString(8,expiryYear);
			signupClient.setString(9,cvv);
			signupClient.setInt(10,type);
			int result1 = signupClient.executeUpdate();
			
			PreparedStatement insertUser = conn.prepareStatement("insert into test.users( email, password, type)" + "values(?,?,?)");
			insertUser.setString(1,email);
			insertUser.setString(2,password); 
			insertUser.setInt(3,type);
			int result2 = insertUser.executeUpdate();
			try{
				if(null != insertUser){
					insertUser.close();
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