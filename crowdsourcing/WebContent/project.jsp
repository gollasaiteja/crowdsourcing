<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
			passwordAtt = (String) session.getAttribute("password");
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
		    	if(cookie.getName().equals("password")) userPassword = cookie.getValue();
		    	if(cookie.getName().equals("userFirst")) userFirst = cookie.getValue();
		    	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
			}
		}
		%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
		            <li class="active"><a href="home-client.jsp"><%=(String)session.getAttribute("email") %></a></li>
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
		        <h3><%=(String)session.getAttribute("userFirst") %> !</h3>
		        <p>Your projects:</p>
		        	<% 
	         			ArrayList rows = new ArrayList();
		        	
	         			if (request.getAttribute("project") != null){
	             			rows = (ArrayList) request.getAttribute("project");
	         			}
	         			String updatedClientStatus = null;
	         			if (request.getAttribute("statusClient") != null){
	             			updatedClientStatus = (String) request.getAttribute("statusClient");
	         			}
         			%>
         			<ul>
         		    	<c:forEach items="${project}" var="projectInfo">
	         		    			<b>Project Title:</b> ${projectInfo[0]}</br>
	         		    			<b>Project Description:</b> ${projectInfo[1]} </br>
	         		    			Required Skill: ${projectInfo[2]}</br>
	         		    			Required Availability: ${projectInfo[3]} hours/week</br>
	         		    			Client Location: ${projectInfo[4]}</br>
	         		    			Hourly Rate: CAD ${projectInfo[5]} 
	         		    			
	         		    			<!--  if worker status is not 0, 1, 2, he can initiate contact --> 
	         		    			
	    						<c:if test="${projectInfo[7] == 0}">
	         		    			<form role="form" method="post" action="ViewRecommendation">
			        				<div>
			        				<input type="hidden" name="project_id" value="<%=request.getAttribute("pID")%>">
			        				<input type="hidden" name="user_first" value="<%=userFirst %>">
			        				<input type="hidden" name="email" value="<%=userEmail %>">        					
			        				<input type="submit" value="Get Recommendation &raquo;" class="btn btn-lg btn-primary">
			        				</div>
		        					</form>
		        				</c:if>
    							</br>
    							</br>
    							<c:if test="${ projectInfo[9] != null}">
	         		    			Client Status : 
	         		    			<c:choose>
		         		    			<c:when test="${projectInfo[7] == 0}">Not Assigned</c:when>
		         		    			<c:when test="${projectInfo[7] == 1}">Assigned</c:when>
		         		    			<c:when test="${projectInfo[7] == 2}">Completed</c:when>
	         		    			</c:choose>
	         		    			<form method="post" action="UpdateStatusClient">
	         		    			<input type="hidden" name="project_id" value="<%=request.getAttribute("pID")%>">
	         		    			<input type="hidden" name="firstName" value="<%=userFirst %>">
	         		    			<input type="hidden" name="email" value="<%=userEmail %>">
	         		    			<select name= "clientStatus">
	                                    <option value="0" <c:if test="${projectInfo[7] == 0}">selected</c:if> >Not Assigned</option>
	                                    <option value="1" <c:if test="${projectInfo[7] == 1}">selected</c:if>  >Assigned</option>
	                                    <option value="2" <c:if test="${projectInfo[7] == 2}">selected</c:if>  >Completed</option>
                                    </select>
                                    <input type="submit" value="Update">
                                    
	         		    			</form>
    							</c:if>
	    						</br>
	    					
	         		    	
	         		    	<c:if test="${ projectInfo[7] == '2' && projectInfo[8] == '2' }">
	    						Credibility Score: 
	         		    		<form method="post" action="AssignCredibility">
	         		    		<input type="hidden" name="email" value="<%=(String)session.getAttribute("email") %>">
	         		    		<input type="hidden" name="firstName" value="<%=(String)session.getAttribute("userFirst") %>">
	         		    		<input type="hidden" name="wId" value="${projectInfo[9]}">
	         		    		<input type="hidden" name="project_id" value="<%=request.getAttribute("pID")%>">
	         		    			<select name="Credibility">
	         		    			<option value="0">0</option>
                                    <option value="0.1">0.1</option>
                                    <option value="0.2">0.2</option>
                                    <option value="0.3">0.3</option>
                                    <option value="0.4">0.4</option>
                                    <option value="0.5">0.5</option>
                                    <option value="0.6">0.6</option>
                                    <option value="0.7">0.7</option>
                                    <option value="0.8">0.8</option>
                                    <option value="0.9">0.9</option>
                                    <option value="1.0">1.0</option>
                                    </select>
                                    <input type="submit" value="Update">
	         		    			</form>
	         		    		</br>
	         		    		Make payment:
	         		    		${projectInfo[10]}	
	         		    	</c:if>
         		    	        </c:forEach>
         			            </br>
         			            </br>
		                    	<form role="form" method="post" action="AddProjectEligibility">
		        				<div>
					        		<input type="hidden" name="email" value="<%=(String)session.getAttribute("email") %>">
					        		<input type="hidden" name="userFirst" value="<%=(String)session.getAttribute("userFirst") %>">
					        		<input type="submit" value="Start a new project &raquo;" class="btn btn-lg btn-primary">
					        	</div>
		        			</form>
		                       </ul>
		                       </div>
		                       </div>
	</body>
</html>