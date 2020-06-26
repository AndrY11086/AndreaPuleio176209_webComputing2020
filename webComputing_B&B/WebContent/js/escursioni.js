var dataSelezionata;


function aggiungi(id, loggato) {

	if (!loggato) {
		if (confirm("per acquistare sul sito devi essere registrato. Premendo ok verrai reindirizzato alla pagina di login"))
			location.replace("AreaRiservata.jsp");
	}else{
	
		var nome = getNome(id);
		var codice = getCodice(id);

		var txt = "<div class='modal fade' id='"
			+ id
			+ "X' tabindex='-1' role='dialog'"
			+ "aria-labelledby='" + id + "' aria-hidden='true'>"
			+ "<div class='modal-dialog'>"
			+ "<div class='modal-content'>"
			+ "<div class='modal-header'>"
			+ "<h5 class='modal-title' id='nomeEvento" + id + "'>" + nome + "</h5>"
			+ "<button type='button' class='close' data-dismiss='modal'"
			+ "aria-label='Close'>"
			+ "<span aria-hidden='true'>&times;</span></button></div>"
			+ "<div class='modal-body'>"
			+ codice
			+ "<p style='color:black' id='finalPrice'></p>"
			+ "</div>"
			+ "<div class='modal-footer'>"
			+ "<button type='button' class='btn btn-danger'"
			+ "data-dismiss='modal'>Annulla</button>"
			+ "<button id='" + id + "' type='button' class='btn btn-warning' data-dismiss='modal' onclick='aggiungiAlCarrello(this.id)'><span class='fa fa-shopping-cart' ></span>  Aggiungi al carrello</button>"
			+ "</div></div></div></div>";

		$("footer").append(txt);



		$(document).ready(function() {
			$("#passenger" + id).click(function() {
				var price = getPrice($("#" + id).text(), id);
				$("#finalPrice").html("Prezzo finale: " + price + " &euro;");
			});
		});
	}
}
function getPrice(price, id) {
	var finalPrice = price.split(" ");
	var final = finalPrice[1];
	final = final * $("#passenger" + id).val();
	return final;
}

function getNome(id) {

	switch (id) {
		case "isoleEolie":
			return "Giornata alle isole Eolie";
		case "tracciolino":
			return "Tracciolino di Palmi";
		case "subImm":
			return "Immersioni subacquee";
		case "lido":
			return "Lido con 1 ombrellone e 2 lettini";
	}

}

function getCodice(id) {

	var date = getDate();

	switch (id) {
		case "isoleEolie":
		case "subImm":
			return "<select name='passenger' class='custom-select mb-3' id='passenger" + id + "'>"
				+ "<option selected>Numero Persone</option>"
				+ "<option value='1'>1</option>"
				+ "<option value='2'>2</option>"
				+ "<option value='3'>3</option>"
				+ "<option value='4'>4</option>"
				+ "<option value='5'>5</option>"
				+ "</select>";
		case "tracciolino":
			return "<select name='passenger' class='custom-select mb-3' id='passenger" + id + "'>"
				+ "<option selected>Numero Persone (max 4)</option>"
				+ "<option value='1'>1</option>"
				+ "<option value='2'>2</option>"
				+ "<option value='3'>3</option>"
				+ "<option value='4'>4</option>"
				+ "</select>";
		case "lido":

			return "<select name='passenger' class='custom-select mb-3' id='passenger" + id + "'>"
				+ "<option selected>Numero ombrelloni (max 2)</option>"
				+ "<option value='1'>1</option>"
				+ "<option value='2'>2</option>"
				+ "</select>"
				+ date;
	}
}

function getDate() {

	var toReturn = "<select name='data' class='custom-select mb-3' id='data'>";

	var firstDay = new Date("21 Jun 2020");
	var last = new Date;
	var september = new Date();
	september.setFullYear(2020, 8 ,15);
	var limit = false;

	for (i = 0; i < 13; i++) {

		if (i > 0) {
			firstDay.setDate(firstDay.getDate() + 7);
		}

		var day = firstDay.getDate();
		var month = firstDay.getMonth() + 1;
		var year = firstDay.getYear() + 1900;
		var arrivo = day + "/" + month + "/" + year;

		last.setDate(firstDay.getDate() + 7);

		var lastday = last.getDate();
		var lastmonth = last.getMonth() + 1;
		var lastyear = last.getYear() + 1900;
		var partenza = lastday + "/" + lastmonth + "/" + lastyear;



		if (firstDay < september) {
			toReturn += "<option value='" + arrivo + " --> " + partenza + "'>Dal " + arrivo + " al " + partenza + "</option>";
		} else {
			limit = true;
			break;
		}

	}

	if (limit === true) {
		var nextYear = firstDay.getYear() + 1 + 1900;
		toReturn += "<option value='weekEnds'>Stagione terminata. Ritorniamo nel " + nextYear + "</option>";
	}

	toReturn += "</select>";
	return toReturn;
}


function aggiungiAlCarrello(id) {
	var price = $("#finalPrice").text();

	var split = price.split(" ");
	var final = split[2];

	$.ajax({
		type: "GET",
		url: "Escursioni",
		data: {
			"nome": $("#nomeEvento" + id).text(),
			"prezzo": final,
			"nPax": $("#passenger" + id).val(),
			"data": $("#data").val()
		},
		success: function(jsonArray) {
			
			var json = JSON.parse(jsonArray);
			var txt = "";
			for(i in json){
				txt += json[i].nPersone + "x "+json[i].nome;

				if(json.length > 1){
					txt += ", ";
				}
			}
					
			$("#elementi").html(txt);
		}
	});
}

