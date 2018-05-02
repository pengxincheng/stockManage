function initTable() {

    $.ajax({
        url: '../../role/list',// 跳转到 action

        data: {
            "role.roleName": $("#roleName").val(),
            "role.roleCode": $("#roleCode").val()
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
                                "data": "roleName",
                                "class": "align-center"
                            },
                            {
                                "data": "roleCode",
                                "class": "align-center"
                            },
                            {
                                "data": "createTime",
                                "class": "align-center"
                            }],
                        columnDefs: [// 设置列的属性，此处设置第一列不排序
                           ],

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

