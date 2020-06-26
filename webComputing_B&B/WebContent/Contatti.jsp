<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head> 
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<LINK rel="stylesheet" href="bootstrap-4.4.1-dist/css/bootstrap.min.css">

	<script src="jquery/jquery-3.2.1.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="js/redirect.js"></script>
	<script src="js/utility.js"></script>
	<script src="js/mailContatti.js"></script>
	
	
	<title>B&B Chiara - Contatti</title>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
<jsp:include page="jsp/menu.jsp"></jsp:include>

<div class="container">
  <h3 class="text-center">Contatti</h3>
  <p class="text-center"><em>Ti servono informazioni? contattaci.</em></p>
  <div class="row test">
    <div class="col-md-4">
      <p>Ci puoi trovare:</p>
      <p><span class="glyphicon glyphicon-map-marker"></span>Via Martiri Fosse Ardeatine 1, Polistena, RC</p>
      <p><span class="glyphicon glyphicon-phone"></span>Telefono: +39 335484421</p>
      <p><span class="glyphicon glyphicon-envelope"></span>Email: bandbchiara@libero.it</p> 
      <p><span class="glyphicon glyphicon-envelope"></span><a  href="https://wa.me/39335484421" class="btn btn-success">Contattaci su Whatsapp</a> 
    	</p>
    </div>
    <div class="col-md-8">
      <div class="row">
        <div class="col-sm-6 form-group ">
          <input class="form-control" id="nome" name="nome" placeholder="Nome" type="text" required>
        </div>
        <div class="col-sm-6 form-group">
          <input class="form-control" id="cognome" name="cognome" placeholder="Cognome" type="text" required>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-6 form-group ">
          <input class="form-control" id="telefono" name="telefono" placeholder="Telefono" type="text" required>
        </div>
        <div class="col-sm-6 form-group">
          <input class="form-control" id="email" name="email" placeholder="Email" type="email" required>
        </div>
      </div>
      <textarea class="form-control" id="testo" name="testo" placeholder="Scrivici" rows="5"></textarea>
      <div class="row">
        <div class="col-md-12 form-group">
          <button class="btn pull-left btn-success" type="submit" onclick="contattaci();">Contattaci</button>
        </div>
      </div> 
    </div>
  </div>
</div>
<h3 align="center"><em id="grazie"></em></h3>
</body>
</html>