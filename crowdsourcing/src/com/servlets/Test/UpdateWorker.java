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
		HttpSession session = request.getSession();
		String emailAtt = null; String firstAtt = null; String passwordAtt = null;
		if(session.getAttribute("email") == null || session.getAttribute("userFirst") == null){
		   response.sendRedirect("login.jsp");
		}
		else{
			//emailAtt = (String) session.getAttribute("email");
			//passwordAtt = (String) session.getAttribute("password");
			//firstAtt = (String) session.getAttribute("userFirst");
		
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
			
				
			PreparedStatement pst = conn.prepareStatement("update test.workers(first_name, last_name, skill, location, experience, rate, availability)" + "values(?,?,?,?,?,?,?)");
			pst.setString(1,firstName);
			pst.setString(2,lastName);
			pst.setString(5,skill);
			pst.setString(6,location);
			pst.setString(7,experience);
			pst.setString(8,rate);
			pst.setString(9,availability);
	
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
	            session1.setAttribute("email", emailAtt);
	            session1.setAttribute("user", firstName);
	            session1.setMaxInactiveInterval(30*60); //session expires in 30 minutes
	            
	            Cookie userEmail = new Cookie("email", emailAtt);
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

}