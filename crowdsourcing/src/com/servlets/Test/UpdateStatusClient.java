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
 * Servlet implementation class UpdateStatusClient
 */
@WebServlet("/UpdateStatusClient")
public class UpdateStatusClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStatusClient() {
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
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String statusClient = request.getParameter("clientStatus");
		String firstName =request.getParameter("userFirst");
		String projectId =request.getParameter("projectId");
		
		DBConnection obj = new DBConnection();
		Connection conn = null;
		
		try{
			conn = obj.DBConnect();
			System.out.println(email);
			System.out.println(statusClient);
			
				
			PreparedStatement pst = conn.prepareStatement("update test.projects set status_client=? where client=?"); 
			pst.setString(1,statusClient); 
			pst.setString(2,email);
			
			int result = pst.executeUpdate();
			
			
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
	            request.setAttribute("email", email);
	            request.setAttribute("projectId", projectId);
	            request.setAttribute("statusClient", statusClient);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("project.jsp");
				requestDispatcher.forward(request, response);
			
			
			
			
			
			// request.setAttribute("statusClient", statusClient);
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		System.out.println("UpdateClientStatus.java:62 someting went wrong!");
		System.err.println(e.getMessage());
		e.printStackTrace();
	} 
		finally{
			
		}

}
	}
	
