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
	<script src="js/rooms.js"></script>
	
	<title>B&B Chiara - La Suite</title>
</head>
<body>
<jsp:include page="jsp/menu.jsp"></jsp:include>

    <div class="container">
      <h1 class="mt-5">La Suite</h1>

      <div class="row">

        <div class="col-md-8">
          <img id="principale" class="img-fluid" src="images/LeCamere/suiteChiara.jpg" alt="">
        </div>

        <div class="col-md-4">
          <h3 class="my-3">Descrizione</h3>
          <p>La Suite Chiara è un punto di accoglienza privilegiato per i turisti ed i viaggiatori che decidono di sostare nel bel mezzo del Centro Storico di Polistena oggi ricco di attività ed in grado di offrire ogni giorno ai turisti uno splendido affresco di cultura, paesaggio e costume.</p>
          <h3 class="my-3">Camere dotate di:</h3>
          <ul>
            <li>Aria Condizionata*</li>
            <li>Frigobar*</li>
            <li>Macchina the/caffè</li>
            <li>Microonde</li>
            <li>TV Color</li>
            <li>Asciugacapelli</li>
            <li>Lavatrice*</li>
          </ul>
        </div>

      </div>

      <h3 class="my-4">Altre Foto</h3>

      <div class="row">

        <div class="col-md-3 col-sm-6 mb-4">
         <a href="#">
           <img id="secondaria1" class="img-fluid" src="images/LeCamere/suiteChiara2.jpg" alt="" onclick="swapImage(this.id, this.src)">
         </a>
        </div>

        <div class="col-md-3 col-sm-6 mb-4">
          <a href="#">
            <img id="secondaria2" class="img-fluid" src="images/LeCamere/suiteLiving.jpg" alt="" onclick="swapImage(this.id, this.src)">
          </a>
        </div>

        <div class="col-md-3 col-sm-6 mb-4">
          <a href="#">
            <img id="secondaria3" class="img-fluid" src="images/LeCamere/suiteLiving2.jpg" alt=""onclick="swapImage(this.id, this.src)">
          </a>
        </div>

        <div class="col-md-3 col-sm-6 mb-4">
          <a href="#">
            <img id="secondaria4" class="img-fluid" src="images/LeCamere/suiteLiving3.jpg" alt=""onclick="swapImage(this.id, this.src)">
          </a>
        </div>

      </div>

    </div>
    
    <p>*servizi supplementari a pagamento</p>
 </body>

</html>