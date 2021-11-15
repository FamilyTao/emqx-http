$(document).ready(function() {
	var payload = sessionStorage.getItem("payload");
	var obj = JSON.parse(payload);
	if(obj.hasOwnProperty("code")){
		 $("#code").attr("value", obj["code"]);
	}
	if(obj.hasOwnProperty("errormsg")){
		 $("#errormsg").attr("value", obj["errormsg"]);
	}
	if(obj.hasOwnProperty("device_number")){
		 $("#device_number").attr("value", obj["device_number"]);
	}
	if (obj.hasOwnProperty("list")) {
		var list = obj["list"];
    	if (list.length == 0) {
    		var tbody = "<tr><td colspan='12'>暂无数据</td></tr>";
    		$("tbody").html(tbody);
    	} else {
        	var tbody = "";
        	$.each(list, function(n, value) {
        		var trs = "";
            	trs += "<tr> <td> " + value.mac + " </td>";
            	trs += "<td> " + value.ipv4_addr + " </td>";
            	trs += "<td> " + value.ipv6_linklocal_addr + " </td>";
            	trs += "<td> " + value.active + " </td>";
            	trs += "<td> " + value.host_name + " </td>";
            	trs += "<td> " + value.connection_type + " </td>";
            	trs += "<td> " + value.latest_active_time + " </td>";
            	trs += "<td> " + value.latest_inactive_time + " </td>";
            	trs += "<td> " + value.latest_online_time + " </td>";
            	trs += "<td> " + value.rx_average_speed + " </td>";
            	trs += "<td> " + value.tx_average_speed + " </td>";
            	trs += "<td> " + value.negotiation_speed + " </td> </tr>";
            	tbody += trs;
     		});
     		$("tbody").html(tbody);
     	}
	}
});