<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String emailAtt = null; String firstAtt = null; String passwordAtt = null;
	
	if(session.getAttribute("email") == null || session.getAttribute("user") == null){
	   response.sendRedirect("login.jsp");
	}
	else{
		emailAtt = (String) session.getAttribute("email");
		firstAtt = (String) session.getAttribute("user");
	}
		
	String userEmail = null;
	String userPassword = null;
	String userFirst = null;
	String sessionID = null;
	Cookie[] cookies = request.getCookies();
		
	if(cookies != null){
		for(Cookie cookie : cookies){
	    	if(cookie.getName().equals("email")) userEmail = cookie.getValue();
	    	if(cookie.getName().equals("password")) userPassword = cookie.getValue();
	    	if(cookie.getName().equals("user")) userFirst = cookie.getValue();
	    	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
		}
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<title>Project</title>
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
		<meta name="sessionID" content="<%=sessionID %>">
		    <nav class="navbar navbar-default navbar-fixed-top">
		      <div class="container">
		        <div class="navbar-header">
		          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
		            <span class="sr-only">Toggle navigation</span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		          </button>
		          <a class="navbar-brand" href="index.jsp">MTL Works</a>
		        </div>
		        <div id="navbar" class="navbar-collapse collapse">
		          <ul class="nav navbar-nav">
		            <li class="active"><a href="index.jsp">Home</a></li>
		            <li><a href="all-projects.jsp">Projects</a></li>
            		<li><a href="all-clients.jsp">Clients</a></li>
            		<li><a href="all-workers.jsp">Workers</a></li>
            		<li><a href="about-mtlworks.jsp">About</a></li>
		          </ul>
		          <ul class="nav navbar-nav navbar-right">
		            <li class="active"><a href="home-client.jsp"><%=userEmail %></a></li>
		            <li><a href="logout.jsp">Log Out</a></li>
		          </ul>
		        </div>
		      </div>
		    </nav>
		
		    <div class="container">
		      <div class="jumbotron">
		        <h3><%=userFirst %></h3>
		        <p>Your projects:</p>
		        	<% 
	         			ArrayList rows = new ArrayList();
	         			if (request.getAttribute("recommendation") != null){
	             			rows = (ArrayList) request.getAttribute("recommendation");
	         			}
         			%>
         			<ol>
         		    	<c:forEach items="${recommendation}" var="recommendedWorker">
         		    		<li>
	         		    		<span>
	         		    		<c:forEach items="${recommendedWorker[0]}" var="wID">
	         		    			${wID}
	    						</c:forEach>
	    						<c:forEach items="${projectInfo[1]}" var="pDescription">
	         		    			${pDescription}
	    						</c:forEach>
	    						<c:forEach items="${projectInfo[2]}" var="pSkill">
	         		    			${pSkill}
	    						</c:forEach>
	    						<c:forEach items="${projectInfo[3]}" var="pAvailability">
	         		    			${pAvailability}
	    						</c:forEach>
	    						<c:forEach items="${projectInfo[4]}" var="pLocation">
	         		    			${pLocation}
	    						</c:forEach>
	    						<c:forEach items="${projectInfo[5]}" var="pRate">
	         		    			${pRate}
	    						</c:forEach>
	    						<c:forEach items="${projectInfo[6]}" var="pCredibility">
	         		    			${pCredibility}
	    						</c:forEach>
	    						<c:forEach items="${projectInfo[7]}" var="pStatusClient">
	         		    			${pStatusClient}
	    						</c:forEach>
	    						<c:forEach items="${projectInfo[8]}" var="pStatusWorker">
	         		    			${pStatusWorker}
	    						</c:forEach>
	    						<c:forEach items="${projectInfo[9]}" var="pAssignedWorker">
	         		    			${pAssignedWorker}
	    						</c:forEach>
	    						<c:forEach items="${projectInfo[10]}" var="pID">
	         		    			<form role="form" method="post" action="ViewRecommendation">
			        					<div>
			        						<input type="hidden" name="project_id" value="${pID}">
			        						<input type="hidden" name="user_first" value="<%=userFirst %>">
			        						<input type="hidden" name="email" value="<%=userEmail %>">		        					
			        						<input type="submit" value="Get Recommendation &raquo;" class="btn btn-secondary">
			        					</div>
		        					</form>
		        				</c:forEach>
	    						</u>
	    						</span>
    						</li>     		    	
         		    	</c:forEach>
         			</ol>
		        <p><a class="btn btn-lg btn-primary" href="add-project.jsp" role="button">New Project &raquo;</a></p>
		        <p><a class="btn btn-lg btn-primary" href="edit-profile-client.jsp" role="button">Edit Profile &raquo;</a></p>
		      </div>
		  </div>
	</body>
</html>