function redirect(loggato) {
	if (loggato){
		location.replace("Carrello.jsp");
	}
	else {
		if(confirm("per procedere con l'acquisto devi essere registrato. Premendo OK verrai reindirizzato alla pagina di login."))
		location.replace("AreaRiservata.jsp");
	}

}

function loadCarrello() {

	$.ajax({
		type: "GET",
		url: "Carrello",
		success: function(jsonObject) {

			var json = JSON.parse(jsonObject);
			
			var txt = "<hr>";
			for (i in json) {
				if(i<json.length -1){
					
				txt += "<div align='justify' class='alert alert-light alert-dismissible fade show' role='alert'>"
					+ json[i].nPersone + "x " + json[i].nomeEscursione + " " + json[i].prezzo + "&euro;"
					+ "<button id='" + json[i].nomeEscursione
					+ "' type='button' class='close' data-dismiss='alert' aria-label='Close' onclick='remove(this.id)'>"
					+ "<span aria-hidden='true'>&times;</span>" + "  </button></div>";
				}
				
			}
			txt += "<hr><p align='justify' id='total'> Totale: "+json[json.length-1].total+" &euro;</p>";
			
			$("#carrello").html(txt);
		}
	});
}

function remove(id) {
	$.ajax({
		type: "POST",
		url: "Carrello",
		data: {
			"toRemove": id
		},
		success: function() {
			loadCarrello();
			location.reload();
		}
	});
}

function pagamento() {

	var prezzo = $("#total").text();
	var price = prezzo.split(" ");

	var txt = "<form action='/webComputing_BB/acceptpaymentrequest' method='POST'>";
	txt += "<script src='https://checkout.stripe.com/checkout.js' class='stripe-button'";
	txt += "data-key='pk_test_FUOi8YhwjaeYtXQVUHJunWNr00DWKPtsln'";
	txt += "data-amount= " + price[2] + "00";
	txt += "data-name='B&B Chiara'";
	txt += "data-description='Pagamento'";
	txt += "data-image='https://stripe.com/img/documentation/checkout/marketplace.png'";
	txt += "data-locale='auto'";
	txt += "data-currency='eur'>";
	txt += "</script></form>";


	$("#pay").html(txt);


}