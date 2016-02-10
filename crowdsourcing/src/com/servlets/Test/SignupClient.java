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
 * Servlet implementation class SignupClient
*/

@WebServlet("/SignupClient")
public class SignupClient extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	// Default constructor
    public SignupClient(){
        // TODO Auto-generated constructor stub
    }

	//@see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String firstName = request.getParameter("first-name");
		String lastName = request.getParameter("last-name");
		String email =request.getParameter("email");
		String passwordConfirmation =request.getParameter("password-confirmation");
		String cardHolderName =request.getParameter("card-holder-name");
		String cardNumber =request.getParameter("card-number");
		String ccv =request.getParameter("ccv");
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(email);
		System.out.println(passwordConfirmation);
		System.out.println(cardHolderName);
		System.out.println(cardNumber);
		
		try{
			
			// Establish Connection
			DBConnection obj = new DBConnection();
			Connection conn = null;
			conn = obj.DBConnect();
			
			// SQL Query
			PreparedStatement pst = conn.prepareStatement("insert into sample(name) values(?) ");
			pst.setString(1,firstName);
			int result = pst.executeUpdate();
			
			if(result==1){
				System.out.println("Data inserted succesfully.");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("home-client.jsp");
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}