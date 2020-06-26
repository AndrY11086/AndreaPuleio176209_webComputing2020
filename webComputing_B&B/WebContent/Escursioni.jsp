<%@page import="java.util.LinkedList"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<script src="js/ie10-viewport-bug-workaround.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="js/escursioni.js"></script>
<script src="js/carrello.js"></script>

<title>B&B Chiara - Itinerari, Escursioni</title>
</head>
<body>

	<jsp:include page="jsp/menu.jsp"></jsp:include>

<%
	if(session.getAttribute("carrello") != null){
		LinkedList carrello = (LinkedList)session.getAttribute("carrello");
%>

	<div class="row">
		<div align="center" class="col-sm-12">
			<h3>
				<font color="orange"><span class="fa fa-shopping-cart"></span>
					Carrello: <h6 id="elementi"></h6></font>
					
			</h3>
		</div>
	</div>

<%}else{ %>

<div class="row">
		<div align="center" class="col-sm-12">
			<h3>
				<font color="orange"><span class="fa fa-shopping-cart"></span>
					Carrello: <h6 id="elementi">Vuoto</h6></font>
					
			</h3>
		</div>
	</div>
	
	<%} %>
	<div class="row">

		<div align="center" class="col">
			<img src="images/Escursioni/tourEolie.jpg" style="width: 50%"><br>
			<hr>
			<p>- Gita di un giorno alle Isole Eolie -</p>
			<p>Partenza da Gioia Tauro e visita a Vulcano, Lipari, Stromboli</p>
			<hr>
			<p id="isoleEolie">
				Prezzo: <font color="green" style="font-weight: bold;">
					45 &euro;</font> anzichè <font color="red"><del>60 &euro;</del></font>
			</p>
			<hr>
			<button id="isoleEolie" class="btn btn-warning" data-toggle="modal"
				data-target="#isoleEolieX" onclick="aggiungi(this.id, ${loggato})">
				<span class="fa fa-shopping-cart"></span> Aggiungi al carrello
			</button>

		</div>
		<hr>
		<div align="center" class="col">
			<img src="images/Escursioni/tracciolino.jpg" style="width: 50%"><br>
			<hr>
			<p>- Il sentiero del tracciolino, dalle "3 Croci" di S.Elia fino
				al Ponte di Bagnara -</p>
			<p>(il passaggio del sentiero è gratuito! il pagamento riguarda
				il trasferimento in auto e la Guida Turistica)</p>
			<hr>
			<p id="tracciolino">
				Prezzo: <font color="green" style="font-weight: bold;">
					25 &euro;</font> anzichè <font color="red"><del>35 &euro;</del></font>
			</p>
			<hr>
			<button id="tracciolino" class="btn btn-warning" data-toggle="modal" data-target="#tracciolinoX" onclick="aggiungi(this.id, ${loggato})">
				<span class="fa fa-shopping-cart"></span> Aggiungi al carrello
			</button>
		</div>

	</div>
	<br>
	<hr>
	<div class="row">

		<div align="center" class="col">
			<img src="images/Escursioni/sub.jpg" style="width: 50%"><br>
			<hr>
			<p>- Immersioni subacquee per una persona a Gioiosa Jonica -</p>
			<p>(necessaria la prenotazione)</p>
			<hr>
			<p id="subImm">
				Prezzo: <font color="green" style="font-weight: bold;">
					20 &euro;</font> anzichè <font color="red"><del>35 &euro;</del></font>
			</p>
			<hr>
			<button id="subImm" class="btn btn-warning" data-toggle="modal" data-target="#subImmX" onclick="aggiungi(this.id, ${loggato})">
				<span class="fa fa-shopping-cart"></span> Aggiungi al carrello
			</button>
		</div>
		<hr>
		<div align="center" class="col">
			<img src="images/Escursioni/Lido.jpg" style="width: 50%"><br>
			<hr>
			<p>- Ombrellone e 2 Lettini per una settimana in una località a
				tua scelta! Con noi Risparmi! -</p>
			<p>(Disponibile solo per settimana intera).</p>
			<hr>
			<p id="lido">
				Prezzo: <font color="green" style="font-weight: bold;">
					55 &euro;</font> anzichè <font color="red"><del>70 &euro;</del></font>
			</p>
			<hr>
			<button id="lido" class="btn btn-warning" data-toggle="modal" data-target="#lidoX" onclick="aggiungi(this.id, ${loggato})">
				<span class="fa fa-shopping-cart"></span> Aggiungi al carrello
			</button>
		</div>
	</div>
	<hr>
	<div align="center" class="row">
		<div class="col-sm-12">
			<button class="btn btn-warning btn-lg" onclick="redirect(${loggato})">
				<span class="fa fa-shopping-cart"></span> Vai al Carrello
			</button>
		</div>
	</div>

<div id="modalWindow"></div>

<div id="carrello"></div>

</body>
<footer>
</footer>
</html>