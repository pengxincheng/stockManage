function initTable() {

    $.ajax({
        url: '../../user/list',// 跳转到 action

        data: {
            "user.userName": $("#userName").val(),
            "user.userAlias": $("#userAlias").val(),
            "user.userType":"customer"
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
                                "data": "userId",
                                "render": function (data, type, full, meta) {
                                    return '<label class="pos-rel"><input type="checkbox" class="ace" class="ace"  value="'
                                        + data
                                        + '"/><span class="lbl"></span></label>'
                                },

                            },
                            {
                                "data": "userAlias",
                                "class": "align-center"
                            },
                            {
                                "data": "userName",
                                "class": "align-center"
                            },
                            {
                                "data": "tel",
                                "class": "align-center"
                            },
                            {
                                "data": "address",
                                "class": "align-center"
                            },
                            {
                                "data": "remark",
                                "class": "align-center"
                            },
                            {
                                "data": "createUserId",
                                "class": "align-center"
                            },
                            {
                                "data": "createTime",
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
                                    var id = row.userId;
                                    var html = " <a type='button' class='btn btn-sm btn-info btn-white btn-op-ths' title='编辑' href='addCustomer.jsp?userId="
                                        + id
                                        + "'><i class='ace-icon fa fa-edit'></i></a>";
                                    html += "<button type='button' class='btn btn-sm btn-danger btn-white btn-op-ths' title='删除' name='" + id + "' onclick='delArea(this)'><i class='ace-icon fa fa-trash-o'></i></button>"
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
                location.href = "customerList.jsp";
            }
        })
    }else {
        alert("操作失败！");
    }
}

