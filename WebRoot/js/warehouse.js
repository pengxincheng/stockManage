function initTable() {

    $.ajax({
        url: '../../warehouse/list',// 跳转到 action

        data: {
            "areaName": $("#areaName").val()
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
                        destroy: false,   //是否允许破坏表
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
                                "data": "warehouseName",
                                "class": "align-center"
                            },
                            {
                                "data": "address",
                                "class": "align-center"
                            },
                            {
                                "data": "desc",
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
                                // 定义操作列,######以下是重点########
                                "targets": 6,// 操作按钮目标列
                                "data": null,
                                "render": function (data,
                                                    type, row) {
                                    var id = row.id;
                                    var html = " <a type='button' class='btn btn-sm btn-info btn-white btn-op-ths' title='编辑' href='getAreaDetail.do?id="
                                        + id
                                        + "'><i class='ace-icon fa fa-edit'></i></a>"
                                    html += "<button type='button' class='btn btn-sm btn-danger btn-white btn-op-ths'  title='删除' onclick='delArea(" + id + ")'><i class='ace-icon fa fa-trash-o'></i></button>"
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
function delArea(id) {
    if (confirm("确定删除吗？")) {
        location.href = "delArea.do?id=" + id;
        alert("操作成功！");
    }
}

