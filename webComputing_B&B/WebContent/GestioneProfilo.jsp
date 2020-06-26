<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-signin-client_id"
	content="810905248828-gfrqvbsqntsk2cjlqk7j1n3ouvciibdu.apps.googleusercontent.com">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
<LINK rel="stylesheet" href="bootstrap-4.4.1-dist/css/bootstrap.min.css">

<script src="jquery/jquery-3.2.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="js/utility.js"></script>
<script src="js/googleLogin.js"></script>
<script src="js/modificaProfilo.js"></script>
<script src="js/nuovaPrenotazioneBis.js"></script>
<script src="js/gestionePrenotazioni.js"></script>
<script src="js/statistiche.js"></script>

<script src='https://www.google.com/jsapi'></script>

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://apis.google.com/js/platform.js?onload=onLoad" async
	defer></script>

<title>B&B Chiara - GestioneProfilo</title>

<style id="stile"></style>
</head>
<body>

	<jsp:include page="jsp/menu.jsp"></jsp:include>

	<div id="splitLeft" class="split left">
		<div class="row">
			<div class="col-sm-12">

				<button class="btn btn-info btn-block btn-sm" id="decrescente"
					onclick="visualizzaPrenotazioni(this.id)">Gestione
					Prenotazioni</button>
				<button class="btn btn-info btn-block btn-sm"
					onclick="visualizzaProfilo()">Gestione Profilo</button>
				<button class="btn btn-info btn-block btn-sm"
					onclick="prenotazioneBis()">Nuova Prenotazione</button>
				<c:if test="${admin}">
					<button class="btn btn-info btn-block btn-sm" onclick="stat()">Statistiche</button>
				</c:if>

				<%
					if (session.getAttribute("token") == null) {
				%>

				<a href="EffettuaLogout" class="btn btn-info btn-block btn-sm"
					role="button">Logout</a>

				<%
					} else {
				%>
				<button class="btn btn-info btn-block btn-sm" role="button"
					onclick="logoutGoogle()">Logout Google</button>
				<%
					}
				%>

			</div>
		</div>
	</div>

	<div id="splitRight" class="split right">

		<!-- inserire il lato destro della schermata -->
		<div id="gestionePrenotazioni" style="display: none;">
			<h3 align='center'>
				<em> Lista Prenotazioni: </em>
			</h3>
		</div>

		<div id="modificaProfilo" style="display: none;">
			<form action="ModificaProfilo" method="post">
				<div
					class='container mx-auto border border-light rounded-lg h-50 m-5'
					style='width: 60%' style='height:200px'>
					<label for=nome><b>Nome</b></label> <input type='text'
						placeolder='NOME' name='nome' required id='nome'
						value=${cliente.getNome() }></input> <label for=cognome><b>Cognome</b></label>
					<input type='text' placeolder='COGNOME' name='cognome' required
						id='cognome' value=${cliente.getCognome() }> <label
						for=email><b>Email</b></label> <input type='email'
						placeolder='EMAIL' name='email' required id='email'
						value=${cliente.getIndirizzoEmail() }> <label for=telefono><b>Numero
							di Telefono</b></label> <input type='text' placeolder='TELEFONO'
						name='telefono' required id='telefono'
						value=${cliente.getNumeroTelefono() }> <label for=città><b>Città</b></label>
					<input type='text' placeolder='CITTA' name='city' required
						id='city' value=${cliente.getCity() }> <label for=password><b>Password</b></label>
					<input type='text' placeolder='PASSWORD' name='password' required
						id='password' value=${cliente.getPassword() }><br>

					<div align='center'>
						<button name="button" value="save" align='left'
							class='btn btn-success'>Salva Modifiche</button>
						<button name="button" value="remove" align='right'
							class='btn btn-danger'>Rimuovi Profilo</button>
					</div>
				</div>
			</form>
		</div>



		<div id="nuovaPrenotazione" style="display: none;"></div>
		<div id="statistiche"></div>
	</div>

</body>
</html>