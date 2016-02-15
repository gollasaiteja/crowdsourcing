function workerRegistration() {
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