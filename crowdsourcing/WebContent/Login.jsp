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
		<title>MTL Works | Login</title>
	</head>
	
	<body>
		  <div class="wrapper">
		    <form class="form-signin" method="post" action="Login">       
		      <h2><a class="btn btn-lg btn-block" href="index.jsp" role="button">MTL WORKS</a></h2>
		      <input type="text" class="form-control" name="email" placeholder="Email Address" required="true" autofocus="" />
		      <input type="password" class="form-control" name="password" placeholder="Password" required="true"/>
		      <label for="client"> 
		      	<input type="radio" name="type" value="1" id="client" />  I'm a Client
		      </label><br />
			  <label for="worker">
			  	<input type="radio" checked="checked" name="type" value="0" id="worker" />  I'm a Freelancer
			  </label><br />
		      <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
			  <br><br><hr>    
		      <div class="row">
			    <a class="btn btn-warning btn-block" href="signup-client.jsp" role="button">Sign Up as Client</a>
			    <a class="btn btn-warning btn-block" href="signup-worker.jsp" role="button">Sign Up as Freelancer</a>
			  </div>
		    </form>
		</div>
	</body>
</html>