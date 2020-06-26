function swapImage(id) {

	var first = $("#principale").attr('src');
	var second = $("#"+id).attr('src');

	$("#principale").attr('src',second);
	$("#"+id).attr('src',first);
	
}