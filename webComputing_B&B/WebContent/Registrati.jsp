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
	<script src="js/utility.js"></script>
	<script src="js/registrazione.js"></script>
	<script src="js/autocompleteGoogle.js"></script>
	
	<title>B&B Chiara - Registrati</title>
</head>
<body>
<jsp:include page="jsp/menu.jsp"></jsp:include>

<form action="RegistraUtente" method="post">
  <div class="container mx-auto border border-light rounded-lg h-50 m-5" style="width:60%" style="height:200px">
    <img align="top" src="images/b&b logo.png" alt="Logo BBChiara" style="width:30%" hspace="35%">
    <p>Per favore compila il form per creare un account.</p>
    <hr>

    <label for="email"><b>Email</b></label>
    <input type="text" placeholder="Inserisci Email" name="email" required id="email">

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Inserisci Password" name="password" required id="password">

    <label for="psw-repeat"><b>Repeat Password</b></label>
    <input type="password" placeholder="Ripeti Password" name="psw-repeat" required id="conferma">
    <hr>
    
    <label for="nome"><b>Nome</b></label>
    <input type="text" placeholder="Nome" name="nome" required id="nome">
    <hr>
    
    <label for="cognome"><b>Cognome</b></label>
    <input type="text" placeholder="Cognome" name="cognome" required id="cognome">
    <hr>
    
     <label for="numeroTelefono"><b>Numero di telefono</b></label>
    <input type="number" placeholder="numero telefono" name="numeroTelefono" required id="numeroTelefono">
    <hr>
    
	<label for="città Residenza"><b>Città di Residenza</b></label>
    <input type="text" placeholder="Città di Residenza" name="city" required id="city">
    <hr>
    
    <div class="container">
    <p id="sex" align="left">Sesso:</p>
   <div class="form-check-inline">
      <label class="form-check-label" for="radio1">
        <input type="radio" class="form-check-input" name="optradio" value="maschio" checked id="maschio">M
      </label>
    </div>
    <div class="form-check-inline">
      <label>
        <input type="radio" class="form-check-input" name="optradio" value="femmina" checked id="femmina">F</label>
    </div>
    </div>
    
    <input type="checkbox" id="accetta"></input><p>Per creare un account devi accettare i nostri <a href="#">Termini & condizioni</a>.</p>
    <button type="submit" class="registerbtn btn btn-success">Registrati</button>
  </div>

  <div class="container signin">
    <p>Hai già un account? <a href="redirect?r=AreaRiservata">Accedi</a>.</p>
  </div>
</form>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDEqgfjOK0KU_1Ame-PYJhEo3wiGZlUt9w&libraries=places&callback=initAutocomplete"
        async defer></script>
        
</body>
</html>