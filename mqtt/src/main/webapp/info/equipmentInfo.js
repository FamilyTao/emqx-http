(function($) {
    var equipment = {
        "mac": "",
        "connect_status": "2",
        "page": 1,
        "size": 10
    }
    getEquipmentInfo(equipment);
})(jQuery);

function changesearch() {
    $("tbody").html("");
    $("tfoot").html("");
    var connect_status = $("#connect_status_seclect option:selected").val();
    var mac = $('#mac_scan').val();
    var equipment = {
        "mac": mac,
        "connect_status": connect_status,
        "page": 1,
        "size": 10
    }
    getEquipmentInfo(equipment);
}

function getEquipmentInfo(equipment) {
    $.ajax({
        type: "post",
        url: 'equipment/getEquipmentInfo',
        data: JSON.stringify(equipment),
        contentType: "application/json",
        dataType: "json",
        success: function(data) {
            var list = data.data.resultsList;
            if (list.length == 0) {
                var tbody = "<tr><td colspan='2'>暂无数据</td></tr>";
                $("tbody").html(tbody);
            } else {
                var tbody = "";
                $.each(list,
                function(n, value) {
                    var trs = "";
                    trs += "<tr> <td> " + value.mac + " </td> <td>";
                    var connect_status = value.connect_status;
                    if (connect_status == 0) {
                        trs += "<a class='button border-red'>未连接</a></td></tr>";
                    } else {
                        trs += "<a class='button border-main'>已连接</a></td></tr>";
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
                var tfoot = "<tr><td colspan='2'><div class='pagelist'>"
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
            alert("查询失败！");
        }
    });
}

function getPageInfo(page) {
    $("tbody").html("");
    $("tfoot").html("");
    var connect_status = $("#connect_status_seclect option:selected").val();
    var mac = $('#mac_scan').val();
    var equipment = {
        "mac": mac,
        "connect_status": connect_status,
        "page": page,
        "size": 10
    }
    getEquipmentInfo(equipment);
}