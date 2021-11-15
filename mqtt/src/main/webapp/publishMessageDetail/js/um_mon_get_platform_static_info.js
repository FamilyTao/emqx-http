$(document).ready(function() {
	var payload = sessionStorage.getItem("payload");
	var obj = JSON.parse(payload);
	if(obj.hasOwnProperty("code")){
		 $("#code").attr("value", obj["code"]);
	}
	if(obj.hasOwnProperty("errormsg")){
		 $("#errormsg").attr("value", obj["errormsg"]);
	}
	if(obj.hasOwnProperty("model")){
		 $("#model").attr("value", obj["model"]);
	}
	if(obj.hasOwnProperty("sn")){
		 $("#sn").attr("value", obj["sn"]);
	}
	if(obj.hasOwnProperty("soft_version")){
		 $("#soft_version").attr("value", obj["soft_version"]);
	}
	if(obj.hasOwnProperty("hard_version")){
		 $("#hard_version").attr("value", obj["hard_version"]);
	}
});