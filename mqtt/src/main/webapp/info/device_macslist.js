(function($) {
	$("#indexinfo").attr("value", "");
	var urlinfo = window.location.href;
	var key = urlinfo.split("?")[1];
	if(key.indexOf("mac") != -1 ){
		var paramsInfo = key.split("&");
		var mac = paramsInfo[0].split("=")[1];
		getDeviceMacInfo(mac);
	}
})(jQuery);

$(document)
	.ready(function() {
		var urlinfo = window.location.href;
		var key = urlinfo.split("?")[1];
		if(key.indexOf("mac") != -1 ){
			var paramsInfo = key.split("&");
			var index = paramsInfo[1].split("=")[1];
			$("#indexinfo").attr("value", index);
		}
	});



function getDeviceMacInfo(mac) {
    $.ajax({
        type: "get",
        url: 'equipment/getDeviceMacInfo?mac=' + mac,
        success: function(data) {
            var list = data.data;
            if (list.length == 0) {
                var tbody = "<tr><td colspan='3'>暂无数据</td></tr>";
                $("tbody").html(tbody);
            } else {
                var tbody = "";
                $.each(list,
                function(n, value) {
                    var trs = "<tr> ";
                    var size = n + 1;
                    trs += "<td><input type='checkbox' name='id[]' value=" + value.device_mac + " />" + size + "</td>";
                    trs += "<td> " + value.device_mac + " </td>";
                    trs += "<td> " + value.active + " </td></tr>";
                    tbody += trs;
                });
                $("tbody").html(tbody);
            }
        },
        error: function() {
            alert("查询失败！");
        }
    });
}

$(function() {
    $("#checkall").click(function() {
        $("input[name='id[]']").each(function(){
	 		this.checked = true;
  		});
  		$("#notcheckall").attr("style", "display:block;");
  		$("#checkall").attr("style", "display:none;");
    });
    
    $("#notcheckall").click(function() {
        $("input[name='id[]']").each(function(){
	 		this.checked = false;
  		});
  		$("#checkall").attr("style", "display:block;");
  		$("#notcheckall").attr("style", "display:none;");
    });
    
    $("#submit").click(function() {
    	var device_mac = "";
        $("input[name='id[]']").each(function(){
        	if (this.checked==true) {
        		device_mac += $(this).val() + "#";
        	}
  		});
  		if(isEmpty(device_mac)){
  			alert("下挂对象为空，请先获取设备下挂设备简略信息");
  			sessionStorage.setItem("sign_topic", "um_mon_get_device_brief_info");
  			window.location.href='command.html';
  		}else{
  			device_mac = device_mac.substring(0, device_mac.length - 1);
  			var indexinfo = $('#indexinfo').val();
  			window.location.href='maclist.html?device_status=1&device_mac=' + device_mac + '&index=' + indexinfo;
  		}
    });
});

function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}
