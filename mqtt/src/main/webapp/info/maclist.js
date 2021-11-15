$(document).ready(function() {
    var urlinfo = window.location.href;
    var key = urlinfo.split("?")[1];
    if (key.indexOf("device_status") != -1) {
        var paramsInfo = key.split("&")[0];
        var device_status = paramsInfo.split("=")[1];
        if (device_status == "1") {
            $("#device_info").attr("style", "display:block;");
            getEquipmentInfo(1);
        } else {
            $("#device_info").attr("style", "display:none;");
            getEquipmentInfo(0);
        }
    } else {
        getEquipmentInfo(0);
    }
});

$(window).load(function() {
    var urlinfo = window.location.href;
    if (urlinfo.indexOf("device_mac") != -1) {
        var key = urlinfo.split("?")[1];
        var paramsInfo = key.split("&");
        var device_mac = paramsInfo[1].split("=")[1];
        var index = paramsInfo[2].split("=")[1];
        $(eval("device_macs" + index)).attr("value", device_mac);
    }
});

function getEquipmentInfo(num) {
    $.ajax({
        type: "get",
        async: false,
        url: 'equipment/getAllMacInfo',
        success: function(data) {
            var list = data.data;
            if (list.length == 0) {
                var tbody = "";
                if (num == 1) {
                    tbody = "<tr><td colspan='3'>暂无数据</td></tr>";
                } else {
                    tbody = "<tr><td colspan='2'>暂无数据</td></tr>";
                }
                $("tbody").html(tbody);
            } else {
                var tbody = "";
                $.each(list,
                function(n, value) {
                    var trs = "<tr> ";
                    var size = n + 1;
                    trs += "<td><input type='checkbox' name='id[]' value=" + value.mac + " />" + size + "</td>";
                    trs += "<td> " + value.mac + " </td>";
                    if (num == 1) {
                        var mac = value.mac;
                        trs += "<td><div class='field'> <input type='text' class='input' id='device_macs" + n + "' value='' disabled='disabled' /> <input onclick='getDeviceMacslist(\"" + mac + "\"," + n + ")' type='button' value='选择下挂设备' /></td></tr>";
                    } else {
                        trs += " </tr>";
                    }
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
        $("input[name='id[]']").each(function() {
            this.checked = true;
        });
        $("#notcheckall").attr("style", "display:block;");
        $("#checkall").attr("style", "display:none;");
    });

    $("#notcheckall").click(function() {
        $("input[name='id[]']").each(function() {
            this.checked = false;
        });
        $("#checkall").attr("style", "display:block;");
        $("#notcheckall").attr("style", "display:none;");
    });

    $("#submit").click(function() {
        var macs = "";
        var result = false;
        $("input[name='id[]']").each(function() {
            if (this.checked == true) {
                macs += $(this).val();
                var urlinfo = window.location.href;
                var key = urlinfo.split("?")[1];
                if (key.indexOf("device_status") != -1) {
                    var paramsInfo = key.split("&")[0];
                    var device_status = paramsInfo.split("=")[1];
                    if (device_status == "1") {
                        var device_mac = this.parentNode.parentNode.children[2].children[0].children[0].getAttribute("value");
                        if (isEmpty(device_mac)) {
                            result = true;
                        }else{
                        	macs += "/" +  device_mac +",";
                        }
                    }else{
                    	macs += ",";
                    }
                }
            }
        });
        if (result) {
        	alert("选择对象未选择下辖设备");
            return;
        }else{
      		if (isEmpty(macs)) {
            	alert("请选择发送对象");
            	return;
        	} else {
           		macs = macs.substring(0, macs.length - 1);
           		window.location.href = 'command.html?allmacsinfo=' + macs;
        	}
        }
    });
});

function isEmpty(obj) {
    if (typeof obj == "undefined" || obj == null || obj == "") {
        return true;
    } else {
        return false;
    }
}

function getDeviceMacslist(mac, n) {
    var url = "device_macslist.html?mac=" + mac + "&index=" + n;
    window.location.href = url;
}