$(document).ready(function() {
	var payload = sessionStorage.getItem("payload");
	var obj = JSON.parse(payload);
	if (obj.hasOwnProperty("code")) {
		$("#code").attr("value", obj["code"]);
	}
	if (obj.hasOwnProperty("errormsg")) {
		$("#errormsg").prop("value", obj["errormsg"]);
	}
	if (obj.hasOwnProperty("ipv4_addr")) {
		$("#ipv4_addr").prop("value", obj["ipv4_addr"]);
	}
	if (obj.hasOwnProperty("netmask")) {
		$("#netmask").prop("value", obj["netmask"]);
	}
	if (obj.hasOwnProperty("ipv6_addr")) {
		$("#ipv6_addr").prop("value", obj["ipv6_addr"]);
	}
	if (obj.hasOwnProperty("mac")) {
		$("#mac").prop("value", obj["mac"]);
	}
});