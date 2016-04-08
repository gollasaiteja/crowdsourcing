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
        		<h1><button type="button" class="btn btn-lg btn-success">MTL Works</button></h1>
        		<nav class="navbar navbar-default">
          			<ul class="nav nav-justified">
            			<li class="active"><a href="index.jsp">Home</a></li>
            			<li><a href="all-projects.jsp">Projects</a></li>
            			<li><a href="all-clients.jsp">Clients</a></li>
            			<li><a href="all-workers.jsp">Workers</a></li>
          			</ul>
        		</nav>
      		</div>

      <div class="jumbotron homeback" style="color:snow;">
      	<h1>How Projects Work</h1>
        <p class="lead">Projects have title, description and importantly a skill associated with it. When a project is completed, the client
        must give a credibility score to the freelancer.</p>
        <p><a class="btn btn-lg btn-primary" href="login.jsp" role="button">Log in</a></p>
      </div>

      <!-- Example row of columns -->
      <div class="row">
         
      </div>

      <!-- Site footer -->
      <footer class="footer">
        <p>&copy; 2016 Winter, INSE 6260</p>
      </footer>

    </div> <!-- /container -->

	</body>
</html>