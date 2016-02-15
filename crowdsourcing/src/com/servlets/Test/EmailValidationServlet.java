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

import com.crowdsourcing.DBConnection.DBConnection;

/**
 * Servlet implementation class EmailValidationServlet
 */
@WebServlet(description = "Validates the given email", urlPatterns = { "/EmailValidationServlet" })
public class EmailValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmailValidationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userEmail = request.getParameter("email");
		DBConnection obj = new DBConnection();
		Connection conn = null;
		try{
			conn = obj.DBConnect();
			PreparedStatement pst = conn.prepareStatement("select * from workers where email='"+userEmail+"'");
			ResultSet resultSet = pst.executeQuery();
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			
			if(resultSet.next()){
				out.print("false");
			}
			else{
				out.print("true");
			}
			out.flush();
		}
		catch(SQLException e){
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
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
