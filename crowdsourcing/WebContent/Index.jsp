<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>MTL Works</title>
		<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    	<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" />
	</head>
	
	<body>
		<div class="container">
			<div class="masthead">
        		<h1><a class="btn btn-lg btn-default" href="index.jsp" role="button">MTL WORKS</a></h1>
        		<nav class="navbar navbar-default">
          			<ul class="nav nav-justified">
            			<li class="active"><a href="index.jsp">Home</a></li>
            			<li><a href="all-projects.jsp">Projects</a></li>
            			<li><a href="all-clients.jsp">Clients</a></li>
            			<li><a href="all-workers.jsp">Workers</a></li>
          			</ul>
        		</nav>
      		</div>

      <div class="jumbotron homeback">
      	<h1>Let's do it together</h1>
        <p class="lead">MTL Works is a great resource for remote work.</p>
        <p><a class="btn btn-lg btn-primary" href="login.jsp" role="button">Log in</a></p>
      </div>

      <!-- Example row of columns -->
      <div class="row">
        <div class="col-lg-4">
          <h2>Remote work, simplified.</h2>
          <p >We aim to help people do their remote projects. You can explore the projects and features. </p>
          <p></p>
          <p><a class="btn btn-primary" href="all-projects.jsp" role="button">Explore Projects &raquo;</a></p>
        </div>
        <div class="col-lg-4">
          <h2>Clients</h2>
          <p>As client anyone can explore projects and post any new project. The clients track payments and rates projects.</p>
          <p><a class="btn btn-primary" href="signup-client.jsp" role="button">Sign Up as Client &raquo;</a></p>
       </div>
        <div class="col-lg-4">
          <h2>Freelancers</h2>
          <p>As freelancer with better work you get more credibility score and rank higher in recommendation lists.</p>
          <p><a class="btn btn-primary" href="signup-worker.jsp" role="button">Sign Up as Freelancer &raquo;</a></p>
        </div>
      </div>

      <!-- Site footer -->
      <footer class="footer">
        <p>&copy; 2016 Winter, INSE 6260</p>
      </footer>

    </div> <!-- /container -->

	</body>
</html>