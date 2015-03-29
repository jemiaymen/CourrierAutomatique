<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,com.model.*"%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>New Courrier</title>

<!-- Bootstrap -->
<link href="/CourrierAutomatique/bootstrapjsp/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="//oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="//oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<style media="screen" type="text/css">
body {
	padding-top: 100px;
	padding-bottom: 30px;
}
.logo{
  margin-top: -18px;
  height : 311%;
}
</style>
</head>
<body>

	<!-- Fixed navbar -->
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><img class="logo" src="/CourrierAutomatique/img/logo.png" ></a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="#">Home</a></li>
					<li class="dropdown active"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown">Courrier<span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="Newcourrier">Ajouter courrier</a></li>
							<li><a href="GPochette">Gerer Pochette</a></li>
						</ul></li>

				</ul>
				<ul class="nav navbar-nav pull-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <%
 	User u = (User) request.getAttribute("user");
	String ps = (String) request.getAttribute("ps");
 	if (u != null) {
 		out.println(u.getLogin());
 	}
 %><span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="Logout">Logout</a></li>
						</ul></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>
	<div class="container ">
		<div class="row">
			<div class="col-sm-offset-2 col-sm-3">
				<form method="post">
					<input type="hidden" name="addp" value="yes">
					<button type="submit" class="btn btn-success btn-lg">Nouveau Pochette</button>
				</form>
			</div>
			<div class="col-sm-6">
			<h2>Pochettes</h2>
			<table class="table">
				<th>Pochette ID</th>
				<th>Nombre de Courrier </th>
				<th>Action</th>
				<%=ps %>
			</table>
			</div>
				
		</div>

		
		<div class="row">
			<div class="col-sm-offset-2 col-sm-9">
			<%
				String courrier = (String) request.getAttribute("courrier");
				if(courrier != null){
					out.println(courrier);
				}
			%>
			</div>
		</div>
	</div>

	<script src="/CourrierAutomatique/bootstrapjsp/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>