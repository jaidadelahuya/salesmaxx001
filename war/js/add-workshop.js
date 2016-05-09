var msgD = $("#msg-div");
var myForm = $("#add-workshop-form");
var formDiv = $(".form-div");

function validate() {
	
	var input = $("#image");
	var ok = required(input);
	if (!ok) {
		addError(msgD, "You have to select an Image.");
		return false;
	}

	input = $("#skill-level");
	ok = input.val()===null;
	if (ok) {
		addError(msgD, "You have to select at least one Skill Level.");
		return false;
	}

	input = $("#description");
	ok = required(input);
	if (!ok) {
		addError(msgD, "Description cannot be empty");
		return false;
	}

	input = $("#related-workshops");
	ok = required(input);
	if (!ok) {
		addError(msgD, "Related Workshops cannot be empty");
		return false;
	}
	
	input = $("#audience");
	ok = required(input);
	if (!ok) {
		addError(msgD, "Audiences cannot be empty");
		return false;
	}

	input = $("#course-content");
	ok = required(input);
	if (!ok) {
		addError(msgD, "Course Content cannot be empty");
		return false;
	}
	
	input = $("#learning-outcomes");
	ok = required(input);
	if (!ok) {
		addError(msgD, "Learning Outcomes cannot be empty");
		return false;
	}

	input = $("#industries");
	ok = input.val()===null;
	if (ok) {
		addError(msgD, "You have to select at least one Industry");
		return false;
	}
	
	input = $("#professions");
	ok = input.val()===null;
	if (ok) {
		addError(msgD, "You have to select at least one Profesion");
		return false;
	}

	return true;
}

$(document).ready(function() {
	
	$(".temp-div").click(function() {
		$(this).parent().find(".temp-details").slideToggle();
	});
	myForm.on('submit', function(e) {
		console.log("jhdgdg");
		var x = $(this).attr('action');
		console.log(x);
		$.ajax({
			url : x,
			type : 'POST',
			data : new FormData(this),
			processData : false,
			contentType : false,
			success : function(data) {
				addSuccess(msgD,"The Workshop has been saved successfully.");
				clearForm(myForm);
			},
			error : function(jqXHR, status, errorThrown) {
				addError(msgD,jqXHR.statusText);
			}, complete : function() {
				formDiv.waitMe("hide");
			}
		});
		e.preventDefault();
	});
	
	$("#save-workshop").click(function() {
		
		formDiv.waitMe({
			effect : 'ios',
			color : '#b1010c',
			sizeW : '15',
			sizeH : '15'
		});
		console.log("clcic");
		var cont = validate();
		console.log("got here");
		if(cont) {
			var jqxhr = $.post("/sm-admin/getuploadurl", {"callback":"/sm-admin/save-workshop"}).done(function(data) {
				myForm.prop('action', data);
				console.log("got here s");
				myForm.submit();
				console.log("got here g");
			}).error(function() {
				addError(msgD,errorThrown);
			});
		} else {
			formDiv.waitMe("hide");
		}
		
	});
});