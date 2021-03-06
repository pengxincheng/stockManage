<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <!--浏览器兼容性设置-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>Empty Page - Ace Admin</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="../../assets/css/bootstrap.css"/>
    <link rel="stylesheet" href="../../components/font-awesome/css/font-awesome.css"/>

    <!-- page plugin css -->

    <!-- ace styles -->
    <link rel="stylesheet" href="../../assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="../../assets/css/ace-part2.css" class="ace-main-stylesheet"/>
    <![endif]-->
    <link rel="stylesheet" href="../../assets/css/ace-skins.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="../../assets/css/ace-ie.css"/>
    <![endif]-->

    <!--THS CSS 插件-->
    <link rel="stylesheet" href="../../assets/css/ths.css"/>

    <!-- 自己写的CSS，请放在这里 -->
    <style type="text/css">

    </style>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="../../components/html5shiv/dist/html5shiv.min.js"></script>
    <script src="../../components/respond/dest/respond.min.js"></script>
    <![endif]-->
</head>

<body class="no-skin">

<div class="main-container" id="main-container">
    <div class="main-content">
        <div class="main-content-inner fixed-page-header fixed-40">
            <div id="breadcrumbs" class="breadcrumbs">
                <ul class="breadcrumb">
                    <li class="active"><i class="fa fa-arrow-right"></i>商品类别列表</li>
                </ul><!-- /.breadcrumb -->
            </div>
        </div>
        <div class="main-content-inner padding-page-content">
            <div class="page-content">
                <div class="space-4"></div>
                <div class="row">
                    <div class="col-xs-12">
                        <form class="form-horizontal" role="form" id="form1" action="index.html" method="post">
                            <div class="form-group">
                                <label class="col-sm-1 control-label no-padding-right" for="name">
                                    商品名称
                                </label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" placeholder="商品名称" id="name" name="name"/>
                                </div>
                                <label class="col-sm-1 control-label no-padding-right" for="type">商品类别</label>
                                <div class="col-sm-2">
                                    <select class="form-control" id="type" name="type"
                                            data-validation-engine="validate[required]">
                                        <option value="" selected="selected">-请选择-</option>
                                    </select>
                                </div>
                                <div class="col-sm-5 col-lg-8 col-md-5 align-right">
                                    <div class="space-4 hidden-lg hidden-md hidden-sm"></div>
                                    <button type="button" class="btn btn-info btn-default-ths" id="btnSearch"
                                            onclick="initTable()">
                                        <i class="ace-icon fa fa-search"></i>
                                        搜索
                                    </button>
                                </div>

                            </div>


                            <hr class="no-margin">
                            <div class="page-toolbar align-right list-toolbar">
                                <button type="button" class="btn btn-xs btn-inverse btn-xs-ths" id="btnAdd"
                                        data-ths-href="addOrUpdateProduct.jsp">
                                    <i class="ace-icon fa fa-plus"></i>
                                    添加
                                </button>
                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    <!-- div.table-responsive -->

                                    <!-- div.dataTables_borderWrap table-striped -->
                                    <div>
                                        <table id="example" class="table table-striped  table-bordered table-hover">
                                            <thead>
                                            <tr>
                                                <th class="center">
                                                    <label class="pos-rel">
                                                        <input type="checkbox" class="ace"/>
                                                        <span class="lbl"></span>
                                                    </label>
                                                </th>
                                                <th class="align-center">商品名称</th>
                                                <th class="align-center">类别名称</th>
                                                <th class="align-center">备注</th>
                                                <th class="align-center">创建人</th>
                                                <th class="align-center">创建时间</th>
                                                <th class="align-center hidden-xs"><i class="ace-icon fa fa-wrench"></i>
                                                    操作
                                                </th>
                                            </tr>
                                            </thead>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div><!--/.main-content-inner-->
    </div><!-- /.main-content -->
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="../../components/jquery/dist/jquery.js"></script>
<!-- <![endif]-->

<!--[if IE]>
<script src="../../components/jquery.1x/dist/jquery.js"></script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='components/_mod/jquery.mobile.custom/jquery.mobile.custom.js'>" + "<" + "/script>");
</script>
<script src="../../components/bootstrap/dist/js/bootstrap.js"></script>

<!-- page specific plugin scripts -->
<script src="../../components/datatables/jquery.dataTables.min.js"></script>
<script src="../../components/datatables/dataTables.bootstrap.min.js"></script>
<script src="../../components/datatables/dataTables.fixedColumns.min.js"></script>
<script src="../../components/datatables/dataTables.fixedHeader.min.js"></script>
<script src="../../components/datatables/dataTables.select.min.js"></script>
<script src="../../components/datatables/dataTables.responsive.min.js"></script>

<!--ace script-->
<script src="../../assets/js/src/ace.js"></script>

<!-- 自己写的JS，请放在这里 -->
<script src="../../js/product.js"></script>
<script type="text/javascript">
    initTable();
    jQuery(function ($) {
        //为工具条添加点击事件
        $(".page-toolbar>button").on(ace.click_event, function (e) {
            if ($(this).data("ths-href")) {
                window.location.href = ($(this).data("ths-href"));
            }
        });

        //为表格操作列添加点击事件
        $(".col-op-ths>button").on(ace.click_event, function (e) {
            if ($(this).data("ths-href")) {
                window.location.href = ($(this).data("ths-href"));
            }
        });

        //为表格添加排序事件
        $("#simple-table>thead>tr>th").on(ace.click_event, function (e) {
            var $i = $(this).find("i");
            //console.log($i.hasClass("fa-sort-asc"));
            if ($i && $i.hasClass("fa-sort-asc")) {
                $i.removeClass("fa-sort-asc").addClass("fa-sort-desc");
            }
            else if ($i && $i.hasClass("fa-sort-desc")) {
                $i.removeClass("fa-sort-desc").addClass("fa-sort-asc");
            }
        })

        //And for the first simple table, which doesn't have TableTools or dataTables
        //select/deselect all rows according to table header checkbox
        var active_class = 'active';
        $('#example > thead > tr > th input[type=checkbox]').eq(0).on('click', function () {
            var th_checked = this.checked;//checkbox inside "TH" table header

            $(this).closest('table').find('tbody > tr').each(function () {
                var row = this;
                if (th_checked) $(row).addClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', true);
                else $(row).removeClass(active_class).find('input[type=checkbox]').eq(0).prop('checked', false);
            });
        });

        //select/deselect a row when the checkbox is checked/unchecked
        $('#example').on('click', 'td input[type=checkbox]', function () {
            var $row = $(this).closest('tr');
//            if($row.is('.detail-row ')) return;
            if (this.checked) $row.addClass(active_class);
            else $row.removeClass(active_class);
        });

        $.get("../../productType/list",{},function (data) {
            if (data.resultJson.result == 'SUCCESS') {
                var json =  data.resultJson.content;
                $.each(json, function (i, item) {
                    jQuery("#type").append("<option value="+ item.typeId+">"+ item.typeName+"</option>");
                });
            }
        })
    });

</script>
</body>
</html>
