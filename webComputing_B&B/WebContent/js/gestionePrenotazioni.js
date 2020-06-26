function visualizzaPrenotazioni(id) {

	$.ajax({
		type: "GET",
		url: "GestionePrenotazione",
		data: {
			"id": id
		},
		success: function(jsonObject) {
			
			var txt = "<h3 align='center'><em> Lista Prenotazioni: </em></h3>";
			var json = JSON.parse(jsonObject);
			
			txt += "<div id='accordion'>";
			var i = 0;
			for(i in json){
				
				txt += "<div align='center' class='card col-sm-8 container' style='background-color: #942536'>"
					+ "<div class='card-header' >"
					+ "<a style='color:yellow' class='card-link' data-toggle='collapse' href='#collapse"
					+ json[i].id + "'>" + "numero prenotazione: "
					+ json[i].id + "  arrivo: " + json[i].dataArrivo + " partenza: "
					+ json[i].dataPartenza + "</a></div>" + "<div id='collapse" + json[i].id
					+ "' class='collapse' data-parent='#accordion'>" + "<div class='card-body'>"
					+ "<p style='color:yellow' id='idPrn'>id: " + json[i].id
					+ "<p style='color:yellow'> Ospite: " + json[i].nomeOspite + "</p>"
					+ "<p style='color:yellow'> Data di arrivo: " + json[i].dataArrivo + " Data di partenza: "
					+ json[i].dataPartenza + "</p>" + "<p style='color:yellow'> numero di Ospiti: "
					+ json[i].nPersone + " numero notti: " +json[i].nNotti + "</p>"
					+ "<p style='color:yellow'> prezzo: " + json[i].prezzo + " &euro;" + " pagamento "
					+ json[i].pagamento + "</p>"
					+ "<button class='btn btn-danger' onclick='rimuoviPrenotazione()'>Rimuovi Prenotazione</button>"
					+ "</div></div></div></div></div><hr>";
					
					i++;
					
			}
			
			txt += "</div>";
			
			$("#gestionePrenotazioni").html(txt);
			$("#gestionePrenotazioni").toggle("slide");

			if ($("#modificaProfilo").is(":visible")) {
				$("#modificaProfilo").toggle("slide");
			}

			if ($("#nuovaPrenotazione").is(":visible")) {
				$("#nuovaPrenotazione").toggle("slide");
			}
			$("#statistiche").empty();
		}
	});

}

function rimuoviPrenotazione() {

	if (confirm("vuoi davvero cancellare questa prenotazione?")) {
		$.ajax({
			type: "POST",
			url: "RimuoviPrenotazione",
			data: {
				"id": $("#idPrn").text()
			},
			success: function() {
				visualizzaPrenotazioni();
			}
		});
	}
}