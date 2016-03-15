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
	$("#clientEmail").blur(validateEmail);
	});
function emailValidationSuccess(resp){
	if(!resp){
		$("#clientEmail").css("border-color","red")
	}
	else {
		$("#clientEmail").css("border-color","black")
	}
}
function emailValidationFailure(resp){
	console.log(resp);
}
function validateEmail(evt){
		$.ajax({
			  url: 'EmailValidationServletClient',
			  data: {
				'email' :this.value  
			  },
			  success: emailValidationSuccess,
			  failure: emailValidationFailure
			});
}

$(document).ready(function(){
	$.ajax({
        type: "GET",
        url: 'EditClientSelect',
        success: function(response){
        	
        	$("#first_name").val(response.firstName);
        	$("#last_name").val(response.lastName);
        	$("#password").val(response.password);
        	$("#card-holder-name").val(response.cardHolderName);
        	$("#card-number").val(response.cardNumber);
        	$("#expiry-month").val(response.expiryMonth);
        	$("#expiry_year").val(response.expiryYear);
        	$("#cvv").val(response.cvv);
        }
        });
     });