package com.servlets.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.crowdsourcing.DBConnection.DBConnection;
import com.crowdsourcing.dto.ClientDetails;
import com.crowdsourcing.dto.ProjectDetails;

/**
 * Servlet implementation class EditProject
 */
@WebServlet("/EditProject")
public class EditProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String emailAtt = null; String firstAtt = null; String projectId =null;
		if(session.getAttribute("email") == null || session.getAttribute("userFirst") == null){
		   response.sendRedirect("login.jsp");
		}
		else{
			emailAtt = (String) session.getAttribute("email");
			firstAtt = (String) session.getAttribute("userFirst");
			projectId = request.getParameter("project_id");
			// Opening connection to DB to fetch user details
			DBConnection obj = new DBConnection();
			// SQL Query
			try {
				Connection conn = obj.DBConnect();
				PreparedStatement projectDetailspreparedStmt = conn.prepareStatement("select * from test.projects where id=?");
				projectDetailspreparedStmt.setString(1,projectId);
				ResultSet projectDetailsResultSet = projectDetailspreparedStmt.executeQuery();
				ProjectDetails projectDetails = null;
				while(projectDetailsResultSet.next()){
					projectDetails = new ProjectDetails();
					projectDetails.setProjectName(projectDetailsResultSet.getString(2));
					projectDetails.setProjectDescription(projectDetailsResultSet.getString(3));
					projectDetails.setSkill(projectDetailsResultSet.getString(4));
					projectDetails.setAvailability(projectDetailsResultSet.getString(5));
					projectDetails.setLocation(projectDetailsResultSet.getString(6));
					projectDetails.setRate(projectDetailsResultSet.getString(7));
				

			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			JSONObject jsonObject = new JSONObject(projectDetails);
			out.println(jsonObject);
			out.flush();
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
