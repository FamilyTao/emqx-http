$(document).ready(function() {
	var info = sessionStorage.getItem("info");
	var obj = JSON.parse(info);
	if (obj.hasOwnProperty("ipv6_status")) {
		$("#ipv6_status").attr("value", obj["ipv6_status"]);
	}
	if (obj.hasOwnProperty("ipv6_addr")) {
		$("#ipv6_addr").prop("value", obj["ipv6_addr"]);
	}
	if (obj.hasOwnProperty("ipv6_prefixlength")) {
		$("#ipv6_prefixlength").prop("value", obj["ipv6_prefixlength"]);
	}
	if (obj.hasOwnProperty("ipv6_gateway")) {
		$("#ipv6_gateway").prop("value", obj["ipv6_gateway"]);
	}
	if (obj.hasOwnProperty("ipv6_dns1")) {
		$("#ipv6_dns1").prop("value", obj["ipv6_dns1"]);
	}
	if (obj.hasOwnProperty("ipv6_dns2")) {
		$("#ipv6_dns2").prop("value", obj["ipv6_dns2"]);
	}
	if (obj.hasOwnProperty("ipv6_prefix")) {
		$("#ipv6_prefix").prop("value", obj["ipv6_prefix"]);
	}
});