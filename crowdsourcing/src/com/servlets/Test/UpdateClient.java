package com.servlets.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crowdsourcing.DBConnection.DBConnection;

/**
 * Servlet implementation class UpdateClient
 */
@WebServlet("/UpdateClient")
public class UpdateClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String firstName = request.getParameter("first_name");
		String lastName = request.getParameter("last_name");
		String password = request.getParameter("password");
		String cardHolderName = request.getParameter("card_holder_name");
		String cardNumber = request.getParameter("card_number");
		String cvv = request.getParameter("cvv");
		String expiryMonth = request.getParameter("expiry_month");
		String expiryYear = request.getParameter("expiry_year");
		
		
	DBConnection obj = new DBConnection();
	Connection conn = null;
	
	try{
		conn = obj.DBConnect();
		System.out.println("1");
		
			
		PreparedStatement pst = conn.prepareStatement("update test.clients set first_name=?, "
				+ "last_name=?, password=?, card_holder_name=?, card_number=?, cvv=?, expiry_month=?, expiry_year=? where email=?"); 
		pst.setString(1,firstName);
		pst.setString(2,lastName);
		pst.setString(3,password);
		pst.setString(4,cardHolderName);
		pst.setString(5,cardNumber);
		pst.setString(6,cvv);
		pst.setString(7,expiryMonth);
		pst.setString(8,expiryYear);
		pst.setString(9,email);

		int result = pst.executeUpdate();
		
		PreparedStatement pst1 = conn.prepareStatement("update test.users set password=? "
				+ " where email=?");
		pst1.setString(1,password);
		pst1.setString(2,email);

		int result1 = pst1.executeUpdate();
		
		
		try{
			if(null != pst){
				pst.close();
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
			System.out.println("data updated succesfully!");
			// HTTP session
			HttpSession session1 = request.getSession();
            session1.setAttribute("email", email);
            session1.setAttribute("user", firstName);
            session1.setMaxInactiveInterval(30*60); //session expires in 30 minutes
            
            Cookie userEmail = new Cookie("email", email);
            Cookie userFirst = new Cookie("user", firstName);
            userEmail.setMaxAge(30*60);
            userFirst.setMaxAge(30*60);
            response.addCookie(userEmail);
            response.addCookie(userFirst);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("home-client.jsp");
			requestDispatcher.forward(request, response);
		
			
		
		
}
	catch(SQLException e){
		System.out.println("UpdateClient.java:110 someting went wrong!");
		System.err.println(e.getMessage());
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}

	}

}
