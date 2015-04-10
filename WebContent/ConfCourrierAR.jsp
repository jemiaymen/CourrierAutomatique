<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*,com.model.*,com.controller.*"%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Confirmation Courrier</title>

<!-- Bootstrap -->
<link href="/CourrierAutomatique/bootstrapjsp/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="/CourrierAutomatique/js/jquery-1.11.2.min.js"></script>

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

<%
String[] courrier = (String[]) request.getAttribute("courrier");
User u = (User) request.getAttribute("user");
String rej = (String) request.getAttribute("rej");
%>
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
					<li ><a href="ConfRCourrierFD">Confirmation Courrier reception </a></li>
					<li class='active'><a href="ConfCourrierAR">Confirmation Courrier A/R <% if (courrier[1] != null) out.println("<span class='badge'>" + courrier[1] +"</span>") ;%></a></li>
				</ul>
				<ul class="nav navbar-nav pull-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <%

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
			<%
				if(rej != null)
					out.println(rej);
			%>
		</div>
		
		<div class="row">
			<div class="col-sm-12">
			<%
				
				if(courrier[0] != null){
					out.println(courrier[0]);
				}else{
					out.println("<h3>Pas de Courrier à Confirmer</h3>");
				}
			%>
			</div>
		</div>
		
		
		

	</div>

	<script src="/CourrierAutomatique/bootstrapjsp/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>