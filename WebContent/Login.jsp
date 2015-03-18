<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>



<!DOCTYPE html>
<html lang="en">
  <head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>

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
  padding-top: 40px;
  padding-bottom: 40px;
  background-color: #eee;
}

.form-signin {
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}
.form-signin .form-signin-heading,
.form-signin .checkbox {
  margin-bottom: 10px;
}
.form-signin .checkbox {
  font-weight: normal;
}
.form-signin .form-control {
  position: relative;
  height: auto;
  -webkit-box-sizing: border-box;
     -moz-box-sizing: border-box;
          box-sizing: border-box;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="text"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>
</head>

  <body>

    <div class="container">

      <form class="form-signin" role="form" method="post" action="Login">
        <h2 class="form-signin-heading">WELCOME</h2>
        <label for="inputEmail" class="sr-only">Login</label>
        <input type="text" class="form-control" placeholder="Login" name="login" required autofocus>
        <label for="inputPassword" class="sr-only">Mot de passe</label>
        <input type="password"  class="form-control" placeholder="Mot de passe" name="pw" required>
        <%
        	String error = request.getParameter("error");
        		 if(error != null) 
        			out.println("<p class='text-danger'>Login ou Mot de passe incorect !</p>");
        %>
        <button class="btn btn-lg btn-success form-control" type="submit">Log in</button>
      </form>

    </div> <!-- /container -->


	<script src="/CourrierAutomatique/bootstrapjsp/bootstrap/js/bootstrap.min.js"></script>
	
  </body>
</html>
