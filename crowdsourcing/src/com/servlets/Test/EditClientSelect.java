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

/**
 * Servlet implementation class EditClientSelect
 */
@WebServlet("/EditClientSelect")
public class EditClientSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditClientSelect() {
        super();
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
				PreparedStatement clientDetailspreparedStmt = conn.prepareStatement("select * from test.clients where email=?");
				clientDetailspreparedStmt.setString(1,emailAtt);
				ResultSet clientDetailsResultSet = clientDetailspreparedStmt.executeQuery();
				ClientDetails clientDetails = null;
				while(clientDetailsResultSet.next()){
					clientDetails = new ClientDetails();
					clientDetails.setFirstName(clientDetailsResultSet.getString(2));
					clientDetails.setLastName(clientDetailsResultSet.getString(3));
					clientDetails.setEmail(clientDetailsResultSet.getString(4));
					clientDetails.setPassword(clientDetailsResultSet.getString(5));
					clientDetails.setCardHolderName(clientDetailsResultSet.getString(6));
					clientDetails.setCardNumber(clientDetailsResultSet.getString(7));
					clientDetails.setCvv(clientDetailsResultSet.getString(8));
					clientDetails.setExpiryMonth(clientDetailsResultSet.getString(9));
					clientDetails.setExpiryYear(clientDetailsResultSet.getString(10));
				}

				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				JSONObject jsonObject = new JSONObject(clientDetails);
				out.println(jsonObject);
				out.flush();
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
