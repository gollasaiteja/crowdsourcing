<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String emailAtt = null; String firstAtt = null;
	
	if(session.getAttribute("email") == null || session.getAttribute("user") == null){
	   response.sendRedirect("login.jsp");
	}
	else{
		emailAtt = (String) session.getAttribute("email");
		firstAtt = (String) session.getAttribute("user");
	}
		
	String userEmail = null;
	String userFirst = null;
	String sessionID = null;
	Cookie[] cookies = request.getCookies();
		
	if(cookies != null){
		for(Cookie cookie : cookies){
	    	if(cookie.getName().equals("email")) userEmail = cookie.getValue();
	    	if(cookie.getName().equals("user")) userFirst = cookie.getValue();
	    	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
		}
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<title>Projects by <%=(String)session.getAttribute("userFirst") %></title>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<meta name="sessionID" content="<%=sessionID %>">
    	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" />
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script></script>
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
		            <li><a href="home-client.jsp">Home</a></li>
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
		      	<br>
		        <p><%=(String)session.getAttribute("userFirst") %>, the following freelancers are recommended for your project:</p>
		        	<% 
	         			ArrayList rows = new ArrayList();
	         			if (request.getAttribute("recommendation") != null){
	             			rows = (ArrayList) request.getAttribute("recommendation");
	         			}
         			%>
         			<ul>
         		    	<c:forEach items="${recommendation}" var="recommendedWorker">
	    					<li class="projectgrid">
	    						<c:forEach items="${recommendedWorker[1]}" var="wFirstName">
	         		    			<b>Name:</b> ${wFirstName}
	    						</c:forEach>
	    						<c:forEach items="${recommendedWorker[2]}" var="wLastName">
	         		    			${wLastName} </br>
	    						</c:forEach>
	    						<c:forEach items="${recommendedWorker[3]}" var="wEmail">
	         		    			<b>Email:</b> ${wEmail} </br>
	    						</c:forEach>
	    						<c:forEach items="${recommendedWorker[4]}" var="wCredibility">
	         		    			<b>Freelancer Credibility:</b> ${wCredibility} out of 1.0 </br>
	    						</c:forEach>
	    						<c:forEach items="${recommendedWorker[8]}" var="wRate">
	    							<b>Freelancer's Rate:</b> ${wRate} CAD/hour
	    						</c:forEach>
	    						<c:forEach items="${recommendedWorker[9]}" var="pRate">
	    							<b>Your Rate:</b> ${pRate} CAD/hour
	    						</c:forEach><br>
	    						
	    						<c:forEach items="${recommendedWorker[10]}" var="wAvailibility">
	    							<b>Freelancer's Availability:</b> ${wAvailibility} hours/week
	    						</c:forEach>
	    						<c:forEach items="${recommendedWorker[11]}" var="pAvailability">
	    							<b>Projects Estimated Availability:</b> ${pAvailability} hours/week
	    						</c:forEach>
	    						
	    						<c:forEach items="${recommendedWorker[0]}" var="wID">
	    							<!--  Worker ID: ${wID} </br>-->
	    						</c:forEach>
	    						<br><br>
				        					<div>
												<a href="mailto:${recommendedWorker[3]}" class="btn btn-info">Send email &raquo;</a>
				        					</div>
				        					
										<br>	    						
		         		    			<form role="form" method="post" action="AssignWorker">
				        					<div>
				        						<input type="hidden" name="worker_id" value="${recommendedWorker[0]}">
				        						<input type="hidden" name="project_id" value="<%=request.getAttribute("pID")%>">
				        						<input type="hidden" name="user_first" value="<%=(String)session.getAttribute("userFirst") %>">
				        						<input type="hidden" name="email" value="<%=(String)session.getAttribute("email")%>">
				        						<input type="submit" value="Assign Project &raquo;" class="btn btn-success">
				        					</div>
			        					</form>
			        					
			        			</c:forEach>
			        			<br><hr>	
	    					</li>     		    	
         		    	
         		    	
         			</ul>
		      </div>
		  </div>
	</body>
</html>