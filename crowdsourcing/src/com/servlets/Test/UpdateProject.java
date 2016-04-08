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
 * Servlet implementation class UpdateProject
 */
@WebServlet("/UpdateProject")
public class UpdateProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProject() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String projectId = request.getParameter("project_id");;
		String projectname = request.getParameter("project_name");
		String projectdescription = request.getParameter("project_description"); 
		String projectskill = request.getParameter("project_skill");
		String availability = request.getParameter("project_availability");
		String location = request.getParameter("project_location");
		String rate = request.getParameter("project_rate");
	 
		String clientFirstName = request.getParameter("userFirst");
		String clientEmail = request.getParameter("email");
		System.out.println(clientFirstName+clientEmail+projectskill);
		// Opening connection to DB to fetch user details
					DBConnection obj = new DBConnection();
					// SQL Query
					try {
						Connection conn = obj.DBConnect();
						PreparedStatement pst = conn.prepareStatement("update test.projects set name=?,description=?,skill=?,availability=?,location=?,rate=? where id=?");
						pst.setString(1,projectname);
						pst.setString(2,projectdescription);
						pst.setString(3,projectskill);
						pst.setString(4,availability);
						pst.setString(5,location);
						pst.setString(6,rate);
						pst.setString(7,projectId);
						
						pst.executeUpdate();
						
						// HTTP session
					    HttpSession session = request.getSession();
			            session.setAttribute("email", clientEmail);
			            session.setAttribute("user", clientFirstName);
			            session.setMaxInactiveInterval(30*60);
			            Cookie cookieEmail = new Cookie("email", clientEmail);
			            Cookie cookieUserFirst = new Cookie("user", clientFirstName);
			            cookieEmail.setMaxAge(30*60);
			            cookieUserFirst.setMaxAge(30*60);
			            response.addCookie(cookieEmail);
			            response.addCookie(cookieUserFirst);
						
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("home-client.jsp");
						requestDispatcher.forward(request, response);
						
					}
						catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("UpdateProject.java:67 someting went wrong!");
						System.err.println(e.getMessage());
						e.printStackTrace();
					} 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
