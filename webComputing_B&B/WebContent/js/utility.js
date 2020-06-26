//javascript per scrollspy "DOVE SIAMO"
$(document).ready(function() {
	$('body').scrollspy({
		target : ".navbar",
		offset : 50
	});

	$("#myScrollspy a").on('click', function(event) {
		if (this.hash !== "") {
			event.preventDefault();

			var hash = this.hash;

			$('html, body').animate({
				scrollTop : $(hash).offset().top
			}, 800, function() {

				window.location.hash = hash;
			});
		}
	});
});

/*function checkLogin() {


	var username = $("#email").val();
	var password = $("#pwd").val();
	var rememberMe = $("#ricordami").is(":checked");

	$.ajax({
		
		type : "GET",
		url : "EffettuaLogin",
		data : {
			"email" : username,
			"password" : password,
			"checkbox" : rememberMe
		},
		success : function(login) {

			
			
			 if(login) { 
				 location.replace("GestioneProfilo.jsp"); 
			}
			 else{ 
				 alert("Credenziali Errate");
				 location.replace("AreaRiservata.jsp"); 
			 
			 }
			
		}
	});
}*/

/*function switchLogin(loggato){
	
	if(loggato === true){
		alert("è true");
		location.replace("GestioneProfilo.jsp");
	}
	else{
		alert("Credenziali Errate: " + loggato);
		location.replace("AreaRiservata.jsp");
	}
}*/

/*function logout() {

	$.ajax({
		type : "GET",
		url : "EffettuaLogout",
		success : function() {
			location.replace("Home.jsp");
		}
	});

}*/
