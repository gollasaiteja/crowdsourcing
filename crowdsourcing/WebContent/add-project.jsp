<%@ page language="java" contentType="text/html;" %>
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
<!DOCTYPE html>
<html>
	<head>
		<title>MTL Works: New Project</title>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
		<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
		<script src="http://jqueryvalidation.org/files/dist/jquery.validate.min.js"></script>
		<script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>
		<script>
			$(document).ready(function(){
				$('#AddProject').validate({
			        rules:{
			        	project_name:{required: true,},
			        	project_skill:{required: true,}             		     
			        },
			            
			        messages:{
			        	project_name:{required: "Please enter project name",},
				        project_skill:{required: "Please select skill",}, 
			        }
			    })
			});
		</script>
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
		<div class="row centered-form">
       	<div class="col-xs-8">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    	<h5 class="panel-title">New project for <%=(String)session.getAttribute("userFirst") %></h5>
			 	</div>
			 	<div class="panel-body">
			    	<form role="form" method="post" id="AddProject" action="AddProject">
			    		<div class="row">
			    			<div class="col-xs-12 col-sm-12 col-md-12">
			    				<div class="form-group">
			                		<input type="text" name="project_name" id="project_name" class="form-control input-sm" placeholder="Project Name">
			    				</div>
			    			</div>
			    			
			    			<div class="col-xs-12 col-sm-12 col-md-12">
			    				<div class="form-group">
			    					<textarea type="textarea" rows="10" name="project_description" id="project_description" class="form-control input-sm" placeholder="Project Description"></textarea>
			    				</div>
			    			</div>
			    		
			    		<div class="col-xs-12 col-sm-12 col-md-12">
			    		<div class="form-group">
			    		<label for="project_skill">Choose a Skill Category:</label> 
		      				<input type="radio" checked="checked" name="project_skill" value="writing" id="project_skill" /> Writing
		      				<input type="radio" name="project_skill" value="design" id="project_skill" /> Design
		      				<input type="radio" name="project_skill" value="programming" id="project_skill" /> Programming
		      				<input type="radio" name="project_skill" value="marketing" id="project_skill" /> Marketing
		      				<input type="radio" name="project_skill" value="translation" id="project_skill" /> Translation
		      				<input type="radio" name="project_skill" value="engineering" id="project_skill" /> Engineering
		      				<input type="radio" name="project_skill" value="admin" id="project_skill" /> Admin Support
		      			</div>
		      			</div>
			    		
			    		<div class="col-xs-12 col-sm-12 col-md-12">	
						<div class="form-group">
        					<input type="text" class="form-control" name="project_availability" id="project_availability" placeholder="How many hours per week do you need?">
        				</div>
        				</div>
      					
      					<div class="col-xs-12 col-sm-12 col-md-12">
				      	<div class="form-group">
				          	<input type="text" class="form-control" name="project_location" id="project_location" placeholder="Location (optional)">
				      	</div>
				      	</div>
				      	
				      	<div class="col-xs-12 col-sm-12 col-md-12">
				      	<div class="form-group">
				          	<input type="text" class="form-control" name="project_rate" id="project_rate" placeholder="Hourly rate for the project">
				      	</div>
				      	</div>
			    		
			    		<div class="col-xs-4 col-sm-6 col-md-6">	
			    		<input type="submit" value="Create Project" class="btn btn-info btn-block">
			    		</div>
			    		</div>
			    	</form>
			    </div>
	    	</div>
    	</div>
    	</div>
    </div>
</body>
</html>