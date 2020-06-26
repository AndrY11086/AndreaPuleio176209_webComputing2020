function contattaci(){
	
	if(controllaEMail() === true){
	
		if(confirm("conferma tutti i tuoi dati prima di inviarci la richiesta: \n"
				+$("#nome").val()+"\n"
				+$("#cognome").val()+"\n"
				+$("#telefono").val()+"\n"
				+$("#email").val()+"\n"
				+$("#testo").val()
				 )){
		
			$.ajax({
				type:"POST",
				url: "InviaMail",
				data:{
					"nome":$("#nome").val(),
					"cognome":$("#cognome").val(),
					"telefono":$("#telefono").val(),
					"email":$("#email").val(),
					"testo":$("#testo").val()
				},
				success:function(){
					document.getElementById("grazie").innerHTML = "Grazie per averci contattato! Risponderemo il prima possibile";
				}
			});
		}
	}
}

function controllaEMail(){
	
	var nome = document.getElementById('nome').value;
	var cognome = document.getElementById('cognome').value;
	var tel = document.getElementById('telefono').value;
	var email = document.getElementById('email').value;
	var commento = document.getElementById('testo').value;

	if (nome == "" || email == "" || cognome == "" || tel == "" || commento == "") {
	    alert("Tutti i campi sono obbligatori! quindi per favore inseriscili se vuoi essere ricontattato");
	    return false;
	}
	else if (verificaEmail(email) !== true) {
	    alert("L'indirizzo email non sembra corretto!");
	    return false;  
	}
	
	return true;
}

function verificaEmail(email) {
	  var expression = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	  return expression.test(email);
}