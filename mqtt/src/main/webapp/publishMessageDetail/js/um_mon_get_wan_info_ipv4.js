$(document).ready(function() {
	var info = sessionStorage.getItem("info");
	var obj = JSON.parse(info);
	if (obj.hasOwnProperty("ipv4_status")) {
		$("#ipv4_status").attr("value", obj["ipv4_status"]);
	}
	if (obj.hasOwnProperty("ipv4_addr")) {
		$("#ipv4_addr").prop("value", obj["ipv4_addr"]);
	}
	if (obj.hasOwnProperty("ipv4_netmask")) {
		$("#ipv4_netmask").prop("value", obj["ipv4_netmask"]);
	}
	if (obj.hasOwnProperty("ipv4_gateway")) {
		$("#ipv4_gateway").prop("value", obj["ipv4_gateway"]);
	}
	if (obj.hasOwnProperty("ipv4_dns1")) {
		$("#ipv4_dns1").prop("value", obj["ipv4_dns1"]);
	}
	if (obj.hasOwnProperty("ipv4_dns2")) {
		$("#ipv4_dns2").prop("value", obj["ipv4_dns2"]);
	}
});