$(document).ready(function() {
	var payload = sessionStorage.getItem("payload");
	var obj = JSON.parse(payload);
	if(obj.hasOwnProperty("code")){
		 $("#code").attr("value", obj["code"]);
	}
	if(obj.hasOwnProperty("errormsg")){
		 $("#errormsg").attr("value", obj["errormsg"]);
	}
	if(obj.hasOwnProperty("status")){
		 $("#status").attr("value", obj["status"]);
	}
	if (obj.hasOwnProperty("topology")) {
		var list = obj["topology"];
    	if (list.length == 0) {
    		var tbody = "<tr><td colspan='6'>暂无数据</td></tr>";
    		$("tbody").html(tbody);
    		
    	} else {
        	var tbody = "";
        	$.each(list, function(n, value) {
        		var trs = "";
            	trs += "<tr> <td> " + value.name + " </td>";
            	trs += "<td> " + value.ip + " </td>";
            	trs += "<td> " + value.mac + " </td>";
            	trs += "<td> " + value.negotiation_rate + " </td>";
            	trs += "<td> " + value.rx_average_speed + " </td>";
            	trs += "<td> " + value.tx_average_speed + " </td>";
            	trs += "<td> " + value.role + " </td>";
            	trs += "<td> " + value.child + " </td>";
            	trs += "<td> " + value.parent + " </td> </tr>";
            	tbody += trs;
     		});
     		$("tbody").html(tbody);
     	}
	}
});