(function($) {
    var publishMessage = {
        "client_id": "",
        "username": "",
        "topic": "",
        "page": 1,
        "size": 10
    }
    getPublishMessageInfo(publishMessage);
})(jQuery);

function changesearch() {
    $("tbody").html("");
    $("tfoot").html("");
    var client_id_scan = $('#client_id_scan').val();
    var username_scan = $('#username_scan').val();
    var topic_scan = $('#topic_scan').val();
    var publishMessage = {
        "client_id": client_id_scan,
        "username": username_scan,
        "topic": topic_scan,
        "page": 1,
        "size": 10
    }
    getPublishMessageInfo(publishMessage);
}

function getPublishMessageInfo(publishMessage) {
    $.ajax({
        type: "post",
        url: 'message/getPublishMessageInfo',
        data: JSON.stringify(publishMessage),
        contentType: "application/json",
        dataType: "json",
        success: function(data) {
            var list = data.data.resultsList;
            if (list.length == 0) {
                var tbody = "<tr><td colspan='7'>暂无数据</td></tr>";
                $("tbody").html(tbody);
            } else {
                var tbody = "";
                $.each(list,
                function(n, value) {
                    var trs = "";
                    trs += "<tr> <td> " + value.client_id + " </td>";
                    trs += "<td> " + value.username + " </td>";
                    trs += "<td> " + value.topic_msg + " </td>";
                    var topic = value.topic;
                    trs += "<td> " + topic + " </td>";
                    trs += "<td> " + value.qos + " </td>";
                    trs += "<td> " + value.receive_time + " </td>";
                    var payload = value.payload;
                    if(topic == "um_diag_sys_baseinfo"){
                    	trs += "<td><div class='button-group'> <a class='button border-main' href='javascript:void(0)' onclick='getPublishMessageDetailInfo(" + payload +", 1)'>详情</a></td></tr>";
                    }else if(topic == "um_diag_detect_connect"){
                    	trs += "<td><div class='button-group'> <a class='button border-main' href='javascript:void(0)' onclick='getPublishMessageDetailInfo(" + payload +", 2)'>详情</a></td></tr>";
                    }else if(topic == "um_diag_network_quality"){
                    	trs += "<td><div class='button-group'> <a class='button border-main' href='javascript:void(0)' onclick='getPublishMessageDetailInfo(" + payload +", 3)'>详情</a></td></tr>";
                    }else if(topic == "um_diag_network_topology"){
                    	trs += "<td><div class='button-group'> <a class='button border-main' href='javascript:void(0)' onclick='getPublishMessageDetailInfo(" + payload +", 4)'>详情</a></td></tr>";
                    }else if(topic == "um_diag_network_restart"){
                    	trs += "<td><div class='button-group'> <a class='button border-main' href='javascript:void(0)' onclick='getPublishMessageDetailInfo(" + payload +", 5)'>详情</a></td></tr>";
                    }else if(topic == "um_mon_get_platform_static_info"){
                    	trs += "<td><div class='button-group'> <a class='button border-main' href='javascript:void(0)' onclick='getPublishMessageDetailInfo(" + payload +", 6)'>详情</a></td></tr>";
                    }else if(topic == "um_mon_get_platform_dynamic_info"){
                    	trs += "<td><div class='button-group'> <a class='button border-main' href='javascript:void(0)' onclick='getPublishMessageDetailInfo(" + payload +", 7)'>详情</a></td></tr>";
                    }else if(topic == "um_mon_get_wan_info"){
                    	trs += "<td><div class='button-group'> <a class='button border-main' href='javascript:void(0)' onclick='getPublishMessageDetailInfo(" + payload +", 8)'>详情</a></td></tr>";
                    }else if(topic == "um_mon_get_lan_info"){
                    	trs += "<td><div class='button-group'> <a class='button border-main' href='javascript:void(0)' onclick='getPublishMessageDetailInfo(" + payload +", 9)'>详情</a></td></tr>";
                    }else if(topic == "um_mon_get_wlan_radio_info"){
                    	trs += "<td><div class='button-group'> <a class='button border-main' href='javascript:void(0)' onclick='getPublishMessageDetailInfo(" + payload +", 10)'>详情</a></td></tr>";
                    }else if(topic == "um_mon_get_wlan_ssid_info"){
                    	trs += "<td><div class='button-group'> <a class='button border-main' href='javascript:void(0)' onclick='getPublishMessageDetailInfo(" + payload +", 11)'>详情</a></td></tr>";
                    }else if(topic == "um_mon_get_device_brief_info"){
                    	trs += "<td><div class='button-group'> <a class='button border-main' href='javascript:void(0)' onclick='getPublishMessageDetailInfo(" + payload +", 12)'>详情</a></td></tr>";
                    }else if(topic == "um_mon_get_device_detailed_info"){
                    	trs += "<td><div class='button-group'> <a class='button border-main' href='javascript:void(0)' onclick='getPublishMessageDetailInfo(" + payload +", 13)'>详情</a></td></tr>";
                    }else{
                    	trs += "<td> " + payload + " </td></tr>";
                    }
                    tbody += trs;
                });
                $("tbody").html(tbody);
                var pageNo = data.data.currentPages;
                var pages = data.data.pages;
                var hasFirst = data.data.hasFirst;
                var hasLast = data.data.hasLast;
                var hasNext = data.data.hasNext;
                var hasPrevious = data.data.hasPrevious;
                var tfoot = "<tr><td colspan='7'><div class='pagelist'>"
                if (hasFirst == true) {
                    tfoot += "<a href='javascript:void(0)' onclick='getPageInfo(1)'>首页</a>";
                }
                if (hasPrevious == true) {
                    var number = Number(pageNo) - Number(1);
                    tfoot += "<a href='javascript:void(0)' onclick='getPageInfo(" + number + ")'>上一页</a>";
                }
                tfoot += "<span class='current'>" + pageNo + "</span>";
                if (hasNext == true) {
                    var number = Number(pageNo) + Number(1);
                    tfoot += "<a href='javascript:void(0)' onclick='getPageInfo(" + number + ")'>下一页</a>";
                }
                if (hasLast == true) {
                    tfoot += "<a href='javascript:void(0)' onclick='getPageInfo(" + pages + ")'>尾页</a>";
                }
                tfoot += "</div></td></tr>";
                $("tfoot").html(tfoot);
            }
        },
        error: function() {
            alert(data.resultMsg);
        }
    });
}

