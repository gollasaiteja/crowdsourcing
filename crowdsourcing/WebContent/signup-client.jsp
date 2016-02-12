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
		<title>MTL Works: New Client</title>
	</head>
	
	<body>
		<div class="container">

      <!-- Jumbotron -->
      <div class="jumbotron">
      	<h1>MTL Works: New Client</h1>
      </div>
		
		
      <div class="row centered-form">
       	<div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
        	<div class="panel panel-default">
        		<div class="panel-heading">
			    	<h3 class="panel-title">Client Registration Form <small>MTL Works</small></h3>
			 	</div>
			 	<div class="panel-body">
			    	<form role="form" method="post" action="SignupClient">
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
			    			
			    			<div class="col-xs-6 col-sm-6 col-md-6">
			    				<div class="form-group">
			    					<input type="password" name="password_confirmation" id="password_confirmation" class="form-control input-sm" placeholder="Confirm Password">
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
        					<label class="col-sm-5 control-label" for="expiry_month">Expiration Date</label>
        					<div class="col-sm-7">
          						<div class="row">
            						<div class="col-xs-6">
              							<select class="form-control col-sm-3" name="expiry_month" id="expiry-month">
                							<option>Month</option>
							                <option value="01">Jan (01)</option>
							                <option value="02">Feb (02)</option>
							                <option value="03">Mar (03)</option>
							                <option value="04">Apr (04)</option>
							                <option value="05">May (05)</option>
							                <option value="06">June (06)</option>
							                <option value="07">July (07)</option>
							                <option value="08">Aug (08)</option>
							                <option value="09">Sep (09)</option>
							                <option value="10">Oct (10)</option>
							                <option value="11">Nov (11)</option>
							                <option value="12">Dec (12)</option>
							             </select>
            						</div>
            						
            						<div class="col-xs-6">
              							<select class="form-control col-sm-4" name="expiry_year">
							                <option value="13">2013</option>
							                <option value="14">2014</option>
							                <option value="15">2015</option>
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
			    			
			    		<input type="submit" value="Register" class="btn btn-info btn-block">
			    		
			    	</form>
			    </div>
	    	</div>
    	</div>
    	</div>
    </div>
</body>
</html>