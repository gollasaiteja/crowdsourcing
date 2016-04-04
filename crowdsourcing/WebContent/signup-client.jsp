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
		<title>MTL Works: New Client</title>
	</head>
	
	<body>
		<div class="container">

      	<div class="atmiddle"><button type="button" class="btn btn-lg btn-default navbar-btn">MTL WORKS: New Client</button></div>
		
		
      <div class="row centered-form">
       	<div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    	<h3 class="panel-title">Registration Form<small></small></h3>
			 	</div>
			 	<div class="panel-body">
			    	<form role="form" method="post" id="signupClient" action="SignupClient">
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
			    			<input type="email" name="email" id="clientEmail" class="form-control input-sm" placeholder="Email Address">
			    		</div>
						<div class="form-group">
			    			<div id="emailValidationError"></div>
			    		</div>
			    		<div class="row">
			    			<div class="col-xs-6 col-sm-6 col-md-6">
			    				<div class="form-group">
			    					<input type="password" name="password" id="password" class="form-control input-sm" placeholder="Password">
			    				</div>
			    			</div>
			    			
			    		</div>
			    			
						<div class="form-group">
        					<input type="text" class="form-control" name="card_holder_name" id="card-holder-name" placeholder="Card Holder's Name">
        				</div>
      
      					<div class="form-group">
        					<input type="text" class="form-control" name="card_number" id="card-number" placeholder="Debit/Credit Card Number">
        				</div>

						<div class="form-group row">
        					<label class="col-sm-4 control-label" for="expiry_month">Expires on:</label>
        					<div class="col-sm-8">
          						<div class="row">
            						<div class="col-xs-6">
              							<select class="form-control col-sm-3" name="expiry_month" id="expiry-month">
                							<option>Month</option>
							                <option value="01">Jan</option>
							                <option value="02">Feb</option>
							                <option value="03">Mar</option>
							                <option value="04">Apr</option>
							                <option value="05">May</option>
							                <option value="06">June</option>
							                <option value="07">July</option>
							                <option value="08">Aug</option>
							                <option value="09">Sep</option>
							                <option value="10">Oct</option>
							                <option value="11">Nov</option>
							                <option value="12">Dec</option>
							             </select>
            						</div>
            						
            						<div class="col-xs-6">
              							<select class="form-control col-sm-4" name="expiry_year">
							               
							                <option value="16">2016</option>
							                <option value="17">2017</option>
							                <option value="18">2018</option>
							                <option value="19">2019</option>
							                <option value="20">2020</option>
							                <option value="21">2021</option>
							                <option value="22">2022</option>
							                <option value="23">2023</option>
							             </select>
									</div>
          						</div>
        					</div>
      					</div>
      					
				      	<div class="form-group">
				      		<div class="row">
				        	<label class="col-md-4 control-label" for="cvv">Card CVV</label>
				        	<div class="col-md-8">
				          		<input type="text" class="form-control" name="cvv" id="cvv" placeholder="Security Code">
				        	</div>
				        	</div>
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