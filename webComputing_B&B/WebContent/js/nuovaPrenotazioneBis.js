var prezzoConAggiunta;

function prenotazioneBis() {

	var txt = "<div class='container-fluid'>" +
		"<div class='row justify-content-center mt-0'>" +
		"<div class='col-11 col-sm-9 col-md-7 col-lg-6 text-center p-0 mt-3 mb-2'>" +
		"<div class='card px-0 pt-4 pb-0 mt-3 mb-3'>";

	txt += "<h2><strong>Prenota</strong></h2>" +
		"<h6>Compila il form e vai allo step successivo</h6>" +
		"<div class='row'>";

	txt += "<div class='col-md-12 mx-0'>" +
		"<form id='msform'>" +
		"<ul id='progressbar'>" +
		"<li class='active fa fa-table' id='account'><strong>Dati</strong></li>" +
		"<li class='fa fa-bed' id='personal'><strong>Tipologia Stanza</strong></li>" +
		"<li class='fa fa-credit-card' id='payment'><strong>Pagamento</strong></li>" +
		"<li class='fa fa-check-circle' id='confirm'><strong>Finish</strong></li>" +
		"</ul>";
	//inizio dati preliminari
	txt += "<fieldset>" +
		"<div class='form-card'>" +
		"<h2 class='fs-title'>Dati preliminari:</h2>";

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

	txt += "<p style='color:black'>data di Arrivo: ";
	txt += "<input style='width:200px' type='date' id='arrivo'></p>";
	txt += "<p style='color:black'>data di Partenza: ";
	txt += "<input style='width:200px' type='date' id='partenza'></p>";

	txt += "</div> <input type='button' name='next' class='action-button' value='Avanti' onclick='roomAndService()' />" +
		"</fieldset>";
	//fine dati preliminari

	txt += "<fieldset id='roomService'></fieldset>";
	txt += "<fieldset id='payway'></fieldset>";


	txt += "<fieldset>" +
		"<div class='form-card'>" +
		"<h2 class='fs-title text-center'>Fatto !</h2> <br><br>" +
		"<div class='row justify-content-center'>" +
		"<div class='col-3'> <img src='https://img.icons8.com/color/96/000000/ok--v2.png' class='fit-image'> </div>" +
		"</div> <br><br>" +
		"<div class='row justify-content-center'>" +
		"<div class='col-7 text-center'>" +
		"<h5>Prenotazione effettuata con successo</h5>" +
		"</div></div></div>" +
		"</fieldset>";

	txt += "</form></div></div></div></div></div></div>";

	$("#nuovaPrenotazione").html(txt);
	$("#nuovaPrenotazione").toggle("slide");

	if ($("#gestionePrenotazioni").is(":visible")) {
		$("#gestionePrenotazioni").toggle("slide");
	}

	if ($("#modificaProfilo").is(":visible")) {
		$("#modificaProfilo").toggle("slide");
	}
	$("#statistiche").empty();
	style();

	//////////////////////////////////////////// inizio funzione javascript pannello/////////////////////////////////////////////////////////////////////
	$(document).ready(function() {

		var current_fs, next_fs, previous_fs; //fieldsets
		var opacity;

		$(".action-button").click(function() {

			current_fs = $(this).parent();
			next_fs = $(this).parent().next();

			//Add Class Active
			$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

			//show the next fieldset
			next_fs.show();
			//hide the current fieldset with style
			current_fs.animate({ opacity: 0 }, {
				step: function(now) {
					// for making fielset appear animation
					opacity = 1 - now;

					current_fs.css({
						'display': 'none',
						'position': 'relative'
					});
					next_fs.css({ 'opacity': opacity });
				},
				duration: 600
			});
		});

		$(".previous").click(function() {

			current_fs = $(this).parent();
			previous_fs = $(this).parent().prev();

			//Remove class active
			$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");

			//show the previous fieldset
			previous_fs.show();

			//hide the current fieldset with style
			current_fs.animate({ opacity: 0 }, {
				step: function(now) {
					// for making fielset appear animation
					opacity = 1 - now;

					current_fs.css({
						'display': 'none',
						'position': 'relative'
					});
					previous_fs.css({ 'opacity': opacity });
				},
				duration: 600
			});
		});

		$('.radio-group .radio').click(function() {
			$(this).parent().find('.radio').removeClass('selected');
			$(this).addClass('selected');
		});

		$(".submit").click(function() {
			return false;
		})

	});
	////////////////////////////////////////////fine funzione javascript pannello/////////////////////////////////////////////////////////////////////	//fine funzione javascript

	////////////////////////////////////////////inizio funzione javascript prima facciata/////////////////////////////////////////////////////////////////////

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

	$(document).ready(function() {
		$("#partenza").change(function() {
			if ($("#partenza").val() > $(
				"#arrivo").val()) {
				$("#fase1-2").prop('disabled', false);
			} else {
				alert("devi inserire una data successiva a quella di arrivo"
					+ $("#partenza").val());
				$("#fase1-2").prop('disabled', true);
			}
		});
	});
	/////////////////////////////////////////////////////////fine funzioni javascript prima schermata////////////////////////////////////////////////////

	/////////////////////////////////////////////////////////inizio funzioni javascript seconda schermata////////////////////////////////////////////////////

	/*var stanze = $("#numeroStanze").val();
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
	});*/

	//SERVIZI EXTRA
	//var prezzo = calcolaPrezzo();
	//var prezzoConAggiunta = prezzo;

	/*$("#nomeOspite").text("Nome Ospite: Andrea");
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
			});*/

	/////////////////////////////////////////////////////////fine funzioni javascript seconda schermata////////////////////////////////////////////////////

}
function roomAndService() {
	//inizio tipologia camera e servizi extra
	// "<fieldset>"+
	var txt = "<div class='form-card'>" +
		"<h2 class='fs-title'>Tipologia stanza e Servizi Extra</h2>";

	txt += "<div class='custom-control custom-checkbox container mx-auto border border-light rounded-lg h-50 m-5' style='width:60%' style='height:200px''>";
	txt += "<input type='checkbox' class='custom-control-input' id='singolaRoom' name='singolaRoom'>";
	txt += "<label class='custom-control-label' for='singolaRoom'>Singola: </label><img src='images/LeCamere/singola.jpg'></div>";

	//alert("numeroOspiti: " + $("#numeroOspiti").val());

	if ($('#numeroOspiti').val() > 1) {

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
	txt += "<h6 id='room'>Stanze da selezionare: </h6>";

	txt += "<div><p style='color:black'>Servizi aggiuntivi:</p>";
	txt += "<p style='color:black'>Colazione: </p>";

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
	txt += "</div></div>";

	txt += "<div class='container mx-auto border border-light rounded-lg h-50 m-5' style='width:60%' style='height:200px''>";
	txt += "<p syle='color:black'>Altri servizi:</p>";
	txt += "<div class='custom-control custom-checkbox'>";
	txt += "<input type='checkbox' class='custom-control-input' id='lavatrice' name='lavatrice'>";
	txt += "<label class='custom-control-label' for='lavatrice'>Lavatrice: 5€ al giorno sul totale</label></div>";

	txt += "<div class='custom-control custom-checkbox'>";
	txt += "<input type='checkbox' class='custom-control-input' id='ac' name='ac'>";
	txt += "<label class='custom-control-label' for='ac'>Aria Condizionata: 5€ al giorno sul totale </label></div>";

	txt += "<div class='custom-control custom-checkbox'>";
	txt += "<input type='checkbox' class='custom-control-input' id='barAll-in' name='barAll-in'>";
	txt += "<label class='custom-control-label' for='barAll-in'>Mini-bar All Inclusive: 5€ al giorno a persona</label></div></div>";



	txt += "<h2 id='price'>Prezzo: </h2>";

	txt += "</div> <input type='button' name='previous' class='previous action-button-previous' value='Precedente' />" +
		"<input type='button' name='next' class='action-button' value='Avanti' onclick='payWay()' />";//+
	//"</fieldset>";
	//fine di stanze e servizi

	$("#roomService").html(txt);

	//inizio javascript servizi extra

	var stanze = $("#numeroStanze").val();
	$("#room").append(stanze);

	$(document).on('change', '.custom-control', function() {
		if (stanze > 0) {
			stanze -= 1;
			$("#room").text("Stanze da selezionare: " + stanze);
		}
		if (stanze == 0) {
			$(".custom-control").prop('disabled', true);
		}
	});

	var prezzo = calcolaPrezzo();
	$("#price").text("Prezzo: " + prezzo);

	prezzoConAggiunta = prezzo;

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

	//fine javascript servizi extra

	////////////////////////////////////////////inizio funzione javascript pannello/////////////////////////////////////////////////////////////////////
	$(document).ready(function() {

		var current_fs, next_fs, previous_fs; //fieldsets
		var opacity;

		$(".action-button").click(function() {

			current_fs = $(this).parent();
			next_fs = $(this).parent().next();

			//Add Class Active
			$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

			//show the next fieldset
			next_fs.show();
			//hide the current fieldset with style
			current_fs.animate({ opacity: 0 }, {
				step: function(now) {
					// for making fielset appear animation
					opacity = 1 - now;

					current_fs.css({
						'display': 'none',
						'position': 'relative'
					});
					next_fs.css({ 'opacity': opacity });
				},
				duration: 600
			});
		});

		$(".previous").click(function() {

			current_fs = $(this).parent();
			previous_fs = $(this).parent().prev();

			//Remove class active
			$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");

			//show the previous fieldset
			previous_fs.show();

			//hide the current fieldset with style
			current_fs.animate({ opacity: 0 }, {
				step: function(now) {
					// for making fielset appear animation
					opacity = 1 - now;

					current_fs.css({
						'display': 'none',
						'position': 'relative'
					});
					previous_fs.css({ 'opacity': opacity });
				},
				duration: 600
			});
		});

		$('.radio-group .radio').click(function() {
			$(this).parent().find('.radio').removeClass('selected');
			$(this).addClass('selected');
		});

		$(".submit").click(function() {
			return false;
		})

	});
	////////////////////////////////////////////fine funzione javascript pannello/////////////////////////////////////////////////////////////////////	//fine funzione javascript

}

function payWay() {
	//inizio sezione pagamento
	//txt += "<fieldset>"+
	var prezzoConAggiunta = $("#price").text();

	var txt = "<div class='form-card'>" +
		"<h2 class='fs-title'>Modalità di pagamento</h2>";

	txt += "<h4>Dati di riepilogo:</h4>";

	txt += "<div class='container mx-auto border border-light rounded-lg h-50 m-5' style='width:60%' style='height:200px'''>";
	txt += "<h6 id='nomeOspite'></h6>";
	txt += "<h6 id='dataDiArrivo'></h6>";
	txt += "<h6 id='dataDiPartenza'></h6>";
	txt += "<h6 id='nRooms'></h6>";
	txt += "<h6 id='nPax'></h6>";
	txt += "<h6 id='finalPrice'></h6>";
	txt += "</div>";

	txt += "<div class='custom-control custom-radio'>";
	txt += "<input type='radio' class='custom-control-input' id='struttura' name='pagamento'>";
	txt += "<label class='custom-control-label' for='struttura'>Pagamento presso la struttura</label>";
	txt += "</div>";

	txt += "<div class='custom-control custom-radio'>";
	txt += "<input type='radio' class='custom-control-input' id='carta' name='pagamento'>";
	txt += "<label class='custom-control-label' for='carta'>Pagamento con carta di credito </label>";
	txt += "</div>";

	txt += "</div> <div id='paycard'></div><input id='bottone' type='button' name='previous' class='previous action-button-previous' value='Precedente' />" +
		"<input id='bottone' type='button' name='next' class='action-button' value='Avanti' onclick='paga()'/>";//+
	//"</fieldset>";
	//fine sezione pagamento

	$("#payway").html(txt);

	//$("#nomeOspite").text("Nome Ospite: ${cliente.getCognome() } ${cliente.getNome() }");
	$("#dataDiArrivo").text("Data di Arrivo: " + $("#arrivo").val());
	$("#dataDiPartenza").text("Data di Partenza: " + $("#partenza").val());
	$("#nRooms").text("Numero di Stanze: " + $("#numeroStanze").val());
	$("#nPax").text("Numero di Ospiti: " + $("#numeroOspiti").val());
	$("#finalPrice").text(prezzoConAggiunta);

	//////////////////////////////////////////inizio funzione javascript pannello/////////////////////////////////////////////////////////////////////
	$(document).ready(function() {

		var current_fs, next_fs, previous_fs; //fieldsets
		var opacity;

		$(".action-button").click(function() {

			current_fs = $(this).parent();
			next_fs = $(this).parent().next();

			//Add Class Active
			$("#progressbar li").eq($("fieldset").index(next_fs)).addClass("active");

			//show the next fieldset
			next_fs.show();
			//hide the current fieldset with style
			current_fs.animate({ opacity: 0 }, {
				step: function(now) {
					// for making fielset appear animation
					opacity = 1 - now;

					current_fs.css({
						'display': 'none',
						'position': 'relative'
					});
					next_fs.css({ 'opacity': opacity });
				},
				duration: 600
			});
		});

		$(".previous").click(function() {

			current_fs = $(this).parent();
			previous_fs = $(this).parent().prev();

			//Remove class active
			$("#progressbar li").eq($("fieldset").index(current_fs)).removeClass("active");

			//show the previous fieldset
			previous_fs.show();

			//hide the current fieldset with style
			current_fs.animate({ opacity: 0 }, {
				step: function(now) {
					// for making fielset appear animation
					opacity = 1 - now;

					current_fs.css({
						'display': 'none',
						'position': 'relative'
					});
					previous_fs.css({ 'opacity': opacity });
				},
				duration: 600
			});
		});

		$('.radio-group .radio').click(function() {
			$(this).parent().find('.radio').removeClass('selected');
			$(this).addClass('selected');
		});

		$(".submit").click(function() {
			return false;
		})

	});
	////////////////////////////////////////////fine funzione javascript pannello/////////////////////////////////////////////////////////////////////	//fine funzione javascript


}

function paga() {

	if ($("#struttura").prop("checked") == true) {
		var notti = calcolaNumeroNotti();
		savePrn("Presso la Struttura", notti);
	} else {

		var prezzo = $("#finalPrice").text();
		var price = prezzo.split(" ");

		//alert("prezzo: " + prezzo + " price: " + price[1]);

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


		var nNotti = calcolaNumeroNotti();
		$("#paycard").html(txt);
		$("input#bottone").remove();
		$.ajax({
			type: "POST",
			url: "NuovaPrenotazione",
			data: {
				"prezzo": $("#price").text(),
			},
			success: function() {
				savePrn("Pagato con carta di credito", nNotti);
			}
		});
	}
}
function savePrn(tipo, notti) {

	//var notti = calcolaNumeroNotti();

	$.ajax({
		type: "GET",
		url: "NuovaPrenotazione",
		data: {
			"dataArrivo": $("#arrivo").val(),
			"dataPartenza": $("#partenza").val(),
			"numeroPersone": $("#numeroOspiti").val(),
			"prezzo": $("#price").text(),
			"pagamento": tipo,
			"notti": notti,
			"lavatrice": $("#lavatrice").prop("checked"),
			"a/c": $("#ac").prop("checked"),
			"miniBar": $("#barAll-in").prop("checked"),
			"loco": $("#loco").prop("checked"),
			"bar": $("#bar").prop("checked"),
			"barCamera": $("#barCamera").prop("checked")
		},

		success: function() {
			
			if (tipo === "Presso la Struttura") {
				window.open("Voucher");
			}
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

function style() {

	var style = "* {margin: 0;   padding: 0}" +

		"html {    height: 100%}" +

		"#msform {    text-align: center;    position: relative;    margin-top: 20px}" +

		"#msform fieldset .form-card {    background: white;    border: 0 none;    border-radius: 0px;    box-shadow: 0 2px 2px 2px rgba(0, 0, 0, 0.2);    padding: 20px 40px 30px 40px;    box-sizing: border-box;    width: 94%;    margin: 0 3% 20px 3%;    position: relative}" +


		"#msform fieldset {    background: white;    border: 0 none;    border-radius: 0.5rem;    box-sizing: border-box;    width: 100%;    margin: 0;    padding-bottom: 20px;    position: relative}" +

		"#msform fieldset:not(:first-of-type) {    display: none}" +

		"#msform fieldset .form-card {    text-align: left;    color: #9E9E9E}" +

		"#msform input,#msform textarea {    padding: 0px 8px 4px 8px;    border: none;    border-bottom: 1px solid #ccc;    border-radius: 0px;    margin-bottom: 25px;    margin-top: 2px;    width: 100%;    box-sizing: border-box;    font-family: montserrat;    color: #2C3E50;    font-size: 16px;    letter-spacing: 1px}" +

		"#msform input:focus,#msform textarea:focus {    -moz-box-shadow: none !important;    -webkit-box-shadow: none !important;    box-shadow: none !important;    border: none;    font-weight: bold;    border-bottom: 2px solid skyblue;    outline-width: 0}" +


		"#msform .action-button {    width: 100px;    background: skyblue;    font-weight: bold;    color: white;    border: 0 none;    border-radius: 0px;    cursor: pointer;    padding: 10px 5px;    margin: 10px 5px}" +

		"#msform .action-button:hover,#msform .action-button:focus {    box-shadow: 0 0 0 2px white, 0 0 0 3px skyblue}" +

		"#msform .action-button-previous {    width: 100px;    background: #616161;    font-weight: bold;    color: white;    border: 0 none;    border-radius: 0px;    cursor: pointer;    padding: 10px 5px;    margin: 10px 5px}" +

		"#msform .action-button-previous:hover,#msform .action-button-previous:focus {    box-shadow: 0 0 0 2px white, 0 0 0 3px #616161}" +

		"select.list-dt {    border: none;    outline: 0;    border-bottom: 1px solid #ccc;    padding: 2px 5px 3px 5px;    margin: 2px}" +

		"select.list-dt:focus {    border-bottom: 2px solid skyblue}" +

		".card {    z-index: 0;    border: none;    border-radius: 0.5rem;    position: relative}" +

		".fs-title {    font-size: 25px;   color: #2C3E50;    margin-bottom: 10px;    font-weight: bold;    text-align: left}" +

		"#progressbar {    margin-bottom: 30px;    overflow: hidden;    color: lightgrey}" +

		"#progressbar .active {    color: #000000}" +

		"#progressbar li {    list-style-type: none;    font-size: 12px;    width: 25%;    float: left;    position: relative}" +

		"#progressbar #account:before {    font-family: FontAwesome;    content: " + "\f023" + "}" +

		"#progressbar #personal:before {    font-family: FontAwesome;    content: " + "\f007" + "}" +

		"#progressbar #payment:before {    font-family: FontAwesome;    content: " + "\f09d" + "}" +

		"#progressbar #confirm:before {    font-family: FontAwesome;    content: " + "\f00c" + "}" +

		"#progressbar li:before {    width: 50px;    height: 50px;    line-height: 45px;    display: block;   font-size: 18px;    color: #ffffff;    background: lightgray;    border-radius: 50%;    margin: 0 auto 10px auto;    padding: 2px}" +

		"#progressbar li:after {    content: '';    width: 100%;    height: 2px;    background: lightgray;    position: absolute;    left: 0;    top: 25px;    z-index: -1}" +

		"#progressbar li.active:before,#progressbar li.active:after {    background: skyblue}" +

		".radio-group {    position: relative;    margin-bottom: 25px}" +

		".radio {    display: inline-block;    width: 204;    height: 104;    border-radius: 0;    background: lightblue;    box-shadow: 0 2px 2px 2px rgba(0, 0, 0, 0.2);    box-sizing: border-box;    cursor: pointer;    margin: 8px 2px}" +

		".radio:hover {    box-shadow: 2px 2px 2px 2px rgba(0, 0, 0, 0.3)}" +

		".radio.selected {    box-shadow: 1px 1px 2px 2px rgba(0, 0, 0, 0.1)}" +

		".fit-image {    width: 100%;    object-fit: cover}" +

		".custom-control-label {color:black}";


	$("#stile").html(style);
}
