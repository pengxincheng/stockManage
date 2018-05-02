function initTable() {

    $.ajax({
        url: '../../item/inList',// 跳转到 action

        data: {
            "user.userName": $("#userName").val(),
            "user.userAlias": $("#userAlias").val(),
            "user.roleId": $("#roleId").val(),
            "user.userType": "employee"
        },
        traditional: true,
        type: "post",
        cache: false,
        dataType: "json",
        success: function (data) {
            if (data.resultJson.result == 'SUCCESS') {
                $('#example tbody').html("");
                $('#example').DataTable(
                    {
                        destroy: true,   //是否允许破坏表
                        77: 6,// 每页显示多少条记录
                        searching: false,
                        lengthChange: true,
                        data: data.resultJson.content,
                        info: false,
                        lengthMenu: [6, 10, 15, 20],
                        pagingType: "full_numbers",
                        dom: "t<'ths-page'fl><'ths-pager'p>",
                        columns: [
                            {

                                "sClass": "align-center",
                                "data": "itemId",
                                "render": function (data, type, full, meta) {
                                    return '<label class="pos-rel"><input type="checkbox" class="ace" class="ace"  value="'
                                        + data
                                        + '"/><span class="lbl"></span></label>'
                                },

                            },
                            {
                                "data": "product.productName",
                                "class": "align-center"
                            },
                            {
                                "data": "product.type.typeName",
                                "class": "align-center"
                            },
                            {
                                "data": "supplier.userAlias",
                                "class": "align-center"
                            },
                            {
                                "data": "productTime",
                                "class": "align-center"
                            },
                            {
                                "data": "warehouse.name",
                                "class": "align-center"
                            },
                            {
                                "data": "inTime",
                                "class": "align-center"
                            },
                            {
                                "data": "inUser.userAlias",
                                "class": "align-center"
                            },
                            {
                                "data": "inPrice",
                                "class": "align-center"
                            },
                            {
                                "data": "outTime",
                                "class": "align-center"
                            },
                            {
                                "data": "outUserId",
                                "class": "align-center"
                            },
                            {
                                "data": "outPrice",
                                "class": "align-center"
                            },
                            {
                                "data": "itemStatus",
                                "class": "align-center"
                            },
                            {
                                "data": "remark",
                                "class": "align-center"
                            }],
                        columnDefs: [// 设置列的属性，此处设置第一列不排序
                            {
                                "bSortable": false,
                                "aTargets": [0]
                            },
                            {
                                "class": "tn",
                                "targets": [0]
                            }],

                        /*
                         * scrollX:500, fixedColumns: {
                         * leftColumns: 2 },
                         */
                        /*
                         * "order": [[ 3, 'asc' ], [ 4, 'desc'
                         * ]],
                         */
                        language: {
                            paginate: {
                                next: "下一页",
                                previous: "上一页",
                                first: "首页",
                                last: "尾页"
                            },
                            lengthMenu: "每页 _MENU_ 条",
                            zeroRecords: "没有找到相关记录。"
                        }
                    });
            }
            else {
                alert("暂无记录！");
            }
        },
        error: function () {
            // view("异常！");
            alert("异常！");
        }
    });
}
function delArea(obj) {
    var id = obj.name;
    if (confirm("确定删除吗？")) {
        $.post("../../user/delete", {"id": id}, function (data) {
            if (data.resultJson.result = 'SUCCESS') {
                alert("操作成功！");
                location.href = "userList.jsp";
            }
        })
    } else {
        alert("操作失败！");
    }
}

//加载类型
function getType() {
    $.get("../../productType/list", {}, function (data) {
        if (data.resultJson.result == 'SUCCESS') {
            var json = data.resultJson.content;
            $.each(json, function (i, item) {
                jQuery("#typeId").append("<option value=" + item.typeId + ">" + item.typeName + "</option>");
            });
        }

    })
}

//加载商品
$("#typeId").change(function () {
    var params = {"product.productType": $("#typeId").val()};
    $.get("../../product/list", params, function (data) {
        if (data.resultJson.result == 'SUCCESS') {
            var json = data.resultJson.content;
            $("#productId").html('');
            $("#productId").append("<option value='' selected='selected'>-请选择-</option>");
            $.each(json, function (i, item) {
                $("#productId").append("<option value=" + item.productId + ">" + item.productName + "</option>");
            });
        }
    })
});

//供应商或者客户
function getBussinessUser(userType) {
    var params = {"user.userType": userType};
    $.get("../../user/list", params, function (data) {
        if (data.resultJson.result == 'SUCCESS') {
            var json = data.resultJson.content;
            $.each(json, function (i, item) {
                $("#customerId").append("<option value=" + item.userId + ">" + item.userAlias + "</option>");
            });
        }

    })
}

function getWarehouse() {
    var params = {};
    $.get("../../warehouse/list", params, function (data) {
        if (data.resultJson.result == 'SUCCESS') {
            var json = data.resultJson.content;
            $.each(json, function (i, item) {
                $("#warehouseId").append("<option value=" + item.id + ">" + item.name + "</option>");
            });
        }

    })
}
