$(".alignment .btn").click(function() {
    // whenever a button is clicked, set the hidden helper
    $("#alignment").val($(this).text());
});

function workerRegistration(){
	var hasErrors = false;
	var emailId = document.getElementById("email").value;
	if(emailId.indexOf("@") == -1 || emailId.indexOf(".") == -1){
		hasErrors = true;
	}
	if (hasErrors) {
		return false;
	}
	else return true;
}

// Duplicate email check for worker
$(document).ready(function(){
	$("#email").blur(validateEmail);
});

function emailValidationSuccess(resp){
	if(!resp){
		$("#email").css("border-color","red")
	}
	else {
		$("#email").css("border-color","black")
	}
}

function emailValidationFailure(resp){
	console.log(resp);
}

function validateEmail(evt){
	$.ajax({
		url: 'EmailValidationServlet',
		data: {
			'email' :this.value  
		},
		success: emailValidationSuccess,
		failure: emailValidationFailure
	});
}

//Duplicate email check for client
$(document).ready(function(){
	$("#email").blur(validateEmail);
});

function emailValidationSuccess(resp){
	if(!resp){
		$("#email").css("border-color","red")
	}
	else {
		$("#email").css("border-color","black")
	}
}

function emailValidationFailure(resp){
	console.log(resp);
}

function validateEmail(evt){
	$.ajax({
		url: 'EmailValidationServlet',
		data: {
			'email' :this.value  
		},
		success: emailValidationSuccess,
		failure: emailValidationFailure
	});
}