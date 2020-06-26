<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<script src="js/carrello.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>B&B Chiara - Carrello</title>
</head>
<body onload="loadCarrello()">

<jsp:include page="jsp/menu.jsp"></jsp:include>

<div align="center" class="col-sm-12">
	<div class="container">
		<font color="orange"><span class="fa fa-shopping-cart"></span><h4>Carrello:</h4></font><hr>
		<div align="left" id="carrello" class="col-sm-4 border border-light rounded-lg">

		<hr>
		
		<p align='justify' id='total'>Carrello Vuoto</p>
		
		</div><hr>
		<div class="col-sm-4" id='pay'>
			<button class="btn btn-warning" onclick="pagamento()">Acquista</button>
		</div>
	</div>
</div>

</body>
</html>