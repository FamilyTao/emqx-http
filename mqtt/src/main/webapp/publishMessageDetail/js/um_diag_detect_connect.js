$(document).ready(function() {
	var payload = sessionStorage.getItem("payload");
	var obj = JSON.parse(payload);
	if (obj.hasOwnProperty("code")) {

		$("#code").attr("value", obj["code"]);
	}
	if (obj.hasOwnProperty("errormsg")) {
		$("#errormsg").attr("value", obj["errormsg"]);
	}
	if (obj.hasOwnProperty("status")) {
		$("#status").attr("value", obj["status"]);
	}
	if (obj.hasOwnProperty("detect_number")) {
		$("#detect_number").attr("value", obj["detect_number"]);
	}
	//解析数组
	if (obj.hasOwnProperty("list")) {
		var list = obj["list"];
    	if (list.length == 0) {
    		var tbody = "<tr><td colspan='6'>暂无数据</td></tr>";
    		$("tbody").html(tbody);
    	} else {
  
        	var tbody = "";
        	$.each(list, function(n, value) {
        		var trs = "";
            	trs += "<tr> <td> " + value.detect_item_id + " </td>";
            	trs += "<td> " + value.detect_item_result + " </td>";
            	trs += "<td> " + value.detect_item_fail_reason + " </td> </tr>";
            	tbody += trs;
     		});
     		$("tbody").html(tbody);
     	}
	}
});