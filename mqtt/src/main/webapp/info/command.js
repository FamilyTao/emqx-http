(function($) {
	$("#macs").attr("value", "");
    getCommandInfo();
})(jQuery);

$(document).ready(function() {
	var urlinfo = window.location.href;
	if(urlinfo.indexOf("?") != -1 ){
		var key = urlinfo.split("?")[1];
		if(key.indexOf("allmacsinfo") != -1 ){
			var macs = key.split("=")[1];
			$("#macs").attr("value", macs);	
		}
	}
});

function getCommandInfo() {
    $.get("publish/getPublishTopicInfo",
    function(data) {
        var list = data.data;
        var sign_topic = sessionStorage.getItem("sign_topic");
        if (isEmpty(macs)) {
        	$.each(list, function(n, value) {
            	$("#command_type").append("<option value='" + value.topic + "'>" + value.topic_msg + "</option>");
        	});
        }else{
        	$.each(list, function(n, value) {
        		if(value.topic == sign_topic){
        			$("#command_type").append("<option selected = 'selected'  value='" + value.topic + "'>" + value.topic_msg + "</option>");
        		}else{
        			$("#command_type").append("<option value='" + value.topic + "'>" + value.topic_msg + "</option>");
        		}
        	});
        }
        if (sign_topic == "um_diag_network_restart") {
            $("#network_show").attr("style", "display:block;");
        } else {
            $("#network_show").attr("style", "display:none;");
        }
        if (sign_topic == "um_mon_get_device_brief_info") {
            $("#active_status").attr("style", "display:block;");
        } else {
            $("#active_status").attr("style", "display:none;");
        }
        //网络检测连通性
        if (sign_topic == "um_diag_detect_connect") {
            $("#connect_show").attr("style", "display:block;");
        } else {
            $("#connect_show").attr("style", "display:none;");
        }
        
    });
}

$(function() {
    $("#command_type").change(function() { 
        if ($(this).val() == "um_diag_network_restart") {
            $("#network_show").attr("style", "display:block;");
        } else {
            $("#network_show").attr("style", "display:none;");
        }
        if ($(this).val() == "um_mon_get_device_brief_info") {
            $("#active_status").attr("style", "display:block;");
        } else {
            $("#active_status").attr("style", "display:none;");
        }
        //网络检测连通性
        if ($(this).val() == "um_diag_detect_connect") {
            $("#connect_show").attr("style", "display:block;");
        } else {
            $("#connect_show").attr("style", "display:none;"); //隐藏div
        }
    });
 
    $("#publish").click(function() { //动态注册提交命令
        var sign_topic = $("#command_type option:selected").val();
        var network = $("#network_show option:selected").val();
        var connect = $("#connect_show option:selected").val();
        var active_status = $("#active_status option:selected").val();
        var qos = $("#qos_level option:selected").val();
        var macs = $('#macs').val();
        if (isEmpty(macs)) {
    		alert("发送对象不能为空");
    		return;
		}
        var publishInfo = {
            "sign_topic": sign_topic,
            "network": network,
            "conncet": connect,
            "qos": qos,
            "active_status": active_status,
            "macs": macs
        }
        publishTopic(publishInfo);
    });
});

function isEmpty(obj){
    if(typeof obj == "undefined" || obj == null || obj == ""){
        return true;
    }else{
        return false;
    }
}


function publishTopic(publishInfo) { 	
    $.ajax({
        type: "post",
        url: 'publish/publishTopicInfo',	//去controller
        data: JSON.stringify(publishInfo),
        contentType: "application/json",
        async:false, 
        dataType: "json",
        success: function(data) {
            alert(data.resultMsg);
        },
        error: function() {
            alert(data.resultMsg);
        }
    });
}

function getMacInfo() {
	var sign_topic = $("#command_type option:selected").val();
	sessionStorage.setItem("sign_topic", sign_topic);
	if (sign_topic == "um_mon_get_device_detailed_info") {
		window.location.href='maclist.html?device_status=1';
	}else{
		window.location.href='maclist.html?device_status=0';
	}
}