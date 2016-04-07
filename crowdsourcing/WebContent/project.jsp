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
		<title>Project</title>
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
	         		    	<h3><%=(String)session.getAttribute("userFirst") %>, here are the details of ${projectInfo[0]}:</h3><br>
	         		    	<b>Project Title:</b> ${projectInfo[0]}</br>
	         		    	<b>Project Description:</b> ${projectInfo[1]} </br>
	         		    	<b>Required Skill:</b> ${projectInfo[2]}</br>
	         		    	<b>Required Availability:</b> ${projectInfo[3]} hours/week</br>
	         		    	<b>Client Location:</b> ${projectInfo[4]}</br>
	         		    	<b>Hourly Rate:</b> CAD ${projectInfo[5]}</br>
	         		    	<c:if test="${ projectInfo[6] != NULL}">
	         		    		<b>Credibility Score:</b> ${projectInfo[6]} out of 1.0 <br>
	         		    	</c:if>
	         		    			
	         		    	<!--  if worker status is not 0, 1, 2, he can initiate contact --> 
	         		    	<c:if test="${projectInfo[7] == 0}">
	         		    		<form role="form" method="post" action="ViewRecommendation">
			        				<div>
			        				<input type="hidden" name="project_id" value="<%=request.getAttribute("pID")%>">
			        				<input type="hidden" name="user_first" value="<%=(String)session.getAttribute("userFirst") %>">
			        				<input type="hidden" name="email" value="<%=(String)session.getAttribute("email") %>">        					
			        				<input type="submit" value="Get Recommendation &raquo;" class="btn btn-sm btn-info">
			        				</div>
		        				</form>
		        			</c:if>
    						</br>
    						<c:if test="${ projectInfo[9] != null}">
	         		    		<b>Client Status :</b> 
	         		    		<c:choose>
		         		    		<c:when test="${projectInfo[7] == 0}">Not Assigned</c:when>
		         		    		<c:when test="${projectInfo[7] == 1}">Assigned</c:when>
		         		    		<c:when test="${projectInfo[7] == 2}">Completed</c:when>
	         		    		</c:choose>
	         		    		<form method="post" action="UpdateStatusClient">
	         		    			<input type="hidden" name="project_id" value="<%=request.getAttribute("pID")%>">
	         		    			<input type="hidden" name="firstName" value="<%=(String)session.getAttribute("userFirst") %>">
	         		    			<input type="hidden" name="email" value="<%=(String)session.getAttribute("email") %>">
	         		    			<select name="clientStatus" class="form-control">
	                                    <option value="0" <c:if test="${projectInfo[7] == 0}">selected</c:if> >Not Assigned</option>
	                                    <option value="1" <c:if test="${projectInfo[7] == 1}">selected</c:if>  >Assigned</option>
	                                    <option value="2" <c:if test="${projectInfo[7] == 2}">selected</c:if>  >Completed</option>
                                    </select>
                                    <input type="submit" value="Update Status &raquo;" class="btn btn-sm btn-info">
                                </form>
    						</c:if>
	    					</br>
	    					
	         		    	
	         		    	<c:if test="${projectInfo[7] == '2' && projectInfo[8] == '2' && projectInfo[6] == NULL }">
	    						<b>Assign Credibility Score:</b> 
	         		    		<form method="post" action="AssignCredibility">
	         		    		<input type="hidden" name="email" value="<%=(String)session.getAttribute("email") %>">
	         		    		<input type="hidden" name="firstName" value="<%=(String)session.getAttribute("userFirst") %>">
	         		    		<input type="hidden" name="wId" value="${projectInfo[9]}">
	         		    		<input type="hidden" name="project_id" value="<%=request.getAttribute("pID")%>">
	         		    			<select name="Credibility" class="form-control">
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
                                    <input type="submit" value="Assign Credibility &raquo;" class="btn btn-sm btn-info">
	         		    			</form>
	         		    	</c:if>
	         		    	
	         		    	<c:if test="${ projectInfo[7] == '2' && projectInfo[8] == '2' }">
	         		    	
	         		    	
	         		    	<c:if test="${ projectInfo[10] != 'null'}">
	         		    		<p class="bg-warning">The project is completed according to you and the contractor. Please make payment</p>
	         		    		<form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top">
									<input type="hidden" name="cmd" value="_xclick">
									<input type="hidden" name="business" value="${projectInfo[10]}">
									<input type="hidden" name="lc" value="CA">
									<input type="hidden" name="item_name" value="${projectInfo[0]}">
									<input type="hidden" name="currency_code" value="CAD">
									<input type="hidden" name="bn" value="PP-DonationsBF:logo_paypal_212x56.png:NonHosted">
									<input type="image" src="https://www.paypalobjects.com/webstatic/logo/logo_paypal_212x56.png" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
									<img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1">
								</form>
							</c:if>	

							<c:if test="${ projectInfo[10] == 'null'}">
								<div>
									<p class="bg-warning">The assigned freelancer does not have business email with account. Please click the button below to send him an email.</p>
									<a href="mailto:${projectInfo[12]}" class="btn btn-info">Send email &raquo;</a>
				        		</div>
								<!-- <form action="https://www.paypal.com/cgi-bin/webscr" method="post">
								    <input type="hidden" name="cmd" value="_xclick">
								    <input type="hidden" name="business" value="XXX">
								    <input type="hidden" name="lc" value="CA">
								    <input type="hidden" name="item_name" value="Tangled Roots">
								    <input type="hidden" name="button_subtype" value="services">
								    <input type="hidden" name="no_note" value="0">
								    <input type="hidden" name="cn" value="Add special instructions to the seller">
								    <input type="hidden" name="no_shipping" value="2">
								    <input name="amount" value="16.99">
								    <input type="hidden" name="currency_code" value="CAD">
								    <input type="hidden" name="bn" value="PP-BuyNowBF:btn_buynowCC_LG.gif:NonHosted">
								    <input type="image" src="https://www.paypalobjects.com/en_US/i/btn/btn_buynowCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
								    <img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1">
								</form>-->
	         		    	</c:if>
	         		    		
	         		    	</c:if>
         				</c:forEach>
         			    
         			    </br></br><hr>
		                
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