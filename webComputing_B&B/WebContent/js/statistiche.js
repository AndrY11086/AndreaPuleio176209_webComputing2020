function stat() {

	if ($("#gestionePrenotazioni").is(":visible")) {
		$("#gestionePrenotazioni").toggle("slide");
	}

	if ($("#modificaProfilo").is(":visible")) {
		$("#modificaProfilo").toggle("slide");
	}

	if ($("#nuovaPrenotazione").is(":visible")) {
		$("#nuovaPrenotazione").toggle("slide");
	}

	$.ajax({
		type: "GET",
		url: "Statistiche",
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(json) {

			var month = json.mesi;

			var txt = "<div class='row'>" +
				"<div class='container'>" +
				"<div class='col'>" +
				"<h3>Statistiche Prenotazioni</h3>"
				+ "<hr>" +

				"<p>Prenotazioni Totali: " + json.prenotazioniTotali + "</p>" +
				"<p>Persone ospitate: " + json.personeOspitate + "</p>" +
				"<p>Numero Notti totali:" + json.numeroNottiTotali + "</p>" +
				"<p>Guadagno Totale:" + json.guadagniPrenotazioni + "&euro;</p>" +
				"<p>--->: " + json.incassoContanti + "&euro; Pagati presso la struttura</p>" +
				"<p>--->: " + json.incassoCC + "&euro; pagati tramite carta di credito</p>" +
				"<p>Pagamenti con c/c: " + json.pagatiConCarta + ",--- Pagati presso la struttura: pagatiContanti</p>" +
				"<p>Prezzo medio speso: " + json.prezzoMedioSpeso + "&euro;</p>" +
				"<hr>" +
				"</div>" +
				"<div class='col'>" +
				"<h3>Statistiche Escursioni</h3>" +
				"<hr>" +
				"<p>Numero persone coinvolte:" + json.numeroPersoneCoinvolte + "</p>" +
				"<p>Guadagni: " + json.guadagniEscursioni + "&euro;</p>" +
				"<p>Lido preferito dal: " + json.percLido + " %	degli utenti</p>" +
				"<p>Gita alle isole Eolie preferito dal: " + json.percEolie + "% degli utenti</p>" +
				"<p>Immersioni subacquee preferite dal:	" + json.percSub + "% degli utenti</p>" +
				"<p>Tracciolino di Palmi preferito dal:" + json.percTrac + "% degli utenti</p>" +
				"<hr></div></div></div>";


			txt += "<div class='col-sm-12' style='width: 100%; min-height: 450px;' id='chart_div1'></div>" +
				"<hr><div class='col-sm-12' style='width: 100%; min-height: 450px;'	id='chart_div2'></div>";

			txt += "<script> google.load('visualization', '1', {packages:['corechart']});\r\n"
				+ "google.setOnLoadCallback(drawChart1);\r\n"
				+ "function drawChart1() {\r\n"
				+ "  var data = google.visualization.arrayToDataTable([\r\n" + "    ['Escursione', 'Preferenze'],\r\n"
				+ "    ['Eolie',  " + json.eolie + "],\r\n" + "    ['Tracciolino',  " + json.trac + "],\r\n" + "    ['Sub',  "
				+ json.sub + "],\r\n" + "    ['Lido',  " + json.lido + "]\r\n" + "  ]);\r\n" + "\r\n" + "  var options = {\r\n"
				+ "    title: 'Escursioni',\r\n"
				+ "    hAxis: {title: 'Escursioni', titleTextStyle: {color: 'red'}}\r\n" + " };\r\n" + "\r\n"
				+ "var chart = new google.visualization.PieChart(document.getElementById('chart_div1'));\r\n"
				+ "  chart.draw(data, options);}\r\n"

				+ "google.setOnLoadCallback(drawChart2);"

				+ "function drawChart2() {\r\n" +
				"  var data = google.visualization.arrayToDataTable([\r\n" +
				"    ['Mese', 'Prenotazioni'],\r\n" +
				"    ['Gennaio',  " + month[0] + "],\r\n" +
				"    ['Febbraio',  " + month[1] + "],\r\n" +
				"    ['Marzo',  " + month[2] + "],\r\n" +
				"    ['Aprile',  " + month[3] + "],\r\n" +
				"    ['Maggio',  " + month[4] + "],\r\n" +
				"    ['Giugno',  " + month[5] + "],\r\n" +
				"    ['Luglio',  " + month[6] + "],\r\n" +
				"    ['Agosto',  " + month[7] + "],\r\n" +
				"    ['Settembre',  " + month[8] + "],\r\n" +
				"    ['Ottobre',  " + month[9] + "],\r\n" +
				"    ['Novembre',  " + month[10] + "],\r\n" +
				"    ['Dicembre',  " + month[11] + "]\r\n" +
				"  ]);"

				+ "var options = {\r\n" +
				"    title: 'Prenotazioni',\r\n" +
				"    hAxis: {title: 'Prenotazioni',  titleTextStyle: {color: '#333'}},\r\n" +
				"    vAxis: {minValue: 0}\r\n" +
				"  };"

				+ "var chart = new google.visualization.PieChart(document.getElementById('chart_div2'));"

				+ "chart.draw(data, options);}"

				+ "$(window).resize(function(){\r\n" +
				"  drawChart1();\r\n" +
				"  drawChart2();\r\n" +
				"});"
				+ "</script>";

							
				$("#statistiche").html(txt);
				//$('#statistiche').toggle("slide");

		}
	});

}
