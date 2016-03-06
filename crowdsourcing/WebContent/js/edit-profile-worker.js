$(document).ready(function(){
	$.ajax({
        type: "GET",
        url: 'EditWorkerSelect',
        success: function(response){
        	console.log(response);
//            $('#container').load(response);
        }
   });
});