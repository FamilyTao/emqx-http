$(document).ready(function() {
	var payload = sessionStorage.getItem("payload");
	var obj = JSON.parse(payload);
	if(obj.hasOwnProperty("code")){
		 $("#code").attr("value", obj["code"]);
	}
	if(obj.hasOwnProperty("errormsg")){
		 $("#errormsg").attr("value", obj["errormsg"]);
	}
	if(obj.hasOwnProperty("list")){
		var list = obj["list"];
    	if (list.length == 0) {
    		var tbody = "<tr><td colspan='6'>暂无数据</td></tr>";
    		$("tbody").html(tbody);
    	} else {
        	var tbody = "";
        	$.each(list, function(n, value) {
        		var trs = "";
            	trs += "<tr> <td> " + value.frequency + " </td>";
            	trs += "<td> " + value.channel + " </td>";
            	trs += "<td> " + value.power_level + " </td>";
            	trs += "<td> " + value.band_width + " </td> </tr>";
            	tbody += trs;
     		});
     		$("tbody").html(tbody);
     	}
	}
});