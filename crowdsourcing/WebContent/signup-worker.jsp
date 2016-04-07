<%@ page language="java" contentType="text/html;"%>

<!DOCTYPE html>
<html>
	<head>
		<title>MTL Works: New Freelancer Registration</title>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/signup-worker.js"></script>
		<script src="http://jqueryvalidation.org/files/dist/jquery.validate.min.js"></script>
		<script src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script>
		<script>
			$(document).ready(function(){
				$('#signup-worker').validate({
			    	rules:{
			        	first_name:{required: true,},
			        	last_name:{required: true,},
			            password:{required: true,minlength: 6,},
			  		    location:{required: true,}, 
			            rate:{required: true,},
			            availabilty:{required: true,},
				  		paypal:{required: true,},
			        },
			        messages:{
			        	first_name:{required: "Enter first name.",},
			        	last_name:{required: "Enter last name.",},
			            password:{required: "Password is required.",minlength: "Password should be at least 6 characters",},
			  		    location:{required: "Location is requried",}, 
			            rate:{required: "Enter hourly rate",},
			            availabilty:{required: "Enter availability",},
				  		paypal:{required: "This is needed to process your payments.",},
			    	}
				})
			});
		</script>
	</head>
	
	<body>
		<div class="container">
			<div class="atmiddle"><button type="button" class="btn btn-lg btn-default navbar-btn">MTL WORKS: New Freelancer</button></div>
				<div class="row centered-form">
			       	<div class="col-xs-12 col-sm-10 col-md-8 col-sm-offset-2 col-md-offset-2">
			        	<div class="panel panel-default">
			        		<div class="panel-heading">
						    	<h3 class="panel-title"><small>Registration Form</small></h3>
						 	</div>
						 	<div class="panel-body">
						    	<form role="form" method="post" id="SignupWorkerForm" action="SignupWorker">
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

						    		<div class="row">
						    			<div class="col-xs-12 col-sm-12 col-md-12">
						    				<div class="form-group">
						    					<input type="email" name="email" id="email" class="form-control input-sm" placeholder="Email Address">
						    				</div>	
						    			</div>
						    		</div>
						    		
						    		<div class="row">
						    			<div class="col-xs-12 col-sm-12 col-md-12">
						    			<div class="form-group">
			    		                     	<div id="emailValidationError"></div>
			    		                </div>
			    		            	</div>
			    		            </div>
			    		            
						    			<div class="row">
						    			<div class="col-xs-12 col-sm-12 col-md-12">
						    				<div class="form-group">
						    					<input type="password" name="password" id="password" class="form-control input-sm" placeholder="Password">
						    				</div>
						    			</div>
						    		</div>
						    			    			
						    		<div class="form-group">
						    		<label for="skill">Choose a Skill Category:</label> 
					      				<input type="radio" checked="checked" name="skill" value="writing" id="skill" /> Writing
					      				<input type="radio" name="skill" value="design" id="skill" /> Design
					      				<input type="radio" name="skill" value="programming" id="skill" /> Programming
					      				<input type="radio" name="skill" value="marketing" id="skill" /> Marketing
					      				<input type="radio" name="skill" value="translation" id="skill" /> Translation
					      				<input type="radio" name="skill" value="engineering" id="skill" /> Engineering
					      				<input type="radio" name="skill" value="admin" id="skill" /> Admin Support
					      			</div>
					      		
			      
			      					<div class="form-group row">
			      						<div class="col-xs-4 col-sm-4 col-md-4"><label for="location">Location:</label></div>
			        					<div class="col-xs-8 col-sm-8 col-md-8">
			        					<input type="text" class="form-control" name="location" id="location" placeholder="Example: Montreal, Toronto">
			        					</div>
			        				</div>
						    		<div class="form-group row">
						    			<div class="col-xs-4 col-sm-4 col-md-4"><label for="rate">Hourly Rate:</label></div>
			        					<div class="col-xs-8 col-sm-8 col-md-8"><input type="text" class="form-control" name="rate" id="rate" placeholder="15, 16.5 etc"></div>
			        				</div>
						    		<div class="form-group row">
						    			<div class="col-xs-4 col-sm-4 col-md-4"><label for="availability">Availability per week:</label></div>
			        					<div class="col-xs-8 col-sm-8 col-md-8"><input type="text" class="form-control" name="availability" id="availability" placeholder="30, 30, 35 etc"></div>
			        				</div>
			        				<div class="form-group row">
						    			<div class="col-xs-4 col-sm-4 col-md-4"><label for="paypal">Paypal Business Email:</label></div>
			        					<div class="col-xs-8 col-sm-8 col-md-8"><input class="form-control" name="paypal" id="paypal" placeholder="Enter email address associated with your Paypal business account."></div>
			        				</div>
			               				
						    		<input type="submit" value="Register" id="submitBtn" class="btn btn-info btn-block">
						    		
						    	</form>
						    </div>
				    	</div>
			    	</div>
    	</div>
    </div>
</body>
</html>