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
 * Servlet implementation class UpdateWorker
 */
@WebServlet(description = "Updates worker table", urlPatterns = { "/UpdateWorker" })
public class UpdateWorker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateWorker() {
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
			String skill = request.getParameter("skill");
			String location = request.getParameter("location");
			String experience = request.getParameter("experience");
			String rate = request.getParameter("rate");
			String availability = request.getParameter("availability");
			System.out.println(firstName + lastName  + skill + location + experience + rate + availability );
		DBConnection obj = new DBConnection();
		Connection conn = null;
		
		try{
			conn = obj.DBConnect();
			System.out.println("1");
			
				
			PreparedStatement pst = conn.prepareStatement("update test.workers set first_name=?, "
					+ "last_name=?, skill=?, location=?, experience=?, rate=?, availability=? where email=?"); 
			pst.setString(1,firstName);
			pst.setString(2,lastName);
			pst.setString(3,skill);
			pst.setString(4,location);
			pst.setString(5,experience);
			pst.setString(6,rate);
			pst.setString(7,availability);
			pst.setString(8,email);
	
			int result = pst.executeUpdate();
			
			
			try{
				if(null != pst){
					pst.close();
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			if(result == 1){
				System.out.println("Data inserted succesfully.");
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
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("home-worker.jsp");
				requestDispatcher.forward(request, response);
			}
			else{
				System.out.println("Not inserted");
			}
			
	}
		catch(SQLException e){
			System.out.println("Someting went wrong.");
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

}
	}

