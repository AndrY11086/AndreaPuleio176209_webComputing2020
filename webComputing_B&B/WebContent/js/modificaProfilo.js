function visualizzaProfilo() {
	$('#modificaProfilo').toggle("slide");

	if ($("#gestionePrenotazioni").is(":visible")) {
		$("#gestionePrenotazioni").toggle("slide");
	}

	if ($("#nuovaPrenotazione").is(":visible")) {
		$("#nuovaPrenotazione").toggle("slide");
	}
	$("#statistiche").empty();
	
	//alert("visualizza");

	/*	var tipo = "visualizza";
		
		 $.ajax({ 
			 type:"GET", 
			 url:"ModificaProfilo",
			 data:{
				 "tipo":tipo
			 },
			 contentType:"application/json; charset=utf-8",
			 dataType:"json",
			 success:function(data){
				 
				 var txt = "<div class= 'container mx-auto border border-light rounded-lg h-50 m-5' style='width:60%' style='height:200px'>";
					txt += "<label for=nome><b>Nome</b></label>";
					txt += "<input type='text' placeolder='NOME' nome='nome' required id='nome'></input>";
	
					txt += "<label for=cognome><b>Cognome</b></label>";
					txt += "<input type='text' placeolder='COGNOME'cognome='cognome' required id='cognome'>";
	
					txt += "<label for=email><b>Email</b></label>";
					txt += "<input type='email' placeolder='EMAIL' email='email' required id='email'>";
	
					txt += "<label for=telefono><b>Numero di Telefono</b></label>";
					txt += "<input type='text' placeolder='TELEFONO' telefono='telefono' required id='telefono'>";
	
					txt += "<label for=città><b>Città</b></label>";
					txt += "<input type='text' placeolder='CITTA' città='città' required id='city'>";
					
					txt += "<div align='center'><button align='left' class='btn btn-success' onclick='update()'> Salva Modifiche</button>";
					txt += "<button align='right' class= 'btn btn-danger'onclick='rimuoviProfilo()'> Rimuovi Profilo</button></div>";
					
					txt += "</div>";
					
					$("#schermataDestra").html(txt);
			
					
					document.getElementById("email").value = data.email;
					document.getElementById("nome").value = data.nome;
					document.getElementById("cognome").value = data.cognome;
					document.getElementById("telefono").value = data.numeroTelefono;
					document.getElementById("city").value = data.citta;
			 }
		});*/




}

function rimuoviProfilo() {

	var remove = "rimuovi";

	if (confirm("vuoi davvero cancellare il tuo profilo?")) {
		$.ajax({
			type: "GET",
			url: "ModificaProfilo",
			data: {
				"tipo": remove
			},
			success: function() {
				location.replace("Home.jsp");
				$.ajax({
					type: "GET",
					url: "EffettuaLogout"
				});
			}
		});
	}
}

function update() {

	var update = "update";

	if (confirm("Premi ok per confermare i tuoi dati: \n"
		+ "nome: " + $("#nome").val() + "\n"
		+ "cognome: " + $("#cognome").val() + "\n"
		+ "email: " + $("#email").val() + "\n"
		+ "numeroTelefono: " + $("#telefono").val() + "\n"
		+ "città: " + $("#city").val())) {

		$.ajax({
			type: "GET",
			url: "ModificaProfilo",
			data: {
				"nome": $("#nome").val(),
				"cognome": $("#cognome").val(),
				"email": $("#email").val(),
				"numeroTelefono": $("#telefono").val(),
				"città": $("#city").val(),
				"tipo": update
			},
			success: function() {
				location.reload();
			}

		});
	}
}