function getPublishMessageDetailInfo(payload, topic) {
	var json = JSON.stringify(payload);
	sessionStorage.setItem("payload", json);
	if(topic == 1){
		window.location.href='publishMessageDetail/um_diag_sys_baseinfo.html';
	}else if(topic == 2){
		window.location.href='publishMessageDetail/um_diag_detect_connect.html';
	}else if(topic == 3){
		window.location.href='publishMessageDetail/um_diag_network_quality.html';
	}else if(topic == 4){
		window.location.href='publishMessageDetail/um_diag_network_topology.html';
	}else if(topic == 5){
		window.location.href='publishMessageDetail/um_diag_network_restart.html';
	}else if(topic == 6){
		window.location.href='publishMessageDetail/um_mon_get_platform_static_info.html';
	}else if(topic == 7){
		window.location.href='publishMessageDetail/um_mon_get_platform_dynamic_info.html';
	}else if(topic == 8){
		window.location.href='publishMessageDetail/um_mon_get_wan_info.html';
	}else if(topic == 9){
		window.location.href='publishMessageDetail/um_mon_get_lan_info.html';
	}else if(topic == 10){
		window.location.href='publishMessageDetail/um_mon_get_wlan_radio_info.html';
	}else if(topic == 11){
		window.location.href='publishMessageDetail/um_mon_get_wlan_ssid_info.html';
	}else if(topic == 12){
		window.location.href='publishMessageDetail/um_mon_get_device_brief_info.html';
	}else{
		window.location.href='publishMessageDetail/um_mon_get_device_detailed_info.html';
	}
}

function getPageInfo(page) {
    $("tbody").html("");
    $("tfoot").html("");
    var client_id_scan = $('#client_id_scan').val();
    var username_scan = $('#username_scan').val();
    var topic_scan = $('#topic_scan').val();
    var publishMessage = {
        "client_id": client_id_scan,
        "username": username_scan,
        "topic": topic_scan,
        "page": page,
        "size": 10
    }
    getPublishMessageInfo(publishMessage);
}