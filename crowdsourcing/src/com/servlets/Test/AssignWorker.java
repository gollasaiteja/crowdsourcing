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

@WebServlet("/AssignWorker")
public class AssignWorker extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    public AssignWorker() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String projectID = request.getParameter("project_id");
		String workerID = request.getParameter("worker_id");
		String clientFirst = request.getParameter("user_first");
		String email = request.getParameter("email");
		
		
		DBConnection obj = new DBConnection();
		Connection conn = null;
	
		try{
			conn = obj.DBConnect();

			PreparedStatement pst = conn.prepareStatement("update test.projects set assigned_worker=?,status_client=1 where id=?");
			pst.setString(1,workerID);
			pst.setString(2,projectID);
			System.out.println(workerID);
			
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
				System.out.println("data updated succesfully!");
				
				HttpSession session1 = request.getSession();
				session1.setAttribute("email", email);
				session1.setAttribute("user", clientFirst);
				session1.setMaxInactiveInterval(30*60); //session expires in 30 minutes
            
	            Cookie userEmail = new Cookie("email", email);
	            Cookie userFirst = new Cookie("user", clientFirst);
	            userEmail.setMaxAge(30*60);
	            userFirst.setMaxAge(30*60);
	            response.addCookie(userEmail);
	            response.addCookie(userFirst);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("home-client.jsp");
				requestDispatcher.forward(request, response);
			}
			else{
				System.out.println("Not inserted");
			}
		
		}
		
		catch(SQLException e){
			System.out.println("Someting went wrong!");
			System.err.println(e.getMessage());
		}
		catch (ClassNotFoundException e1){
			e1.printStackTrace();
		}
	}
}