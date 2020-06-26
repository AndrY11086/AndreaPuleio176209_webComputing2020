function onLoad() {

	$.ajax({
		type: "GET",
		url: "Recensioni",
		success: function(jsonObject) {
			var txt = "";
			var json = JSON.parse(jsonObject);
			var voti = getVoti(voti, json);
			var percentuali = getPercentuale(percentuali, voti, json[json.length - 1].size);
			var media = getMedia(json[json.length - 1].size, voti).toFixed(2);

			var buttonFunction = "";

			if (json[json.length - 1].clienteCheck != "vuoto") {
				buttonFunction = "newRating(this.id)";
			}
			else {
				buttonFunction = "redirect()";
			}

			txt = "<div class='row'>" + "<div class='col-sm-3'>" + "<div class='rating-block'>"
				+ "<h4>Voto medio degli utenti</h4>" + "<h2 class='bold padding-bottom-7'>" + media
				+ " <small>/ 5</small></h2>";

			for (i = 0; i < 5; i++) {
				var css = "";
				if (i < media) {
					css = "btn-warning";
				}
				else {
					css = "btn-default btn-grey";
				}

				var id = i + 1;

				txt += "<button id='" + id + "' type='button' class='btn " + css + " btn-sm' aria-label='Left Align' onclick='" + buttonFunction;
				txt += "'><span class='fa fa-star checked'></span></button>";
			}

			txt += "</div></div>";
			txt += "<div id='newRat'></div>";
			txt += "<div class='col-sm-3'>" + "<h3>Votazioni</h3>";

			for (i = 0; i < 5; i++) {

				var number = 5 - i;
				var color = getColor(number);

				txt += "<div class='pull-left'>" + "<div class='pull-left' style='width:35px; line-height:1;'>"
					+ "<div style='height:9px; margin:5px 0; color:white'>" + number
					+ "<span id='star' class='fa fa-star checked'></span></div></div>"
					+ "<div class='pull-left' style='width:180px;'>"
					+ "<div class='progress' style='height:9px; margin:8px 0;'>"
					+ "<div class='progress-bar bg-"
					+ color + "' role='progressbar' aria-valuenow=" + number
					+ " aria-valuemin='0' aria-valuemax='5' style='width: " + percentuali[number - 1] + "%'>"
					+ "<span class='sr-only'>80% Complete (danger)</span>" + "</div></div></div>"
					+ "<div class='pull-right' style='margin-left:10px; color:white'>" + voti[number - 1] + "</div>"
					+ "</div>";
			}

			txt += "</div></div></div>";

			txt += "<div class='row'>" + "<div class='col-sm-7'>" + "<hr/><div class='review-block'>";

			for (i = 0; i < json.length - 1; i++) {
				
				var gender = getGender(json[i]);
				
				txt += "<div class='row'>" + "<div class='col-sm-3'>"
					+ "<img src='"+gender+"' class='mr-3 mt-3 rounded-circle'>"
					+ "<div class='review-block-name'>" + json[i].cliente + "</div>"
					+ "<div class='review-block-date'>" + json[i].data + "</div>" + "</div>"
					+ "<div class='col-sm-9'>" + "<div class='review-block-rate'>";

				for (j = 0; j < 5; j++) {
					var css = "";

					if (j < json[i].voto) {
						css = "btn-warning";
					} else {
						css = "btn-default btn-grey";
					}

					txt += "<button type='button' class='btn " + css + " btn-xs' aria-label='Left Align'>"
						+ "<span class='fa fa-star' aria-hidden='true'></span>" + "</button>";
				}

				txt += "</div><div class='review-block-title'>" + json[i].titolo + "</div>"
					+ "<div class='review-block-description'>" + json[i].commento + "</div>" + "</div>"
					+ "</div>";

			}

			$("#recensioni").html(txt);
		}
	});
}

function getGender(json){
	if(json.sesso === "M"){
		return "images/Avatar/man.png";
	}else{
		return "images/Avatar/woman.png"
	}
}

function getVoti(voti, json) {
	voti = [0.0, 0.0, 0.0, 0.0, 0.0];
	for (i in json) {
		voti[json[i].voto - 1]++;
	}
	return voti;
}
function getPercentuale(percentuali, voti, jsonSize) {
	var percentuali = [0.0, 0.0, 0.0, 0.0, 0.0];

	if (jsonSize > 1) {
		for (i = 0; i < 5; i++) {
			var div = voti[i] / jsonSize;
			percentuali[i] = div * 100;
		}
	}
	return percentuali;
}
function getMedia(tot, voti) {
	var media = 0.0;
	for (i = 0; i < 5; i++) {
		media += voti[i] * (i + 1);
	}
	var toReturn = media / tot;

	return toReturn;
}

function getColor(n) {
	switch (n) {
		case (5):
			return "success";
		case (4):
			return "primary";
		case (3):
			return "info";
		case (2):
			return "warning";
		case (1):
			return "danger";
		default:
			return "";
	}
}


function newRating(id) {

	var text = "<div class='alert alert-success alert-dismissible fade show'>" +
		"<button type='button' class='close' data-dismiss='alert'>&times;</button>" +
		"<label for='title' style='color:black'>Titolo:</label>" +
		"<input type='text' id='title' name='title'><br><br>" +
		"<label for='comment' style='color:black'>Commento:</label>" +
		"<input type='text' id='comment' name='comment'><br><br>" +
		"<p id='idVoto' style='color:black'>Voto: " + id + "</p>" +
		"<button class='btn btn-success' data-dismiss='alert' onclick='saveRating()'>Invia</button>" +
		"</div>";

	$("#newRat").html(text);
}

function saveRating() {

	$.ajax({
		type: "POST",
		url: "Recensioni",
		data: {
			"voto": $("#idVoto").text(),
			"titolo": $("#title").val(),
			"commento": $("#comment").val()
		},
		success: function() {
			var text = "<div class='alert alert-success alert-dismissible fade show'>" +
				"<button type='button' class='close' data-dismiss='alert'>&times;</button>" +
				"<p style='color:black'>Grazie per averci lasciato la tua opinione!</p>";
			$("#newRat").html(text);

			onLoad();
		}
	});
}
function redirect() {
	if (confirm("Per lasciare una recensione devi essere registrato. Premendo OK verrai reindirizzato alla pagina di login."))
		location.replace("AreaRiservata.jsp");
}