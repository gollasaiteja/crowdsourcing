<%@ page language="java" contentType="text/html;"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/signup-worker.js"></script>
		<script>
		$(document).ready(function () {

		    $('#signup-worker').validate({ // initialize the plugin
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
		  		               location: {
		             required: true,
		               		     },
		           	experience: {
		  		         required: true,
		  		                }, 
		            rate: {
		 		  		         required: true,
		  		                },
		            availabilty: {
			 		  		         required: true,
			  		                },
		        },
		            
		         messages: {
		        	
		        	 
		            
		        }
		    })
		    });
		</script>
		<title>MTL Works: New Worker</title>
	</head>
	
	<body>
		<div class="container">

      <!-- Jumbotron -->
      <div class="jumbotron">
      	<h1>MTL Works: New Worker</h1>
      </div>
		
		
      <div class="row centered-form">
       	<div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    	<h3 class="panel-title">Worker Registration Form <small>MTL Works</small></h3>
			 	</div>
			 	<div class="panel-body">
			    	<form role="form" method="post" action="SignupWorker">
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

			    		<div class="form-group">
			    			<input type="email" name="email" id="email" class="form-control input-sm" placeholder="Email Address">
			    		</div>

			    		<div class="row">
			    			<div class="col-xs-6 col-sm-6 col-md-6">
			    				<div class="form-group">
			    					<input type="password" name="password" id="password" class="form-control input-sm" placeholder="Password">
			    				</div>
			    			</div>
			    			</div>
			    			    			
			    		<div class="form-group">
			    		<label for="skill">Choose a Skill Category:</label> 
		      				<input type="radio" checked="checked" name="skill" value="writing" id="skill" /> Writing
		      				<input type="radio" name="skill" value="design" id="skill" /> Design
		      				<input type="radio" name="skill" value="writing" id="skill" /> Programming
		      				<input type="radio" name="skill" value="marketing" id="skill" /> Marketing
		      			</div>
		      		
      
      					<div class="form-group">
        					<input type="text" class="form-control" name="location" id="location" placeholder="location">
        				</div>
                              <div class="form-group">
        					<input type="text" class="form-control" name="experience" id="experience" placeholder="experience">
        				</div>
			    			<div class="form-group">
        					<input type="text" class="form-control" name="rate" id="rate" placeholder="rate">
        				</div>
			    		<div class="form-group">
        					<input type="text" class="form-control" name="availabilty" id="availablity" placeholder="availabilty">
        				</div>
               				
			    		<input type="submit" value="Register" class="btn btn-info btn-block">
			    		
			    	</form>
			    </div>
	    	</div>
    	</div>
    	</div>
    </div>
</body>
</html>