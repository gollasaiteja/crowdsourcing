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
		System.out.println(firstName + lastName + email + passwordConfirmation + cardHolderName + cardNumber + expiryMonth + expiryYear + cvv + type);
		
		try{
			// Establish Connection
			DBConnection obj = new DBConnection();
			Connection conn = null;
			conn = obj.DBConnect();
			
			System.out.println("1");
			
			// SQL Query
			PreparedStatement pst = conn.prepareStatement("insert into test.clients(first_name, last_name, email, password, card_holder_name, card_number, expiry_month, expiry_year, cvv, type)" + "values(?,?,?,?,?,?,?,?,?,?) ");
			
			System.out.println("2");
			
			pst.setString(1,firstName);
			pst.setString(2,lastName);
			pst.setString(3,email);
			pst.setString(4,passwordConfirmation);
			pst.setString(5,cardHolderName);
			pst.setString(6,cardNumber);
			pst.setString(7,expiryMonth);
			pst.setString(8,expiryYear);
			pst.setString(9,cvv);
			pst.setInt(10,type);
			int result = pst.executeUpdate();
			
			if(result==1){
				System.out.println("Data inserted succesfully.");
	            HttpSession session = request.getSession();
	            session.setAttribute("email", email);
	            //setting session to expiry in 30 mins
	            session.setMaxInactiveInterval(30*60);
	            Cookie user = new Cookie("email", email);
	            user.setMaxAge(30*60);
	            response.addCookie(user);
	            // response.sendRedirect("LoginSuccess.jsp");
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
	}

	// @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}