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