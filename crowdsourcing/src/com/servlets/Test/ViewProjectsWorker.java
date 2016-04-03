package com.servlets.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
 * Servlet implementation class ViewProjectsWorker
 */
@WebServlet("/ViewProjectsWorker")
public class ViewProjectsWorker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProjectsWorker() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		String firstName = request.getParameter("userFirst");
		String name=null;
		String description=null;
		String id=null;
		String projectId=null;
		System.out.println(email);
		try{			
			// Establish Connection
			DBConnection obj = new DBConnection();
			Connection conn = null;
			conn = obj.DBConnect();
			
			PreparedStatement q1 = conn.prepareStatement(" select id from test.workers where email=? ");
			q1.setString(1,email);
			ResultSet wid = q1.executeQuery();
			String id1 = null;
			while(wid.next())
			{
				id1= wid.getString("id");
			}
			System.out.println(id1);
			// SQL Query
			PreparedStatement pst = conn.prepareStatement(" select id,name,description,skill,credibility,status_client,status_worker from test.projects where assigned_worker=? ");
			//pst.setString(1,name);
			//pst.setString(2,description);
			pst.setString(1,id1);
			 ResultSet workerprojects = pst.executeQuery();
			 ArrayList Rows = new ArrayList();
			    String dbId = "null";
				String dbName = "null";
				String dbDescription = "null";
				String dbSkill = "null";
				String dbCredibility = "null";
				String dbStatusClient = "null";
				String dbStatusWorker = "null";
			
				while(workerprojects.next()){
					dbId = workerprojects.getString("id");
					dbName = workerprojects.getString("name");
					dbDescription = workerprojects.getString("description");
					dbSkill = workerprojects.getString("skill");
					dbCredibility = workerprojects.getString("credibility");
					dbStatusClient = workerprojects.getString("status_client");
					dbStatusWorker = workerprojects.getString("status_worker");
					
					ArrayList row = new ArrayList();
				    for (int i = 1; i <= 1 ; i++){
				    	row.add(workerprojects.getString("id"));
				    	row.add(workerprojects.getString("name"));
				    	row.add(workerprojects.getString("description"));
				    	row.add(workerprojects.getString("skill"));
				    	row.add(workerprojects.getString("credibility"));
				    	row.add(workerprojects.getString("status_client"));
				    	row.add(workerprojects.getString("status_worker"));
							}
				    Rows.add(row);
				    System.out.println(dbName); 
				    System.out.println(dbId);
				    String pPid = null;
				    pPid = dbId;
				    System.out.println(pPid);
				    
				    
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
				}
				request.setAttribute("workerprojects", Rows);
				request.setAttribute("pPid", projectId);
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view-projects-worker.jsp");
				requestDispatcher.forward(request,response);	
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
