function required($input) {
	var val = $input.val();
	if (!(val === null)) {

		if (val.length == 0) {
			return false;
		} else {
			return true;
		}
	} else {
		return false;
	}
}

function allNumeric($input) {
	var val = $input.val();
	if (!(val === null)) {
		var numbers = /^[0-9]+$/;
		if (val.match(numbers)) {
			return true;
		} else {
			return false;
		}
	} else {
		return false;
	}

}

function clearForm(form) {
	form[0].reset();
}

function validateEmail($input) {
	var val = $input.val();
	if (!(val === null)) {
		if (/^\S+@\S+\.\S+/.test(val)) {
			return (true)
		} else {
			return false;
		}
	} else {
		return false;
	}
	
}