<%@ page language="java" contentType="text/html;"%>

<!DOCTYPE html>
<html>
	<head>
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
		$(document).ready(function () {

		    $('#signupClient').validate({ // initialize the plugin
		        rules: {
		        	first_name: {
		                required: true,
		               		     },
		        	last_name: {
		                required: true,
		               		     },
		             password: {
		  		        required: true,
		  		        minlength: 6,
		  		               	 },
		        	card_number: {
		                required: true,
		                maxlength: 16,
		                regex: "(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\d{3})\d{11})",
		            //http://www.regular-expressions.info/creditcard.html
		         		        },
		        	card_holder_name: {
		                required: true,
		               		     },
		             cvv: {
		  		         required: true,
		  		         maxlength: 3,
		  		               	 }             		     
		        },
		            
		         messages: {
		        	
		        	 card_holder_name: {
			                required: "Please enter your card holder name",
			               		     },
			             cvv: {
			  		         required: "Please enter your cvv number",
			  		         maxlength:"Please enter 3 digit number",
			  		               	 }, 
		        	 card_number: {
		                required: "Please enter your card number",
		                maxlength: "Please enter 16 digit card number",
		                regex: "Invalid credit card number",
		            }
		        }
		    })
		    });
	
		</script>
		<title>MTL Works: New Project</title>
	</head>
	
	<body>
		<div class="container">
		
		<div class="masthead">
        		<h1>MTL Works</h1>
        		<nav>
          			<ul class="nav nav-justified">
            			<li class="active"><a href="#">Home</a></li>
            			<li><a href="all-projects.jsp">Projects</a></li>
            			<li><a href="all-clients.jsp">Clients</a></li>
            			<li><a href="all-workers.jsp">Workers</a></li>
            			<li><a href="about-mtlworks.jsp">About</a></li>
          			</ul>
        		</nav>
      		</div>

      <div class="jumbotron">
      	<h1>MTL Works: New Project</h1>
      </div>
		
		
      <div class="row centered-form">
       	<div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    	<h3 class="panel-title"><small>MTL Works</small></h3>
			 	</div>
			 	<div class="panel-body">
			    	<form role="form" method="post" id="addProject" action="addProject">
			    		<div class="row">
			    			<div class="col-xs-6 col-sm-6 col-md-6">
			    				<div class="form-group">
			                		<input type="text" name="project_name" id="project_name" class="form-control input-sm" placeholder="Project Name">
			    				</div>
			    			</div>
			    			
			    			<div class="col-xs-6 col-sm-6 col-md-6">
			    				<div class="form-group">
			    					<input type="textarea" name="project_description" id="project_description" class="form-control input-sm" placeholder="Project Description">
			    				</div>
			    			</div>
			    		</div>
			    		
			    		<label for="project_skill"> 
		      				<input type="radio" checked="checked" name="project_skill" value="writing" id="project_skill" /> Writing
		      				<input type="radio" name="project_skill" value="design" id="project_skill" /> Design
		      				<input type="radio" name="project_skill" value="writing" id="project_skill" /> Programming
		      				<input type="radio" name="project_skill" value="marketing" id="project_skill" /> Marketing
		      			</label><br />
			    			
						<div class="form-group">
        					<input type="text" class="form-control" name="project_availability" id="project_availability" placeholder="How many hours per week do you need?">
        				</div>
      					
				      	<div class="form-group">
				          	<input type="text" class="form-control" name="project_location" id="project_location" placeholder="Location (optional)">
				      	</div>
				      	
				      	<div class="form-group">
				          	<input type="text" class="form-control" name="project_rate" id="project_rate" placeholder="Hourly rate for the project">
				      	</div>
			    			
			    		<input type="submit" value="Create Project" class="btn btn-info btn-block">
			    		
			    	</form>
			    </div>
	    	</div>
    	</div>
    	</div>
    </div>
</body>
</html>