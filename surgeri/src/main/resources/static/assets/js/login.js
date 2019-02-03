function doLogin() {
	var isValidForm = validateLogin();
	if (isValidForm) {
		var username = $("#username").val();
		var password = $("#password").val();

		var loginDetails = {}
		loginDetails["username"] = $("#username").val();
		loginDetails["password"] = $("#password").val();

		$.ajax({
			type : "POST",
			contentType : "application/json; charset=utf-8",
			url : "http://localhost:8080/user/login",
			data : JSON.stringify(loginDetails),
			// dataType : 'json',
			success : function(data, xhr) {
				// login success- redirect to dashboard
				console.log("Login success");
				window.location = "/getDashboard";
			},
			error : function(data) {
				console.log('Login error: ' + data.responseJSON.errorMessage);
				$("#errorDiv").removeClass('hide');
				var errorMessage = "<p> " + data.responseJSON.errorMessage
				+ " </p>"
				$("#errorDiv").append(errorMessage);
			},
		});
	}
}

function validateLogin() {
	var username = $("#username").val();
	var password = $("#password").val();

	if (!username || username == "") {
		$("#usernameDiv").addClass('is-dirty');
		$("#usernameDiv").addClass('is-invalid');
		return false;
	} else {
		$("#usernameDiv").removeClass('is-dirty');
		$("#usernameDiv").removeClass('is-invalid');
	}

	if (!password || password == "") {
		$("#passwordDiv").addClass('is-dirty');
		$("#passwordDiv").addClass('is-invalid');
		return false;
	} else {
		$("#passwordDiv").removeClass('is-dirty');
		$("#passwordDiv").removeClass('is-invalid');
	}

	return true;
}


function logout() {
	$.ajax({
		type : "GET",
		url : "http://localhost:8080/user/logout",
		success : function(data) {
			// login success- redirect to dashboard
			console.log("Logout success");
			window.location = "/";
		},
		error : function(data) {
			window.location = "/";
		},
	});
}