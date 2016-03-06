package com.servlets.Test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.crowdsourcing.DBConnection.*;
import com.crowdsourcing.dto.WorkerDetails;

import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class EditWorkerSelect
 */
@WebServlet("/EditWorkerSelect")
public class EditWorkerSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditWorkerSelect() {
       
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String emailAtt = null; String firstAtt = null; String passwordAtt = null;
		if(session.getAttribute("email") == null || session.getAttribute("userFirst") == null){
		   response.sendRedirect("login.jsp");
		}
		else{
			emailAtt = (String) session.getAttribute("email");
			passwordAtt = (String) session.getAttribute("password");
			firstAtt = (String) session.getAttribute("userFirst");
			// Opening connection to DB to fetch user details
			DBConnection obj = new DBConnection();
			// SQL Query
			try {
				Connection conn = obj.DBConnect();
				PreparedStatement workerDetailspreparedStmt = conn.prepareStatement("select * from test.workers where email=?");
				workerDetailspreparedStmt.setString(1,emailAtt);
				ResultSet workerDetailsResultSet = workerDetailspreparedStmt.executeQuery();
				WorkerDetails workerDetails = null;
				while(workerDetailsResultSet.next()){
					workerDetails = new WorkerDetails();
					workerDetails.setFirstName(workerDetailsResultSet.getString(2));
					workerDetails.setLastName(workerDetailsResultSet.getString(3));
					workerDetails.setEmail(workerDetailsResultSet.getString(4));
					workerDetails.setSkill(workerDetailsResultSet.getString(6));
					workerDetails.setLocation(workerDetailsResultSet.getString(7));
					workerDetails.setExperience(workerDetailsResultSet.getString(9));
					workerDetails.setRate(workerDetailsResultSet.getString(10));
					workerDetails.setAvailability(workerDetailsResultSet.getString(11));
				}
				response.setContentType("application/json");
				PrintWriter out = response.getWriter();
				out.println(workerDetails);
				out.flush();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			String dbEmail = "null";
			String dbPassword = "null";
			String dbType = "null";
		}
		
		/*String userEmail = null;
		String userPassword = null;
		String userFirst = null;
		String sessionID = null;
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null){
			for(Cookie cookie : cookies){
		    	if(cookie.getName().equals("email")) userEmail = cookie.getValue();
		    	if(cookie.getName().equals("password")) userPassword = cookie.getValue();
		    	if(cookie.getName().equals("userFirst")) userFirst = cookie.getValue();
		    	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
			}
		}
	System.out.println(userEmail+ userPassword+ userFirst+ sessionID );*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
