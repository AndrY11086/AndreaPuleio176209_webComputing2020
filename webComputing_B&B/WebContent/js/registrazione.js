function registra(){
	
	var check = $("#accetta").is(":checked");
	
	var nome = $("#nome").val();
	var cognome = $("#cognome").val();
	var email = $("#email").val();
	var password = $("#password").val();
	var numeroTelefono = $("#numeroTelefono").val();
	var city = $("#city").val();
	var sessoM = $('input[name="optradio"]:checked').val();
	
	var conferma = $("#conferma").val();
	var correctField = false;
	var regexMail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	var regexPass = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
	
	
	
	
	if(nome.length == 0 || cognome.length == 0 || email.length == 0 || password.length == 0
			|| numeroTelefono.length == 0 || city.length == 0 || sessoM.length == 0){
		
		alert("bisogna inserire correttamente tutti i parametri");
	}
	else{
		correctField = true;
		
		if(!regexPass.test(password)){
			alert("La password deve contenere almeno 8 caratteri di cui almeno una lettera ed un numero.");
			correctField = false;
		}
		else if(password != conferma){
			alert("il campo password e conferma password non coincidono");
			correctField = false;
		}
		if(!regexMail.test(email.toLowerCase())){
			alert("indirizzo mail non valido");
			correctField = false;
		}
	}
	
	if(check === true && correctField === true)
	{
			$.ajax({
				
				type: "GET",
				url: "RegistraUtente",
				data: {
					"nome":nome,
					"cognome":cognome,
					"email":email,
					"password":password,
					"numeroTelefono":numeroTelefono,
					"city":city,
					"sessoM":sessoM,
					
				},
				success: function(){
				$.ajax({
					type:"GET",
					url:"redirect",
					data:{
						"r":"Home"
					}
				});
			
			}
			
		});
	}
	else{
		alert("devi accettare termini e condizioni");
	}
	
	alert("dati utente: " + nome + " " + cognome + " " + email + " " + password + " " + numeroTelefono + " " + city + " " + sessoM);
}