<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>



<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Gere Utilisateur</title>

<!-- Bootstrap -->
<link
	href="/CourrierAutomatique/bootstrapjsp/bootstrap/css/bootstrap.min.css"
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

.form-signin .form-signin-heading, .form-signin .checkbox {
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

.btn {
margin-top:20px;
}
</style>
</head>

<body>

	<div class="container">

		<form class="form-signin" role="form" method="post" action="Signin">
			<h2 class="form-signin-heading">Ajouter Utilisateur</h2>
			<label class="sr-only">Login</label> <input type="text"
				class="form-control" placeholder="Login" name="login" required
				autofocus> <label class="sr-only">Mot de passe</label> <input
				title="Password must contain at least 6 characters" type="password"
				class="form-control" placeholder="Mot de passe" name="pw" required
				pattern=".{6,}"
				onchange="
        this.setCustomValidity(this.validity.patternMismatch ? this.title : '');
        form.repw.pattern = this.value;
        ">
			<input type="password" class="form-control"
				placeholder="Re Mot de passe" name="repw" required
				title="Please enter the same Password as above"
				onchange="
        this.setCustomValidity(this.validity.patternMismatch ? this.title : '');
        ">
			
			
			<label class="sr-only">Prenom</label> 
			<input type="text" class="form-control" placeholder="Prenom" name="pren" required>
			
			<label class="sr-only">Nom</label> 
			<input type="text" class="form-control" placeholder="Nom" name="nom" required>
			
			<label class="sr-only">Cin</label> 
			<input title="Please enter Just number at least 8" type="text" class="form-control" placeholder="Cin" name="cin" pattern=".[0-9].{6,}" required
			onchange=" this.setCustomValidity(this.validity.patternMismatch ? this.title: ''); ">
			
			<label class="sr-only">Tel</label> 
			<input title="Please enter Just number at least 8" type="text" class="form-control" pattern=".[0-9].{6,}" placeholder="Tel" name="tel" required
			onchange=" this.setCustomValidity(this.validity.patternMismatch ? this.title: ''); ">
			
			<label >Role</label> 
			<select class="form-control" name="role" required>
				<option value="1">Administrateur</option>
				<option value="2">Agence</option>
				<option value="3">Service Central</option>
				<option value="4">Zone</option>
				<option value="5">Bureau D'ordre</option>
			</select>
			
			<label class="sr-only">Adress</label> 
			<textarea class="form-control" placeholder="Adress" name="adress" required></textarea>
			<%
				String error = request.getParameter("error");
				String success = request.getParameter("success");
				
				if (error != null)
					out.println("<p class='text-danger'>Error !</p>");
				else if(success != null)
					out.println("<p class='text-success'>Success .</p>");
			%>
			<button class="btn btn-lg btn-success form-control" type="submit">Ajouter</button>
		</form>

	</div>
	<!-- /container -->



</body>
</html>