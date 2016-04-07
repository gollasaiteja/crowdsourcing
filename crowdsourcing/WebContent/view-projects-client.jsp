<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import ="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String emailAtt = null; String firstAtt = null;
	if(session.getAttribute("email") == null || session.getAttribute("userFirst") == null){
		response.sendRedirect("login.jsp");
	}
	else{
		emailAtt = (String) session.getAttribute("email");
		firstAtt = (String) session.getAttribute("userFirst");
	}
		
	String userEmail = null;
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
		<title>Client Projects</title>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<meta name="sessionID" content="<%=sessionID %>">
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
		          <a href="index.jsp"><button type="button" class="btn btn-default navbar-btn">MTL WORKS</button></a>
		        </div>
		        <div id="navbar" class="navbar-collapse collapse">
		          <ul class="nav navbar-nav">
		            <li><a href="index.jsp">Home</a></li>
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
		      	</br>
		        <h3><%=(String)session.getAttribute("userFirst") %>,here are your projects:</h3><br><hr>
		        	<% 
         			ArrayList rows = new ArrayList();
         			if (request.getAttribute("projectList") != null){
             			rows = (ArrayList) request.getAttribute("projectList");
         			}
         			%>
         			<ol>
         		    	<c:forEach items="${projectList}" var="project">
         		    		<li class="projectgrid">
	         		    		<span>
	         		    		<c:forEach var="title" items="${project[1]}">
	         		    			<p>${title}</p>
	    						</c:forEach>
	    						<c:forEach var="link" items="${project[0]}">
	         		    		<div class="row">
	         		    			<div class="col-xs-1 col-sm-1 col-md-1">	
	         		    			<form role="form" method="post" action="ViewProject">
			        					<div>
			        						<input type="hidden" name="project_id" value="${link}">			        						
			        						<input type="hidden" name="user_first" value="<%=(String)session.getAttribute("userFirst") %>">
			        						<input type="hidden" name="email" value="<%=(String)session.getAttribute("email")%>">		        					
			        						<input type="submit" value="Open &raquo;" class="btn btn-info">
			        					</div>
		        					</form>
		        					</div>
		        					
		        					<div class="col-xs-1 col-sm-1 col-md-1">
		        					<form role="form" method="post" action="edit-project-clients.jsp">
			        					<div>
			        						<input type="hidden" name="project_id" value="${link}">			        						
			        						<input type="hidden" name="user_first" value="<%=(String)session.getAttribute("userFirst") %>">
			        						<input type="hidden" name="email" value="<%=(String)session.getAttribute("email")%>">		        					
			        						<input type="submit" value="Edit &raquo;" class="btn btn-info">
			        					</div>
		        					</form>
		        					</div>
		        					
		        					<div class="col-xs-1 col-sm-1 col-md-1">
		        					<form role="form" method="post" action="DeleteProject">
			        					<div>
			        						<input type="hidden" name="project_id" value="${link}">			        						
			        						<input type="hidden" name="user_first" value="<%=(String)session.getAttribute("userFirst") %>">
			        						<input type="hidden" name="email" value="<%=(String)session.getAttribute("email")%>">		        					
			        						<input type="submit" value="Delete &raquo;" class="btn btn-danger">
			        					</div>
		        					</form>
		        					</div>
		        				</div>			
		        				</c:forEach>
	    						</span>
    						</li>     		    	
         		    	</c:forEach>
         			</ol>
         			</br>
         			<hr>
		        <p>
		        	<form role="form" method="post" action="AddProjectEligibility">
		        		<div>
		        			<input type="hidden" name="email" value="<%=(String)session.getAttribute("email") %>">
		        			<input type="hidden" name="userFirst" value="<%=(String)session.getAttribute("userFirst") %>">
		        			<input type="submit" value="Create New Project &raquo;" class="btn btn-lg btn-primary">
		        		</div>
		        	</form>
		        </p>
		        
		      </div>
		  </div>
	</body>
</html>