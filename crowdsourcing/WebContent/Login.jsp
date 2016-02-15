<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/script.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<title>MTL Works</title>
	</head>
	
	<body>
		  <div class="wrapper">
		    <form class="form-signin" method="post" action="Login">       
		      <h2 class="form-signin-heading">MTL Works</h2>
		      <input type="text" class="form-control" name="email" placeholder="Email Address" required="true" autofocus="" />
		      <input type="password" class="form-control" name="password" placeholder="Password" required="true"/>
		      <input type="hidden" name="option" value="" id="btn-input" />
				<div class="btn-group" data-toggle="buttons-radio">  
				  <button id="btn-one" type="button" data-toggle="button" name="type" value="1" class="btn btn-secondary">I'm a Client</button>
				  <button id="btn-two" type="button" data-toggle="button" name="type" value="0" class="btn btn-secondary">I'm a Freelancer</button>
				</div>
		      <label class="checkbox">
		        <input type="checkbox" value="remember-me" id="rememberMe" name="rememberMe"> Remember me
		      </label>
		      <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>   
		    </form>
  		</div>
	</body>
</html>