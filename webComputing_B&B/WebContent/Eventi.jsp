<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<LINK rel="stylesheet" href="bootstrap-4.4.1-dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="js/eventi.js"></script>

<title>B&B Chiara - Eventi</title>
</head>
<body oncontextmenu="return false" onselectstart="return false"
	ondragstart="return false" onload="loadEventi()">
	
	<jsp:include page="jsp/menu.jsp"></jsp:include>

	<div id="splitLeft" class="split left">
		<div class="row">
			<div class="col-sm-12">
				<p>Seleziona l'evento</p>
				<c:if test="${loggato }">
				<button id='addEvent' class='btn btn-info btn-block btn-sm' onclick='aggiungi()'> + Aggiungi Evento </button><hr>
				</c:if>
				<div id="buttons"></div>
				
			</div>
		</div>
	</div>

	<!-- inizio lato destro  -->
	<div id="splitRight" class="split right">
		<div class='centered'>
			<img src=${fotoEvento } alt='Concerto'>
			<div class='container'>
				<h3>${evento.getNome()}</h3>
				<table class='table table-striped'>
					<tbody>
						<tr>
							<td>Data:</td>
							<td>${evento.getDataEvento()}</td>
						</tr>
						<tr>
							<td>Tipo:</td>
							<td>${evento.getTipo()}</td>
						</tr>
						<tr>
							<td>Durata:</td>
							<td>${evento.getDurata()}</td>
						</tr>
						<tr>
							<td>Descrizione:</td>
							<td>${evento.getDescrizione()}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

	</div>
	<!-- fine lato destro -->
</body>
</html>