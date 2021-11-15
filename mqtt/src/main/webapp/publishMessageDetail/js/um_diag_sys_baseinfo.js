$(document).ready(function() {
	var payload = sessionStorage.getItem("payload");
	var obj = JSON.parse(payload);
	if(obj.hasOwnProperty("code")){
		 $("#code").attr("value", obj["code"]);
	}
	if(obj.hasOwnProperty("errormsg")){
		 $("#errormsg").attr("value", obj["errormsg"]);
	}
	if(obj.hasOwnProperty("cpu_utilization")){
		 $("#cpu_utilization").attr("value", obj["cpu_utilization"]);
	}
	if(obj.hasOwnProperty("memory_utilization")){
		 $("#memory_utilization").attr("value", obj["memory_utilization"]);
	}
	if(obj.hasOwnProperty("storage_utilization")){
		 $("#storage_utilization").attr("value", obj["storage_utilization"]);
	}
	if(obj.hasOwnProperty("memory_total")){
		 $("#memory_total").attr("value", obj["memory_total"]);
	}
});