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
	padding-top: 70px;
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
							<li><a href="Signin">Utilisateur</a></li>
						</ul></li>

				</ul>
				<ul class="nav navbar-nav pull-right">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <%
 	User u = (User) request.getAttribute("user");
	String dfuid = (String) request.getAttribute("dfuid");
	String chfuid = (String) request.getAttribute("chfuid");
	String chiuid = (String) request.getAttribute("chiuid");
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
			<div class="col-sm-4">
			Chargé du courrier : <b> <% if (u != null) out.println(u.getLogin()); %></b>
			</div>
			<div class="col-sm-offset-5 col-sm-3">
				<p>Date : <b id="dt"></b> </p>
			</div>
		</div>
		<div class="row">
				<form class="form-horizontal">
					
					<div class="form-group">
					    <label class="col-sm-3 control-label">Destinataire final</label>
					    <div class=" col-sm-6">
						    <%=dfuid %>
						</div>
					</div>
					
					<div class="form-group">
					    <label class="col-sm-3 control-label">Chargé du courrier final</label>
					    <div class=" col-sm-6">
						    <%=chfuid %>
						</div>
					</div>
					
					<div class="form-group">
					    <label class="col-sm-3 control-label">Chargé du courrier intermédaire</label>
					    <div class=" col-sm-6">
						    <%=chiuid %>
						</div>
					</div>
					
					<div class="form-group">
					    <label class="col-sm-3 control-label">Courrier Type</label>
					    <div class=" col-sm-6">
						    <select class="form-control" name="type" id="ct">
						        <option value=""></option>
						    	<option value="valeur">Courrier valeur</option>
						    	<option value="simple">Courrier Simple</option>
						    </select>
						</div>
					</div>
					
					<div class="form-group">
					    <label class="col-sm-3 control-label">Courrier Nature</label>
					    <div class=" col-sm-6">
						    <select class="form-control" name="nature" id="cn">

						    </select>
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-3 control-label">Montant</label>
						<div class="col-sm-6">
				    		<input type="text" class="form-control" pattern="[0-9]" placeholder="Montant" name="mnt" required>
						</div>
					</div>
					
				
				  
				  <div class="form-group">
						<div class="col-sm-offset-3 col-sm-6">
				    		  <button type="submit" class="form-control btn btn-success">Ajouter Courrier</button>
						</div>
					</div>
				</form>
		</div>
	</div>

	<script src="/CourrierAutomatique/bootstrapjsp/bootstrap/js/bootstrap.min.js"></script>
	
	<script>
		var date = new Date();
		var formadate = date.getDate()  + '-' + (date.getMonth() + 1) + '-' +  date.getFullYear() ;
		$('#dt').append(formadate);

		$('#ct').change(function(){
			if($('#ct option:selected').val() =='valeur'){
				$('#cn').html("<option value='cheque'>Chéque</option><option value='effet'>Effet</option><option value='titre de credit'>Titre de crédit</option><option value='travel cheque'>Travel Cheque</option><option value='euro cheque'>Euro Cheque</option><option value='bon de caisse'>Bon de Caisse</option><option value='ordre de virement'>Ordre de Virement</option><option value='carte bancaire'>Carte Bancaire</option><option value='code confidentiel'>Code Confidentiel</option><option value='journee comptable'>Journée Comptable</option><option value='documents originaux SBE'>Documents originaux SBE</option><option value='documents originaux a caractere juridique'>documents originaux à caractére juridique</option><option value='bon d essence'>bon d'essence</option><option value='bon de restauration'>bon de restauration</option><option value='carnets de cheques'>carnets de cheques</option><option value='livrets d epargnes'>livrets d'epargnes</option><option value='PV de toute nature'>PV de toute nature</option><option value='facture'>facture</option>");
			}else {
				$('#cn').html("<option value='lettre'>lettre</option><option value='note'>note</option><option value='reclamation'>réclamation</option><option value='opposition administrative'>opposition administrative</option><option value='notification saisie arret'>notification saisie arrêt</option><option value='controle des impots'>controle des impôts</option><option value='demande de credit'>demande de crédit</option><option value='demande demploi'>demande d'emploi</option><option value='feuille de maladie'>feuille de maladie</option><option value='fiche de paie'>fiche de paie</option><option value='recu jardin d enfant'>recu jardin d'enfant</option><option value='demande carte bancaire'>demande carte bancaire</option><option value='decharge'>décharge</option><option value='situation fin du mois'>situation fin du mois</option><option value='état prime de scolarité'>etat prime de scolarite</option><option value='releve de compte'>relevé de compte</option><option value='bord transmission CNP'>bord transmission CNP</option><option value='demande de retrait BBE'>demande de retrait BBE</option><option value='facture'>facture</option><option value='enveloppe nominative'>enveloppe nominative</option><option value='releve de compte'>releve de compte</option><option value='echelle d interet'>échelle d'interet</option><option value='dossier procureur'>dossier procureur</option><option value='demande mainlevee'>demande mainlevee</option><option value='engagement'>engagement</option><option value='divers'>divers</option>");
			}
		});
	</script>
</body>
</html>