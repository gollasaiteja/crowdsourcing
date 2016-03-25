package com.servlets.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
 * Servlet implementation class AssignCredibility
 */
@WebServlet("/AssignCredibility")
public class AssignCredibility extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssignCredibility() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String firstName = request.getParameter("user_first");
		String workerId = request.getParameter("wId");
		String projectID = request.getParameter("project_id");
		String credibility = request.getParameter("Credibility");
	 
	 try{			
			// Establish Connection
			DBConnection obj = new DBConnection();
			Connection conn = null;
			conn = obj.DBConnect();
			
			//System.out.println(projectID);
			//System.out.println(credibility);
	 
			PreparedStatement pst = conn.prepareStatement("update test.projects set credibility=? where id=?"); 
			pst.setString(1,credibility); 
			pst.setString(2,projectID);
			
			int result = pst.executeUpdate();
			float avgCredibility=0;

			PreparedStatement pst2 = conn.prepareStatement("select avg(credibility) from test.projects where assigned_worker=?");
			pst2.setInt(1, Integer.parseInt(workerId));
			ResultSet countResultSet = pst2.executeQuery();
			while(countResultSet.next()){
				avgCredibility = countResultSet.getFloat(1);
			}
			
			PreparedStatement pst3 = conn.prepareStatement("update test.workers set credibility=? where id=?"); 
			pst3.setString(1,String.valueOf(avgCredibility)); 
			pst3.setInt(2,Integer.parseInt(workerId));
			pst3.executeUpdate();
			
			
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
            request.setAttribute("email", email);
            request.setAttribute("workerId", workerId);
            request.setAttribute("firstName", firstName);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("home-client.jsp");
			requestDispatcher.forward(request, response);
			
	 }
			catch(Exception e){
				System.out.println("Something went wrong. Please contact system admin.");
				System.err.println(e.getMessage());
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
