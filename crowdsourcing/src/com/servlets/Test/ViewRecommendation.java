package com.servlets.Test;
import com.crowdsourcing.DBConnection.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

/** Servlet implementation class ViewRecommendation **/

@WebServlet("/ViewRecommendation")
public class ViewRecommendation extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    
    public ViewRecommendation(){
        // TODO Auto-generated constructor stub
    }

	//@see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String projectID = request.getParameter("project_id");
		String clientFirstName = request.getParameter("user_first");
		String clientEmail = request.getParameter("email");
		ArrayList credRows = new ArrayList();
		// Check console if everything is retrieved from previous page
		System.out.println(projectID+clientFirstName+clientEmail);
		
		try{
			
			DBConnection obj = new DBConnection();
			Connection conn = null;
			conn = obj.DBConnect();
			
			PreparedStatement getProjectSkill = conn.prepareStatement(" select skill,rate,availability from test.projects where id=? ");
			getProjectSkill.setString(1,projectID);
			ResultSet skill = getProjectSkill.executeQuery();
			
			String dbProjectSkill = "null";
			String dbProjectRate = "null";
			String dbProjectAvailability = "null";
			
			if(skill.next()){
				dbProjectSkill = skill.getString("skill");
				dbProjectRate = skill.getString("rate");
				dbProjectAvailability = skill.getString("availability");
			}
			
			PreparedStatement getSkilledWorker = conn.prepareStatement(" select id, credibility from test.workers where skill=? order by credibility DESC");
			getSkilledWorker.setString(1,dbProjectSkill);
			ResultSet skilledWorker = getSkilledWorker.executeQuery();
			
			ArrayList Rows1 = new ArrayList();
			String dbID1 = "null";
			
			while(skilledWorker.next()){
				dbID1 = skilledWorker.getString("id");
				ArrayList row1 = new ArrayList();
			    
				for (int i = 1; i <= 1 ; i++){
			    	row1.add(dbID1);
			    }
				
				Rows1.add(row1);
				System.out.println(row1);
				
				for (int i = 0; i < row1.size(); i++){
					System.out.println(row1.get(i));
			    	System.out.println("yes");
			    	String workerID = (String) row1.get(i);
			    	request.setAttribute("wID",workerID);
			    	PreparedStatement getcredScores = conn.prepareStatement(" SELECT first_name, last_name, email, rate, availability, credibility, paypal from test.workers WHERE id=?");
					getcredScores.setString(1,workerID);
					ResultSet credScores = getcredScores.executeQuery();
					String rwFirstName = "null";
					String rwLastName = "null";
					String rwEmail = "null";
					String rwRate = "null";
					String rwAvailability = "null";
					String rwCredScore = "null";
					String rwPaypal = "null";
				    
					while(credScores.next()){
						rwFirstName = credScores.getString("first_name");
						rwLastName = credScores.getString("last_name");
						rwEmail = credScores.getString("email");
						rwRate = credScores.getString("rate");
						rwAvailability = credScores.getString("availability");
						rwCredScore = credScores.getString("credibility");
						rwPaypal = credScores.getString("paypal");
						ArrayList row2 = new ArrayList();
					    for (int j = 1; j <= 1 ; j++){
					    	row2.add(workerID);
					    	row2.add(rwFirstName);
					    	row2.add(rwLastName);
					    	row2.add(rwEmail);
					    	row2.add(rwCredScore);
					    	row2.add(rwPaypal);
					    	row2.add(projectID);
					    	row2.add(dbProjectSkill);
					    	row2.add(rwRate);
					    	row2.add(dbProjectRate);
					    	row2.add(rwAvailability);
					    	row2.add(dbProjectAvailability);
					    }
					    credRows.add(row2);
					}
			    }
			    System.out.println(credRows);
			    request.setAttribute("recommendation", credRows);
			}
			request.setAttribute("pID", projectID);
			
			// HTTP session
		    HttpSession session = request.getSession();
            session.setAttribute("email", clientEmail);
            session.setAttribute("user", clientFirstName);
            session.setMaxInactiveInterval(30*60); //session expires in 30 minutes
            
            Cookie cookieEmail = new Cookie("email", clientEmail);
            Cookie cookieUserFirst = new Cookie("user", clientFirstName);
            cookieEmail.setMaxAge(30*60);
            cookieUserFirst.setMaxAge(30*60);
            response.addCookie(cookieEmail);
            response.addCookie(cookieUserFirst);
			
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/recommendation.jsp");
            requestDispatcher.forward(request,response);
		}
		
		catch(Exception e){
			System.out.println("Something went wrong. Please contact system admin.");
			System.err.println(e.getMessage());
		}
	}

	// @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
}