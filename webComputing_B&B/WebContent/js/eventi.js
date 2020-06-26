function loadEventi(thisId) {
	
	$.ajax({
		type: "GET",
		url: "EventiPage",
		data: {
			"id": thisId
		},
		success: function(eventi) {
			var json = JSON.parse(eventi);
			var txt = "";
			for (id in json) {
				txt += "<button id='" + id //evento.getId()
					+ "' class='btn btn-info btn-block btn-sm' onclick='mostraEvento(this.id)'>" + json[id].nomeEvento
					+ "</button>";

			}

			$("#buttons").html(txt);
		}
	});
}

function mostraEvento(id) {

	$.ajax({
		type: "GET",
		url: "EventiPage",
		data: {
			"id": id
		},
		success: function(txt) {
			$("#splitRight").load(location.href + " #splitRight");
		}
	});
}

function aggiungi() {

	//alert("aggiungi");

	var txt = "<div align='center' class='container'>" +

		"<div class='form-group'>" +
		"<label class='control-label col-sm-2' for='nome'>Nome</label>" +
		"<div class='col-sm-10'>" +
		"<input type='text' class='form-control' id='nome' placeholder='Inserisci Nome Evento' name='nome'></div>" +

		"<div class='form-group'>" +
		"<label class='control-label col-sm-2' for='durata'>Durata</label>" +
		"<div class='col-sm-10'>" +
		"<input type='text' class='form-control' id='durata' placeholder='Inserisci Durata Evento' name='durata'></div>" +

		"<div class='form-group'>" +
		"<label class='control-label col-sm-2' for='descrizione'>Descrizione</label>" +
		"<div class='col-sm-10'>" +
		"<input type='text' class='form-control' id='descrizione' placeholder='Inserisci Descrizione Evento' name='descrizione'></div>" +

		"<label for='tipo'>Tipo: </label>" +
		"<select class='custom-select mb-1' id='tipo'>" +
		"<optgroup>" +
		"<option value='Concerto'>Concerto</option>" +
		"<option value='Festa di Paese'>Festa di Paese</option>" +
		"<option value='Sagra'>Sagra</option>" +
		"<option value='Altro'>Altro</option>" +
		"</optgroup>" +
		"</select>" +

		"<p>data Evento: " +
		"<input type='date' id='dataEvento'>" +

		"</div><button class='btn btn-success' onclick='pubblica()'> Pubblica Evento</button>" +

		"</div>";

	$("#splitRight").html(txt);

}

function pubblica() {

	$.ajax({
		type: "POST",
		url: "PubblicaEvento",
		data: {
			"nome": $("#nome").val(),
			"durata": $("#durata").val(),
			"descrizione": $("#descrizione").val(),
			"tipo": $("#tipo").val(),
			"data": $("#dataEvento").val()
		},
		success: function() {
			$("#splitRight").html("<div class='container' align='center'><h1>Evento registrato correttamente</h1></div>");
			loadEventi();
			//location.reload();
		}
	});

}