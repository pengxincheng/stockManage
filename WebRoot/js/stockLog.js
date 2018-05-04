function initTable() {

    var id,type;
    if (getUrlParam("stockLog.stockId")) {
        id = getUrlParam("stockLog.stockId");
    }
    if (getUrlParam("type")) {
        type = getUrlParam("type");
    }
    $.ajax({
        url: '../../stockLog/list',// 跳转到 action

        data: {
            "stockLog.stockId":id,
            "stockLog.logType":type,
            "stockLog.productId":$("#productId").val(),
            "stockLog.wareHouseId":$("#warehouseId").val(),
            "stockLog.userId":$("#userId").val()
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
                                "data": "id",
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
                                "data": "warehouse.name",
                                "class": "align-center"
                            },
                            {
                                "data": "logType",
                                "class": "align-center"
                            },
                            {
                                "data": "totalCount",
                                "class": "align-center"
                            },
                            {
                                "data": "totalMoney",
                                "class": "align-center"
                            },
                            {
                                "data": "createTime",
                                "class": "align-center"
                            },
                            {
                                "data": "user.userAlias",
                                "class": "align-center"
                            },
                            {
                                "data": null,
                                "class": "hidden-xs align-center col-op-ths"
                            }],
                        columnDefs: [// 设置列的属性，此处设置第一列不排序
                            {
                                "bSortable": false,
                                "aTargets": [0]
                            },
                            {
                                "class": "tn",
                                "targets": [0]
                            },
                            {
                                // 定义操作列,######以下是重点########
                                "targets": 8,// 操作按钮目标列
                                "data": null,
                                "render": function (data,
                                                    type, row) {
                                    var id = row.id;
                                    var type = row.logType;
                                    var html
                                    if(type == '入库'){
                                        html = " <a type='button' class='btn btn-sm btn-info btn-white btn-op-ths' title='商品详情' href='../item/inItemList.jsp?inId="
                                            + id
                                            + "'><i class='ace-icon fa fa-search'></i></a>";
                                    }else{
                                        html = " <a type='button' class='btn btn-sm btn-info btn-white btn-op-ths' title='商品详情' href='../item/inItemList.jsp?outId="
                                            + id
                                            + "'><i class='ace-icon fa fa-search'></i></a>";
                                    }

                                  return html;
                                }
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
//导出
$("#btnExport").click(function () {
    var params = $("#form1").serialize();
    location.href = "../../stockLog/exportOutStock?"+params;
});