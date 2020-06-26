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
	<script src="js/gallery.js"></script>
	
	<title>B&B Chiara - Fotogallery</title>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false" onload="onLoad()">

<jsp:include page="jsp/menu.jsp"></jsp:include>

<div id="demo" class="carousel slide container-fluid" data-ride="carousel" >

  <!-- Indicators 
  <ul class="carousel-indicators" id="indicators">
   
  </ul>

  <div class="carousel-inner" id="photo">
  
  </div>-->

  <!-- Left and right controls -->
  <a class="carousel-control-prev" href="#demo" data-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </a>
  <a class="carousel-control-next" href="#demo" data-slide="next">
    <span class="carousel-control-next-icon"></span>
  </a>

</div>

</body>
</html>