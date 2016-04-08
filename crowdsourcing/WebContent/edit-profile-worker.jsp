<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/signup-worker.js"></script>
		<script type="text/javascript" src="js/edit-profile-worker.js"></script>
		
</head>
<body>

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
		
		<div class="container">
		<div class="atmiddle"><button type="button" class="btn btn-lg btn-default navbar-btn">MTL WORKS: <%=(String)session.getAttribute("userFirst") %>'s Profile</button></div>
      <div class="row centered-form">
       	<div class="col-xs-12 col-sm-8 col-md-8 col-sm-offset-2 col-md-offset-2">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    	<h3 class="panel-title"></h3>
			 	</div>
			 	<div class="panel-body">
			    	<form role="form" method="post" action="UpdateWorker">
			    		<div class="row">
			    			<div class="col-xs-6 col-sm-6 col-md-6">
			    				<div class="form-group">
			                		<input type="text" name="first_name" id="first_name" class="form-control input-sm" placeholder="First Name">
			    				</div>
			    			</div>
			    			
			    			<div class="col-xs-6 col-sm-6 col-md-6">
			    				<div class="form-group">
			    					<input type="text" name="last_name" id="last_name" class="form-control input-sm" placeholder="Last Name">
			    				</div>
			    			</div>
			    		</div>

			    	<%-- 	<div class="form-group">
			    			<input type="email" name="email" id="email" class="form-control input-sm" placeholder="Email Address">
			    		</div>

			    		<div class="row">
			    			<div class="col-xs-6 col-sm-6 col-md-6">
			    				<div class="form-group">
			    					<input type="password" name="password" id="password" class="form-control input-sm" placeholder="Password">
			    				</div>
			    			</div>
			    			</div> --%>
			    			    			
			    		<div class="form-group">
			    		<label for="skill">Skill:</label> 
		      				<input type="radio" checked="checked" name="skill" value="writing" id="skill" /> Writing
		      				<input type="radio" name="skill" value="design" id="skill" /> Design
		      				<input type="radio" name="skill" value="programming" id="skill" /> Programming
		      				<input type="radio" name="skill" value="marketing" id="skill" /> Marketing
		      				<input type="radio" name="skill" value="translation" id="skill" /> Translation
					      	<input type="radio" name="skill" value="engineering" id="skill" /> Engineering
					      	<input type="radio" name="skill" value="admin" id="skill" /> Admin Support
		      			</div>
		      		
      
      					<div class="form-group">
        					<input type="text" class="form-control" name="location" id="location" placeholder="location">
        				</div>
                             <!--  <div class="form-group">
        					<input type="text" class="form-control" name="experience" id="experience" placeholder="experience">
        				</div>-->
			    			<div class="form-group">
        					<input type="text" class="form-control" name="rate" id="rate" placeholder="rate">
        				</div>
			    		<div class="form-group">
        					<input type="text" class="form-control" name="availability" id="availability" placeholder="availability">
        				</div>
               			<input type="hidden" name="email" value="<%=(String)session.getAttribute("email") %>"/>	
			    		<input type="submit" value="Update" class="btn btn-info btn-block">
			    		
			    	</form>
			    </div>
	    	</div>
    	</div>
    	</div>
    </div>
		
</body>
</html>