function generatePassword() {
	var chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&**";
	password = "";
	for (i = 0; i < 6; i++) {
		var x = Math.floor((Math.random() * 100) + 1);
		x = x % chars.length;
		password = password + chars.charAt(x)
	}
	return password;
}
var msgD = $("#msg-div");

function validate () {
	var input = $("#username");
	var ok = required(input);
	if(!ok) {
		addError(msgD,"Admin Email cannot be empty");
		return false;
	}
	
	ok = validateEmail(input);
	if(!ok) {
		addError(msgD,"The Email address you entered is not valid");
		return false;
	}
	
	input = $("#password");
	ok = required(input);
	if(!ok) {
		addError(msgD,"Password cannot be empty");
		return false;
	}
	
	input = $("#role").val();
	
	if(input === "role") {
		addError(msgD,"You have to select a Role");
		return false;
	}
	
	return true;
	
}

$(document).ready(function() {
	$("#generate").click(function() {
		var password = generatePassword();
		$("#password").val(password);
	});
	
	$("#send-code").click(function() {
		var myForm = $("#developer-password-form");
		var jqxhr = $.post("/create-user", myForm.serialize(), function() {

		}).done(function(data) {
			window.location.assign(data);
		}).fail(function() {
			alert("Worng Password");
		});
	});
	
	$("#create-user-btn").click(function() {
		var cont = validate();
		var myForm = $("#create-user-form");
		$(".form-div").waitMe({
			effect : 'ios',
			color : '#b1010c',
			sizeW : '15',
			sizeH : '15'
		});
		
		if(cont) {
			var jqxhr = $.post("/sm-admin/add-user", myForm.serialize()).done(function(data) {
				addSuccess(msgD,"User created successfully");
			}).error(function(jqXHR,status,errorThrown) {
				addError(msgD,errorThrown);
			}).always(function() {
				$(".form-div").waitMe("hide");
			});
		} else {
			$(".form-div").waitMe("hide");
		}
	});
});
