<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
		String emailAtt = null; String firstAtt = null; String passwordAtt = null;
		if(session.getAttribute("email") == null || session.getAttribute("userFirst") == null){
		   response.sendRedirect("login.jsp");
		}
		else{
			emailAtt = (String) session.getAttribute("email");
			firstAtt = (String) session.getAttribute("userFirst");
		}
		
		String userEmail = null;
		String userPassword = null;
		String userFirst = null;
		String sessionID = null;
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null){
			for(Cookie cookie : cookies){
		    	if(cookie.getName().equals("email")) userEmail = cookie.getValue();
		    	if(cookie.getName().equals("userFirst")) userFirst = cookie.getValue();
		    	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
			}
		}
		%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title><%=(String)session.getAttribute("userFirst") %>'s Projects</title>
		<meta name="sessionID" content="<%=sessionID %>">
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" />
	</head>
<body>

	<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
		        <div class="navbar-header">
		          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
		            <span class="sr-only">Toggle navigation</span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		          </button>
		          <button type="button" class="btn btn-default navbar-btn">MTL WORKS</button>
		        </div>
		        <div id="navbar" class="navbar-collapse collapse">
		          <ul class="nav navbar-nav">
		            <li><a href="home-worker.jsp">Home</a></li>
		            <li><a href="all-projects.jsp">Projects</a></li>
            		<li><a href="all-clients.jsp">Clients</a></li>
            		<li><a href="all-workers.jsp">Workers</a></li>
		          </ul>
		          <ul class="nav navbar-nav navbar-right">
		            <li><a href="home-client.jsp"><%=(String)session.getAttribute("email")%></a></li>
		            <li>
		            	<p class="navbar-btn">
		            	<form method="post" action="Logout">
				        	<input type="submit" value="Logout" class="btn btn-default">
				        </form>
				        </p>
		            </li>
		          </ul>
		        </div>
		      </div>
		    </nav>
		     <div class="container">
		      <div class="jumbotron">
		        <h3><%=(String)session.getAttribute("userFirst") %>, these projects are currently assigned to you:</h3>
		        <br>
		        	<% 
	         			ArrayList rows = new ArrayList();
		        	
	         			if (request.getAttribute("workerprojects") != null){
	             			rows = (ArrayList) request.getAttribute("workerprojects");
	         			}
	         			%>
	         			<ol>
	         			<c:forEach items="${workerprojects}" var="projectInfo">
         		    		
         		    		<li class="projectgrid">
	         		    		<c:forEach items="${projectInfo[1]}" var="pTitle">
	         		    			<b>Project Title:</b> ${pTitle}
	    						</c:forEach> </br>	
	    						<c:forEach items="${projectInfo[2]}" var="pDescription">
	         		    			<b>Project Description:</b> ${pDescription} 
	         		    		</c:forEach> </br>
	    						<c:forEach items="${projectInfo[3]}" var="pSkill">
	         		    			<b>Project Skill:</b> ${pSkill} 
	         		    		</c:forEach> </br>
	           		    		
	         		    		<c:forEach items="${projectInfo[4]}" var="Credibility">
	         		    			<b>Project Credibility:</b> ${Credibility} 
	         		    		</c:forEach> </br>
	         		    		<c:forEach items="${projectInfo[5]}" var="StatusClient">
	         		    			<b>Client Status:</b>
	         		    			<c:choose>
		         		    			<c:when test="${projectInfo[5] == 0}">Not Assigned</c:when>
		         		    			<c:when test="${projectInfo[5] == 1}">Assigned</c:when>
		         		    			<c:when test="${projectInfo[5] == 2}">Completed</c:when>
	         		    			</c:choose> 
	         		    		</c:forEach> </br>
	         		    		
	         		    		
	         		    		<c:forEach items="${projectInfo[6]}" var="pStatusWorker">
		         		    		<b>Your Status:</b> 
		         		    		<c:choose>
		         		    			<c:when test="${projectInfo[6] == 0}">Started</c:when>
		         		    			<c:when test="${projectInfo[6] == 1}">Ongoing</c:when>
		         		    			<c:when test="${projectInfo[6] == 2}">Completed</c:when>
	         		    			</c:choose>
		         		    	</c:forEach> </br></br>	
	         		    		
	         		    		<c:forEach items="${projectInfo[0]}" var="pId">
	         		    			<!--  Project ID: ${pId}-->
	    					  		<form method="post" action="UpdateStatusWorker">
		         		    		<input type="hidden" name="email" value="<%=(String)session.getAttribute("email") %>">
		         		    		<input type="hidden" name="firstName" value="<%=(String)session.getAttribute("userFirst") %>">
		         		    		<input type="hidden" name="projectId" value="${pId}">
		         		    			<select name="workerStatus" class="form-control">
		         		    			
	                                    <option value="0">Started</option>
	                                    <option value="1">Ongoing</option>
	                                    <option value="2">Completed</option>
	                                    </select>
	                                    <input type="submit" value="Update Status &raquo;" class="btn btn-sm btn-info">
		         		    			</form>
		    						</c:forEach>
		    					</li>
		    					<br>		     		    	
	         		    	</c:forEach></ol>
			      		</div>
			  </div>
	         		    			
</body>
</html>