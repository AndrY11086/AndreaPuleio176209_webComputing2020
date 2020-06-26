function showPictures() {

	$.ajax({
		type : "GET",
		url : "ShowGallery",
		success : function(txt) {
			$("#demo").append(txt);
		}
	});
}
function toSwitch(change){
	$.ajax({
		type:"GET",
		url:"ImageToShow",
		data: {
			"switch":change
		},
		success:function(){
			
		}
	});
}
function onLoad(){
	
	var txt = "<ul class='carousel-indicators'>";
		
		for (i = 0; i < 13; i++) {
			if(i == 0) {
				txt +=  "<li data-target='#demo' data-slide-to='"+i+"' class='active'></li>";
			}
			else {
				txt +=  "<li data-target='#demo' data-slide-to='"+i+"'></li>";
			}
		}
		
		txt += "</ul>";
		txt += "<div class='carousel-inner'>";
		for (j=0; j<13; j++) {
			
			var path = "images/gallery/"+j+".jpg";//file.getName();
			
			if(j == 0) {
				txt += "<div id='" + path + "' class='carousel-item active' align='center'>";
			}
			else {
				txt += "<div id='" + path + "' class='carousel-item' align='center'>";
			}
			txt += "<img src='"+path+"' alt='B&BChiara' style='width: 60%'>";
			txt += "<div class='carousel-caption'>";
			//txt += "<h3>" + fotografia.getTitle() + "</h3>";
			//txt += "<p>" + fotografia.getDescription() + "</p>";
			txt += "</div></div>";
			
		}
		txt += "</div>";
		
		$("#demo").append(txt);
	
}