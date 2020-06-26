function statusChangeCallback(response){
	
	if(response.status === 'connected'){
		testAPI();
	}
}

function ckeckLoginState(){
	
	FB.getLoginStatus(function(response){
		statusChangeCallback(response);
	});
}

window.fbAsyncInit = function(){
	
	FB.init({
		appId : '524686175075376',
		autoLogAppEvents : true,
		cookie : true,
		xfbml : true,
		oauth : true,
		version : 'v6.0'
	});
	
	FB.AppEvents.logPageView();  
	
	FB.getLoginStatus(function(response){
		statusChangeCallback(response);
	});
};

(function(d,s,id){
	var js, fjs = d.getElementByTagName(s)[0];
	if(d.getElementById(id)){return;}
	js = d.createElement(s); js.id = id;
	js.src = "https://connect.facebook.net/en_us/sdk.js";
	fjs.parentNode.insertBefore(js,fjs);
}(document, 'script', 'facebook-jssdk'));

function testAPI(){
	
	FB.api('/me?fields=email,first_name,last_name', 
			function(response){
		if(response && !response.error){
			buildProfile(response);
		}
	})
}

function buildProfile(user){
	
	var nome = user.first_name;
	var cognome = user.last_name;
	var email = user.email;
	
	$.ajax({
		type : "POST",
		url : "LoginSocial",
		data: {
			"nome":nome,
			"cognome":cognome,
			"email":email
		},
		success : function(){
			location.reload();
		}
	});
}
