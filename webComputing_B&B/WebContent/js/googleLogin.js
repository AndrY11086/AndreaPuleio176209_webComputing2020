function onSignIn(googleUser) {

	var profile = googleUser.getBasicProfile();

	var nome = profile.getGivenName();
	var cognome = profile.getFamilyName();
	var email = profile.getEmail();
	
	//alert("Speriamo: " + googleUser.isSignedIn());
	
	var idToken = googleUser.getAuthResponse().id_token;
	var condition = googleUser.isSignedIn();
	
	//if (!condition) {
		
		$.ajax({
			type : "POST",
			url : "LoginSocial",
			data : {
				"nome" : nome,
				"cognome" : cognome,
				"email" : email,
				"idToken" : idToken
			},
			success : function() {
				location.reload();
			}
		});
		
	/*} else {
		logoutGoogle();
	}*/
}

// SIGN OUT
function signOut() {
	var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
	
	
}

function onLoad() {
	gapi.load('auth2', function() {
		gapi.auth2.init();
	});
}

function logoutGoogle() {
	
	
	$.ajax({
		type : 'GET',
		url : 'EffettuaLogout',
		success : function() {
			signOut();
			window.location = "Home.jsp";
		}
	});
	
	
}