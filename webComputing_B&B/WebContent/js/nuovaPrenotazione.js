/*function prenota() {

	var txt = "<div id='section1' align='center' class= 'container mx-auto border border-light rounded-lg h-50 m-5' style='width:60%' style='height:200px'>";

	txt += "<label for='numeroOspiti'>Numero Ospiti: </label>";
	txt += "<select class='custom-select-sm' id='numeroOspiti'>";
	txt += "<optgroup>";
	txt += "<option value='1'>1</option>";
	txt += "<option value='2'>2</option>";
	txt += "<option value='3'>3</option>";
	txt += "<option value='4'>4</option>";
	txt += "<option value='5'>5</option>";
	txt += "<option value='6'>6</option>";
	txt += "<option value='7'>7</option>";
	txt += "<option value='8'>8</option>";
	txt += "<option value='9'>9</option>";
	txt += "<option value='10'>10</option>";
	txt += "<option value='11'>11</option>";
	txt += "<option value='12'>12</option>";
	txt += "</optgroup>";
	txt += "</select>";

	txt += "<label for='numeroStanze'>Numero Stanze: </label>";
	txt += "<select class='custom-select-sm' id='numeroStanze'>";
	txt += "<optgroup>";
	txt += "<option value='1'>1</option>";
	txt += "<option value='2'>2</option>";
	txt += "<option value='3'>3</option>";
	txt += "<option value='4'>4</option>";
	txt += "<option value='5'>5</option>";
	txt += "</optgroup>";
	txt += "</select>";

	txt += "<p>data di Arrivo: ";
	txt += "<input style='width:200px' type='date' id='arrivo'>";
	txt += "data di Partenza: ";
	txt += "<input style='width:200px' type='date' id='partenza'></p>";

	txt += "<button id='fase1-2' class='btn btn-success' onclick='fase2()'>Avanti</button>";
	txt += "</div>";

	
	
	$("#nuovaPrenotazione").html(txt);
	$("#numeroStanze").prop('disabled', true);
	$("#fase1-2").prop('disabled', true);
	$("#arrivo").prop('disabled', true);
	$("#partenza").prop('disabled', true);

	$(document).ready(function() {
		$("#numeroOspiti").click(function() {
			$("#numeroStanze").prop('disabled', false);
		});
	});

	$(document).ready(function() {
		$("#numeroStanze").click(function() {
			$("#arrivo").prop('disabled', false);
		});
	});

	$(document).ready(function() {
		$("#arrivo").change(function() {
			$("#partenza").prop('disabled', false);
		});
	});

	$(document)
			.ready(
					function() {
						$("#partenza")
								.change(
										function() {
											if ($("#partenza").val() > $(
													"#arrivo").val()) {
												$("#fase1-2").prop('disabled',
														false);
											} else {
												alert("devi inserire una data successiva a quella di arrivo"
														+ $("#partenza").val());
												$("#fase1-2").prop('disabled',
														true);
											}
										});
					});
}

function fase2() {

	var txt = "<div id='section2' class='container mx-auto border border-light rounded-lg h-50 m-5' style='width:60%' style='height:200px'>";

	txt += "<div class='custom-control custom-checkbox container mx-auto border border-light rounded-lg h-50 m-5' style='width:60%' style='height:200px''>";
	txt += "<input type='checkbox' class='custom-control-input' id='singolaRoom' name='singolaRoom'>";
	txt += "<label class='custom-control-label' for='singolaRoom'>Singola: </label> <img src='images/LeCamere/singola.jpg'></div>";

	if ($("#numeroOspiti").val() > 1) {

		txt += "<div class='custom-control custom-checkbox container mx-auto border border-light rounded-lg h-50 m-5' style='width:60%' style='height:200px''>";
		txt += "<input type='checkbox' class='custom-control-input' id='matrimonialeRoom' name='matrimonialeRoom'>";
		txt += "<label class='custom-control-label' for='matrimonialeRoom'>Matrimoniale: </label><img src='images/LeCamere/matrimoniale.jpg'></div>";

		txt += "<div class='custom-control custom-checkbox container mx-auto border border-light rounded-lg h-50 m-5' style='width:60%' style='height:200px''>";
		txt += "<input type='checkbox' class='custom-control-input' id='doppiaRoom' name='doppiaRoom'>";
		txt += "<label class='custom-control-label' for='doppiaRoom'>Doppia con letti separati: </label><img src='images/LeCamere/doppia.jpg'></div>";

		if ($("#numeroOspiti").val() == 3 || $("#numeroOspiti").val() == 5) {

			txt += "<div class='custom-control custom-checkbox container mx-auto border border-light rounded-lg h-50 m-5' style='width:60%' style='height:200px''>";
			txt += "<input type='checkbox' class='custom-control-input' id='triplaRoom' name='triplaRoom'>";
			txt += "<label class='custom-control-label' for='triplaRoom'>Tripla: </label><img src='images/LeCamere/matrimoniale.jpg'></div>";
		}

		txt += "<div class='custom-control custom-checkbox container mx-auto border border-light rounded-lg h-50 m-5' style='width:60%' style='height:200px''>";
		txt += "<input type='checkbox' class='custom-control-input' id='suiteRoom' name='suiteRoom'>";
		txt += "<label class='custom-control-label' for='suiteRoom'>Suite: </label><img src='images/LeCamere/suiteChiara.jpg'></div>";

	}
	txt += "<p id='room'>Stanze da selezionare: </p>";
	txt += "<button id='previous' class='btn btn-danger' onclick='before()'>Indietro</button>";
	txt += "<button id='fase2-3' class='btn btn-success' onclick='fase3()'>Avanti</button>";
	txt += "</div>";

	txt += "</div>";

	$("#nuovaPrenotazione").append(txt);

	$("#fase1-2").prop('disabled', true);
	$("#fase2-3").prop('disabled', true);
	var stanze = $("#numeroStanze").val();
	$("#room").append(stanze);

	$(document).on('change', '.custom-control', function() {
		if (stanze > 0) {
			stanze -= 1;
			$("#room").text("Stanze da selezionare: " + stanze);
		}
		if (stanze == 0) {
			$("#fase2-3").prop('disabled', false);
			$(".custom-control").prop('disabled', true);
		}
	});
}

function fase3() {

	var prezzo = calcolaPrezzo();

	var txt = "<div id='section3' class='container mx-auto border border-light rounded-lg h-50 m-5' style='width:60%' style='height:200px'>";

	txt += "<div class='container mx-auto border border-light rounded-lg h-50 m-5' style='width:60%' style='height:200px''>";
	txt += "<p>Servizi aggiuntivi:</p>";
	txt += "<p>Colazione: </p>";

	txt += "<div class='custom-control custom-radio'>";
	txt += "<input type='radio' checked='checked' class='custom-control-input' id='loco' name='colazione'>";
	txt += "<label class='custom-control-label' for='loco'>in loco (compreso nel prezzo)</label>";
	txt += "</div>";

	txt += "<div class='custom-control custom-radio'>";
	txt += "<input type='radio' class='custom-control-input' id='bar' name='colazione'>";
	txt += "<label class='custom-control-label' for='bar'>Presso bar vicino 3€ al giorno a camera </label>";
	txt += "</div>";

	txt += "<div class='custom-control custom-radio'>";
	txt += "<input type='radio' class='custom-control-input' id='barCamera' name='colazione'>";
	txt += "<label class='custom-control-label' for='barCamera'>Servita in camera dal bar 5€ al giorno a camera</label>";
	txt += "</div>";

	txt += "<div class='container mx-auto border border-light rounded-lg h-50 m-5' style='width:60%' style='height:200px''>";
	txt += "<p>Altri servizi:</p>";
	txt += "<div class='custom-control custom-checkbox'>";
	txt += "<input type='checkbox' class='custom-control-input' id='lavatrice' name='lavatrice'>";
	txt += "<label class='custom-control-label' for='lavatrice'>Lavatrice: 5€ al giorno sul totale</label></div>";

	txt += "<div class='custom-control custom-checkbox'>";
	txt += "<input type='checkbox' class='custom-control-input' id='ac' name='ac'>";
	txt += "<label class='custom-control-label' for='ac'>Aria Condizionata: 5€ al giorno sul totale </label></div>";

	txt += "<div class='custom-control custom-checkbox'>";
	txt += "<input type='checkbox' class='custom-control-input' id='barAll-in' name='barAll-in'>";
	txt += "<label class='custom-control-label' for='barAll-in'>Mini-bar All Inclusive: 5€ al giorno a persona</label></div></div>";

	txt += "<h3>Dati di riepilogo:</h3>";

	txt += "<div class='container mx-auto border border-light rounded-lg h-50 m-5' style='width:60%' style='height:200px'''>";
	txt += "<p id='nomeOspite'></p>";
	txt += "<p id='dataDiArrivo'></p>";
	txt += "<p id='dataDiPartenza'></p>";
	txt += "<p id='nRooms'></p>";
	txt += "<p id='nPax'></p>";
	txt += "<p id='price'></p>";
	txt += "</div>";

	txt += "<button id='previous' class='btn btn-danger' onclick='before()'>Indietro</button>";
	txt += "<button id='previous' class='btn btn-success' onclick='fase4()'>Avanti</button>";

	txt += "</div>";

	$("#nuovaPrenotazione").append(txt);

	var prezzoConAggiunta = prezzo;

	$("#nomeOspite").text("Nome Ospite: Andrea");
	$("#dataDiArrivo").text("Data di Arrivo: " + $("#arrivo").val());
	$("#dataDiPartenza").text("Data di Partenza: " + $("#partenza").val());
	$("#nRooms").text("Numero di Stanze: " + $("#numeroStanze").val());
	$("#nPax").text("Numero di Ospiti: " + $("#numeroOspiti").val());
	$("#price").text("Prezzo: " + prezzoConAggiunta);

	$(document).ready(
			function() {

				$("#loco").change(function() {
					prezzoConAggiunta = prezzo;
					$("#price").text("Prezzo: " + prezzoConAggiunta);
				});

				$("#bar").change(
						function() {
							prezzoConAggiunta = prezzo + 3
									* calcolaNumeroNotti()
									* $("#numeroStanze").val();
							$("#price").text("Prezzo: " + prezzoConAggiunta);
						});

				$("#barCamera").change(
						function() {
							prezzoConAggiunta = prezzo + 5
									* calcolaNumeroNotti()
									* $("#numeroStanze").val();
							$("#price").text("Prezzo: " + prezzoConAggiunta);
						});

				$("#lavatrice").change(function() {
					if ($("#lavatrice").prop("checked") == true) {
						prezzoConAggiunta += 5 * calcolaNumeroNotti();
					} else {
						prezzoConAggiunta -= 5 * calcolaNumeroNotti();
					}
					$("#price").text("Prezzo: " + prezzoConAggiunta);
				});

				$("#ac").change(function() {
					if ($("#ac").prop("checked") == true) {
						prezzoConAggiunta += 5 * calcolaNumeroNotti();
					} else {
						prezzoConAggiunta -= 5 * calcolaNumeroNotti();
					}
					$("#price").text("Prezzo: " + prezzoConAggiunta);
				});
				$("#barAll-in").change(
						function() {
							if ($("#barAll-in").prop("checked") == true) {
								prezzoConAggiunta += 5 * calcolaNumeroNotti()
										* $("#numeroOspiti").val();
							} else {
								prezzoConAggiunta -= 5 * calcolaNumeroNotti()
										* $("#numeroOspiti").val();
							}
							$("#price").text("Prezzo: " + prezzoConAggiunta);

						});
			});
}
function fase4() {

	var txt = "<div id='section4' class='container mx-auto border border-light rounded-lg h-50 m-5' style='width:60%' style='height:200px'>";

	txt += "<div class='custom-control custom-radio'>";
	txt += "<input type='radio' class='custom-control-input' id='struttura' name='pagamento'>";
	txt += "<label class='custom-control-label' for='struttura'>Pagamento presso la struttura</label>";
	txt += "</div>";

	txt += "<div class='custom-control custom-radio'>";
	txt += "<input type='radio' class='custom-control-input' id='carta' name='pagamento'>";
	txt += "<label class='custom-control-label' for='carta'>Pagamento con carta di credito </label>";
	txt += "</div>";

	txt += "<button id='previous' class='btn btn-danger' onclick='before()'>Annulla</button>";
	txt += "<button id='previous' class='btn btn-success' onclick='paga()'>Paga</button>";

	txt += "</div>";

	$("#nuovaPrenotazione").append(txt);
}

function paga() {

	if ($("#struttura").prop("checked") == true) {

		savePrn("Presso la Struttura");
	} else {
		
		var prezzo = $("#price").text();
		var price = prezzo.split(" ");
		var txt = "<form action='/webComputing_BB/acceptpaymentrequest' method='POST'>";
		txt += "<script src='https://checkout.stripe.com/checkout.js' class='stripe-button'";
		txt += "data-key='pk_test_FUOi8YhwjaeYtXQVUHJunWNr00DWKPtsln'";
		txt += "data-amount= " + price[1] + "00";
		txt += "data-name='B&B Chiara'";
		txt += "data-description='Pagamento'";
		txt += "data-image='https://stripe.com/img/documentation/checkout/marketplace.png'";
		txt += "data-locale='auto'";
		txt += "data-currency='eur'>";
		txt += "</script></form>";
		
		
		$("#nuovaPrenotazione").append(txt);
		
		$.ajax({
			type: "POST",
			url: "NuovaPrenotazione",
			data:{
				"prezzo" : $("#price").text()
			},
			success : function(){
				savePrn("Pagato con carta di credito");
			}
		});
		
	}
}
function savePrn(tipo) {

	var notti = calcolaNumeroNotti();
	
	$.ajax({
		type : "GET",
		url : "NuovaPrenotazione",
		data : {
			"dataArrivo" : $("#arrivo").val(),
			"dataPartenza" : $("#partenza").val(),
			"numeroPersone" : $("#numeroOspiti").val(),
			"prezzo" : $("#price").text(),
			"pagamento":tipo,
			"notti":notti,
			"lavatrice": $("#lavatrice").prop("checked"),
			"a/c": $("#ac").prop("checked"),
			"miniBar": $("#barAll-in").prop("checked"),
			"loco": $("#loco").prop("checked"),
			"bar": $("#bar").prop("checked"),
			"barCamera": $("#barCamera").prop("checked")
		},
		
		success:function(){
				window.open("Voucher");
		}
	});
}

function calcolaPrezzo() {
	var prezzoStanza = calcolaPrezzoStanza();
	var notti = calcolaNumeroNotti();

	return prezzoStanza * notti;

}

function calcolaPrezzoStanza() {

	var prezzoStanzeSelezionate = 0;

	if ($("#suiteRoom").is(":checked")) {
		if ($("#numeroOspiti").val() < 3) {
			prezzoStanzeSelezionate += 60;
		} else {
			prezzoStanzeSelezionate += 40 * $("#numeroOspiti").val();
		}
	} else {
		prezzoStanzeSelezionate += 25 * $("#numeroOspiti").val();
	}
	return prezzoStanzeSelezionate;
}

function calcolaNumeroNotti() {
	var nNotti = new Date($("#partenza").val()).getTime()
			- new Date($("#arrivo").val()).getTime();

	nNotti /= (1000 * 3600 * 24);

	return nNotti;
}
*/