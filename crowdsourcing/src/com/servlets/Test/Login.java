package com.servlets.Test;
import com.crowdsourcing.DBConnection.*;
import java.sql.*;
import java.sql.Connection;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	// Default constructor
    public Login() {
        // TODO Auto-generated constructor stub
    }

	//@see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String name =request.getParameter("name");
		System.out.println(name);
		
		try{
			
			// Establish Connection
			DBConnection obj = new DBConnection();
			Connection conn = null;
			conn = obj.DBConnect();
			
			// SQL Query
			PreparedStatement pst = conn.prepareStatement("insert into sample(name) values(?) ");
			pst.setString(1,name);
			int result = pst.executeUpdate();
			
			if(result==1){
				System.out.println("Data insertedsuccesfully");
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("sample.jsp");
	            requestDispatcher.forward(request, response);
			}
			else{
				System.out.println("Not inserted");
			}
		}
		catch(Exception e){
			System.out.println("Someting went wrong");
		}

	}

	// @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}