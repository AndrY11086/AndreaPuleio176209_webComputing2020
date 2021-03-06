<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<LINK rel="stylesheet" href="bootstrap-4.4.1-dist/css/bootstrap.min.css">

<script src="jquery/jquery-3.2.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="bootstrap-4.4.1-dist/js/bootstrap.min.js"></script>

<title></title>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark fixed-top">

		<!-- Brand -->
		<div class="container">
			<a class="navbar-brand" href="#">B&BChiara</a>

			<!-- Toggler/collapsibe Button -->
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>

			<!-- Navbar links -->
			<div class="collapse navbar-collapse" id="collapsibleNavbar">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a class="nav-link"
						href="redirect?r=Home">Home</a></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbardrop"
						data-toggle="dropdown"> il B&B </a>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="redirect?r=Suite">Suite Chiara</a>
							<a class="dropdown-item" href="redirect?r=LeCamere">Le Camere</a>
						</div></li>
					<li class="nav-item"><a class="nav-link"
						href="redirect?r=DoveSiamo">Dove Siamo</a></li>
					<li class="nav-item"><a class="nav-link"
						href="redirect?r=Fotogallery">Fotogallery</a></li>
					<li class="nav-item"><a class="nav-link"
						href="redirect?r=Eventi">Eventi</a></li>
					<li class="nav-item"><a class="nav-link"
						href="redirect?r=Escursioni">Escursioni</a></li>
					<li class="nav-item"><a class="nav-link"
						href="redirect?r=Recensioni">Recensioni</a></li>
					<li class="nav-item"><a class="nav-link"
						href="redirect?r=Regolamento">Regolamento</a></li>
					<li class="nav-item"><a class="nav-link"
						href="redirect?r=Contatti">Contatti</a></li>
					<li class="nav-item"><a href="redirect?r=AreaRiservata"
						class="nav-link btn btn-outline-warning" role="button">Area
							Riservata</a></li>
				</ul>
			</div>
		</div>
	</nav>

</body>
</html>