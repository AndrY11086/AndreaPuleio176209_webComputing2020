<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="google-signin-client_id"
	content="810905248828-gfrqvbsqntsk2cjlqk7j1n3ouvciibdu.apps.googleusercontent.com">
<LINK rel="stylesheet" href="bootstrap-4.4.1-dist/css/bootstrap.min.css">

<script src="jquery/jquery-3.2.1.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="js/utility.js"></script>
<script src="js/googleLogin.js"></script>
<script src="js/facebookLogin.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"
	integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh"
	crossorigin="anonymous"></script>

<script src="https://apis.google.com/js/platform.js" async defer></script>

<script async defer crossorigin="anonymous"
	src="https://connect.facebook.net/it_IT/sdk.js#xfbml=1&version=v6.0"></script>

<title>B&B Chiara - Accedi</title>
</head>
<body>
	<jsp:include page="jsp/menu.jsp"></jsp:include>
	
	<%
		Cookie[] cookies = request.getCookies();
	String name = "", pass = "";
	boolean email = false, password = false;
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("email")) {
		name = cookie.getValue();
		email = true;
			}
			if (cookie.getName().equals("password")) {
		pass = cookie.getValue();
		password = true;
			}
		}
		if (email && password) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("GestioneProfilo.jsp");
			dispatcher.forward(request, response);
		}
	}
	%>


	<form action="EffettuaLogin" method="get">
		<div align="center" class="container">
			<div class="col-sm-12 border border-light rounded-lg"
				style="width: 40%" style="height:200px">

				<img align="top" src="images/b&b logo.png" alt="Logo BBChiara">

				<div class="form-group">
					<label for="email">Email address:</label> <input type="email"
						class="form-control" placeholder="Inserisci email" name=email
						id="email">
				</div>
				<div class="form-group">
					<label for="pwd">Password:</label> <input type="password"
						class="form-control" placeholder="Inserisci password"
						name=password id="pwd">
				</div>
				<div class="form-group form-check">
					<label class="form-check-label">Ricordami<input
						class="custom-control custom-checkbox" type="checkbox"
						id="ricordami">

					</label>
				</div>
				<button type="submit" class="btn btn-primary" id="access">Accedi</button>

				<a href="redirect?r=RecuperaPassword">Password dimenticata?</a> <a
					class="btn btn-success" href="redirect?r=Registrati" id="access">Registrati</a>


				<div align="center" class="col">
					<fb:login-button size="large" scope="public_profile,email"
						onlogin="ckeckLoginState()">Accedi con Facebook</fb:login-button>
					<div>
						<hr>
					</div>

					<div align="center" class="g-signin2" data-onsuccess="onSignIn"
						data-theme="dark">
						<button class="google btn" id="google">
							<i class="fa fa-google fa-fw"></i> Accedi con Google
						</button>
					</div>
				</div>

			</div>

		</div>
	</form>

</body>
</html>