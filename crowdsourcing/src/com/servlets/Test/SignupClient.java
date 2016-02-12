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
		String email =request.getParameter("email");
		String passwordConfirmation =request.getParameter("password_confirmation");
		String cardHolderName =request.getParameter("card_holder_name");
		String expiryMonth =request.getParameter("expiry_month");
		String expiryYear =request.getParameter("expiry_year");
		String cardNumber =request.getParameter("card_number");
		String ccv =request.getParameter("ccv");
		
		try{
			// Establish Connection
			DBConnection obj = new DBConnection();
			Connection conn = null;
			conn = obj.DBConnect();
			
			// SQL Query
			PreparedStatement pst = conn.prepareStatement("insert into clients(first_name, last_name, email, password_confirmation, card_holder_name, card_number, expiry_month, expiry_year, ccv, type) values(?,?,?,?,?,?,?,?,?,?) ");
			pst.setString(1,firstName);
			pst.setString(2,lastName);
			pst.setString(3,email);
			pst.setString(4,passwordConfirmation);
			pst.setString(5,cardHolderName);
			pst.setString(6,cardNumber);
			pst.setString(7,expiryMonth);
			pst.setString(8,expiryYear);
			pst.setString(9,ccv);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}