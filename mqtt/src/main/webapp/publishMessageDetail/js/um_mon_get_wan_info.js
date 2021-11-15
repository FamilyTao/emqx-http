$(document).ready(function () {
    var payload = sessionStorage.getItem("payload");
    var obj = JSON.parse(payload);
    if (obj.hasOwnProperty("code")) {
        $("#code").attr("value", obj["code"]);
    }
    if (obj.hasOwnProperty("errormsg")) {
        $("#errormsg").attr("value", obj["errormsg"]);
    }
    if (obj.hasOwnProperty("wan_count")) {
        $("#wan_count").attr("value", obj["wan_count"]);
    }
    if (obj.hasOwnProperty("list")) {
		var list = obj["list"];
    	if (list.length == 0) {
    		var tbody = "<tr><td colspan='6'>暂无数据</td></tr>";
    		$("tbody").html(tbody);
    	} else {
        	var tbody = "";
        	$.each(list, function(n, value) {
        		var trs = "";
            	trs += "<tr> <td> " + value.wan_index + " </td>";
            	trs += "<td> " + value.wan_name + " </td>";
            	trs += "<td> " + value.mac + " </td>";
            	trs += "<td> " + value.work_mode + " </td>";
            	trs += "<td> " + value.connect_type + " </td>";
            	trs += "<td> " + value.connect_time + " </td>";
            	var ipv4 = JSON.stringify(value.ipv4);
            	var ipv6 = JSON.stringify(value.ipv6);
            	trs += "<td><div class='button-group'> <a class='button border-main' href='javascript:void(0)' onclick='getIpv4(" + ipv4 +")'>详情</a></td>";
            	trs += "<td><div class='button-group'> <a class='button border-main' href='javascript:void(0)' onclick='getIpv6(" + ipv6 +")'>详情</a></td></tr>";
            	tbody += trs;
     		});
     		$("tbody").html(tbody);
     	}
	}
});

function getIpv4(info) {
	var json = JSON.stringify(info)
	sessionStorage.setItem("info", json);
	window.location.href='/publishMessageDetail/um_mon_get_wan_info_ipv4.html';
}

function getIpv6(info) {
	var json = JSON.stringify(info)
	sessionStorage.setItem("info", json);
	window.location.href='/publishMessageDetail/um_mon_get_wan_info_ipv6.html';
}