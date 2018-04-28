<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <!--浏览器兼容性设置-->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>学生宿舍管理系统</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="assets/css/bootstrap.css"/>
    <link rel="stylesheet" href="components/font-awesome/css/font-awesome.css"/>
 	<!-- page plugin css -->
    <link rel="stylesheet" href="components/_mod/jQuery-Validation-Engine/validationEngine.jquery.css"/>
    <link rel="stylesheet" href="components/_mod/jQuery-Validation-Engine/template.css"/>
    <!-- ace styles -->
    <link rel="stylesheet" href="assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="assets/css/ace-part2.css" class="ace-main-stylesheet"/>
    <![endif]--
    <link rel="stylesheet" href="assets/css/ace-skins.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="assets/css/ace-ie.css"/>
    <![endif]-->
    <link rel="stylesheet" href="assets/css/ths-menu.css"/>

    <!-- 自己写的CSS，请放在这里 -->
    <style type="text/css">

    </style>

    <!-- ace settings handler -->
    <script src="assets/js/ace-extra.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="/components/html5shiv/dist/html5shiv.min.js"></script>
    <script src="/components/respond/dest/respond.min.js"></script>
    <![endif]-->
</head>

<body class="no-skin">
<!--新增条-->
<div class="ths-toolbar">
    <div class="row">
        <div class="col-sm-4 hidden-xs">欢迎您，${sessionScope.currentUser.userAlias}</div>
        <div class="col-xs-12 col-sm-8 ">
            <ul class=" pull-right" data-level="top">
                <!-- <li><i class="ace-icon fa fa-file-o grey bigger-100" title="待办事项"></i><span class="badge badge-warning">99</span></li>
                <li><i class="ace-icon fa fa-bullhorn grey bigger-110" title="通知"></i><span class="badge badge-success">2</span></li> -->
                <li><i class="ace-icon fa fa-user grey bigger-110" title="修改密码" onclick="changePassword()"></i></li>
                <li><a class="ace-icon fa fa-power-off grey bigger-110" title="注销" href="logout.do"></a></li>
            </ul>
        </div>
    </div>
</div>
<!--新增条-->
<!-- #section:basics/navbar.layout -->
<div id="navbar" class="navbar navbar-default ths-top-menu">
    <div class="navbar-container " id="navbar-container">
        <!-- #section:basics/sidebar.mobile.toggle 移动端 菜单选项-->
        <button data-target="#sidebarTop" data-toggle="collapse" type="button"
                class="pull-left navbar-toggle collapsed">
            <span class="sr-only">菜单</span>
            <i class="ace-icon fa fa-th white bigger-175"></i>
        </button>
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">模块</span>
            <i class="ace-icon fa fa-navicon white bigger-175"></i>
        </button>
    </div>
    <!-- /section:basics/sidebar.mobile.toggle -->
    <div class="navbar-header pull-left">
        <!-- #section:basics/navbar.layout.brand -->
        <a href="#" class="navbar-brand navbar-brand-line no-padding">
            <small>
                <i class="width_34"><img src="assets/images/haut.png"></i>
                <span>库存管理系统</span>
            </small>
        </a>
        <!-- /section:basics/navbar.toggle -->
    </div>
    <!-- #section:basics/navbar.dropdown -->
   <!-- .sidebar -->
    <!-- /section:basics/navbar.dropdown -->
</div><!-- /.navbar-container -->
<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
    <!-- #section:basics/sidebar -->
    <div id="sidebar" class="sidebar responsive  sidebar-fixed" >
        <ul class="nav nav-list">
            <li class="active open">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-th-large"></i>
                    <span class="menu-text">基础信息管理</span>
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <ul class="submenu">
                    <li class="active">
                        <a href="pages/role/roleList.jsp" target="main">
                            <i class="menu-icon fa fa-laptop"></i>
                            角色管理
                            <!-- <span class="badge badge-warning">5</span> -->
                        </a>
                        <b class="arrow"></b>
                    </li>
                    <li class="">
                        <a href="pages/warehouse/warehouseList.jsp" target="main">
                            <i class="menu-icon fa fa-bar-chart"></i>
                            仓库管理
                            <!-- <span class="badge badge-success">2</span> -->
                        </a>
                        <b class="arrow"></b>
                    </li>
                    <li class="">
                        <a href="pages/user/userList.jsp" target="main">
                            <i class="menu-icon fa fa-legal"></i>
                            用户管理
                        </a>
                        <b class="arrow"></b>
                    </li>
					 <li class="">
                        <a href="goToChangeRoomList.do" target="main">
                            <i class="menu-icon fa fa-area-chart"></i>
                            调换宿舍
                        </a>
                        <b class="arrow"></b>
                    </li>	
                    
                    <li class="">
                        <a href="goToCensusPage.do" target="main">
                            <i class="menu-icon fa fa-area-chart"></i>
                            卫生统计
                        </a>
                        <b class="arrow"></b>
                    </li>				
                </ul>
            </li>
            <li class="active open">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-reorder"></i>
                    <span class="menu-text">宿舍管理员</span>
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <b class="arrow"></b>
                <ul class="submenu">
					<li class="">
                        <a href="goToStuListForManager.do" target="main">
                            <i class="menu-icon fa fa-arrow-circle-right"></i>
                           		学生列表
                        </a>
                        <b class="arrow"></b>
                    </li>  
                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-th"></i>
                            <span class="menu-text">登记管理</span>
                            <b class="arrow fa fa-angle-down"></b>
                        </a>
                        <b class="arrow"></b>
                        <ul class="submenu">
                            <li class="">
                                <a href="goToVisitEnregisterList.do" target="main">
                                    <i class="menu-icon fa fa-list"></i>
                                    	来访登记
                                </a>
                                <b class="arrow"></b>
                            </li>
                            <li class="">
                                <a href="goToRegisterList.do" target="main">
                                    <i class="menu-icon fa fa-list"></i>
                                   		 晚归登记
                                </a>
                                <b class="arrow"></b>
                            </li>
                            <li class="">
                                <a href="goToRegisterListFroQueQin.do" target="main">
                                    <i class="menu-icon fa fa-list"></i>
                                   		 缺勤登记
                                </a>
                                <b class="arrow"></b>
                            </li>
                        </ul>
                    </li>
                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-th"></i>
                            <span class="menu-text">宿舍管理</span>
                            <b class="arrow fa fa-angle-down"></b>
                        </a>
                        <b class="arrow"></b>
                        <ul class="submenu">
                            <li class="">
                                <a href="goToListPageForManager.do" target="main">
                                    <i class="menu-icon fa fa-list"></i>
                                    	宿舍列表
                                </a>
                                <b class="arrow"></b>
                            </li> 
                            <li class="">
                                <a href="goToCheckHygieneList.do" target="main">
                                    <i class="menu-icon fa fa-list"></i>
                                   		 卫生检查
                                </a>
                                <b class="arrow"></b>
                            </li>
                            <li class="">
                                <a href="goToChangeRoomListForManager.do" target="main">
                                    <i class="menu-icon fa fa-list"></i>
                                   		 调换宿舍
                                </a>
                                <b class="arrow"></b>
                            </li>
                        </ul>
                    </li>
                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-th"></i>
                            <span class="menu-text">通知公告</span>
                            <b class="arrow fa fa-angle-down"></b>
                        </a>
                        <b class="arrow"></b>
                        <ul class="submenu">
                            <li class="">
                                <a href="findAllNotifies.do" target="main">
                                    <i class="menu-icon fa fa-list"></i>
                                    	通知公告
                                </a>
                                <b class="arrow"></b>
                            </li>                           
                            <li class="">
                                <a href="findAllNotifiesForUser.do" target="main">
                                    <i class="menu-icon fa fa-list"></i>
                                   		 通知公告列表
                                </a>
                                <b class="arrow"></b>
                            </li>                                                       
                        </ul>
                    </li>
                     <li class="">
                        <a href="goToRepairTasksListForManager.do" target="main">
                            <i class="menu-icon fa fa-arrow-circle-right"></i>
                           		维修处理
                        </a>
                        <b class="arrow"></b>
                    </li>   
                    <li class="">
                        <a href="goToStuApplyGoodsListForManager.do" target="main">
                            <i class="menu-icon fa fa-arrow-circle-right"></i>
                           		物品发放
                        </a>
                        <b class="arrow"></b>
                    </li>
                     <li class="">
                        <a href="goToCensusPageForManager.do" target="main">
                            <i class="menu-icon fa fa-arrow-circle-right"></i>
                           		卫生统计
                        </a>
                        <b class="arrow"></b>
                    </li>
                </ul>
            </li>
            <li class="active open">
                <a href="#" class="dropdown-toggle">
                    <i class="menu-icon fa fa-reorder"></i>
                    <span class="menu-text">管理员</span>
                    <b class="arrow fa fa-angle-down"></b>
                </a>
                <b class="arrow"></b>
                <ul class="submenu">
                    <li class="">
                        <a href="goToListPage.do" target="main">
                            <i class="menu-icon fa fa-arrow-circle-right"></i>
                            	宿舍区管理
                        </a>
                        <b class="arrow"></b>
                    </li>
                    <li class="">
                        <a href="goToBuildingList.do" target="main">
                            <i class="menu-icon fa fa-arrow-circle-right"></i>
                           		楼宇管理
                        </a>
                        <b class="arrow"></b>
                    </li>                 
                     <li class="">
                        <a href="goToRoomList.do" target="main">
                            <i class="menu-icon fa fa-arrow-circle-right"></i>
                           		宿舍管理
                        </a>
                        <b class="arrow"></b>
                    </li>   
                    <li class="">
                        <a href="goToStuList.do" target="main">
                            <i class="menu-icon fa fa-arrow-circle-right"></i>
                           		学生管理
                        </a>
                        <b class="arrow"></b>
                    </li>  
                    <li class="">
                        <a href="goToManagerList.do" target="main">
                            <i class="menu-icon fa fa-arrow-circle-right"></i>
                           		宿舍管理员列表
                        </a>
                        <b class="arrow"></b>
                    </li>
                    
                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-th"></i>
                            <span class="menu-text">统计信息</span>
                            <b class="arrow fa fa-angle-down"></b>
                        </a>
                        <b class="arrow"></b>
                        <ul class="submenu">
                          <li class="">
	                        <a href="goToCensusPageForAdmin.do" target="main">
	                            <i class="menu-icon fa fa-arrow-circle-right"></i>
	                           		卫生统计
	                        </a>
                        	<b class="arrow"></b>
                    		</li> 
                           
                            <li class="">
                                <a href="goToCensusRegister.do" target="main">
                                    <i class="menu-icon fa fa-arrow-circle-right"></i>
                                   		考勤统计
                                </a>
                                <b class="arrow"></b>
                            </li>                                                       
                        </ul>
                    </li>
                </ul>
            </li>
        </ul><!-- /.nav-list -->

        <!-- #section:basics/sidebar.layout.minimize -->
        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left "
               data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>

        <!-- /section:basics/sidebar.layout.minimize -->
    </div>

    <!-- /section:basics/sidebar -->
    <div class="main-content">
        <div id="main-content-inner" class="main-content-inner">
            <iframe id="main-content-iframe" name="main" frameBorder="0"
                    style="width:100%;border: none;overflow-x: hidden; overflow-y:auto"
                    scrolling="auto" src="findAllNotifies.do"></iframe>

        </div><!--/.main-content-inner-->
    </div><!-- /.main-content -->
</div><!-- /.main-container -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					修改密码
				</h4>
			</div>
			<form name="changePwd" id="changePwd">
			<div class="modal-body col-xs-12">
				<div class="row">
					<label class="col-sm-2 control-label no-padding-right" for="oldPwd">原密码</label>
                    <div class="col-sm-5">
                        <input type="password" data-validation-engine="validate[required]" class="form-control" placeholder="原密码" id="oldPwd" name="oldPwd"/>
                    </div>  
                 </div>
                 <div class="row"><br></div>
                 <div class="row">
					<label class="col-sm-2 control-label no-padding-right" for="newPwd">新密码</label>
                    <div class="col-sm-5">
                        <input type="password" data-validation-engine="validate[required]" class="form-control" placeholder="新密码" id="newPwd" name="newPwd"/>
                    </div>  
                 </div>
                  <div class="row"><br></div>
                 <div class="row">
					<label class="col-sm-2 control-label no-padding-right" for="newPwd1">确认密码</label>
                    <div class="col-sm-5">
                        <input type="password" data-validation-engine="validate[required]" class="form-control" placeholder="确认密码" id="newPwd1" name="newPwd1"/>
                    </div>  
                 </div>
			</div>
			</form>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary" id="submitChange">
					提交
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- basic scripts -->

<!--[if !IE]> -->
<script src="components/jquery/dist/jquery.js"></script>
<!-- <![endif]-->

<!--[if IE]>
<script src="components/jquery.1x/dist/jquery.js"></script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='../components/_mod/jquery.mobile.custom/jquery.mobile.custom.js'>" + "<" + "/script>");
</script>
<script src="components/bootstrap/dist/js/bootstrap.js"></script>

<!-- page specific plugin scripts -->
<!--表单验证jquery.validationEngine-->
<script src="components/_mod/jQuery-Validation-Engine/jquery.validationEngine-zh_CN.js" type="text/javascript"
        charset="utf-8"></script>
<script src="components/_mod/jQuery-Validation-Engine/jquery.validationEngine.js" type="text/javascript"
        charset="utf-8"></script>
<!-- ace scripts -->
<script src="assets/js/src/elements.scroller.js"></script>
<script src="assets/js/src/ace.js"></script>
<script src="assets/js/ace-elements.js"></script>
<script src="assets/js/src/ace.basics.js"></script>
<script src="assets/js/src/ace.sidebar.js"></script>
<script src="assets/js/src/ace.sidebar-scroll-1.js"></script>
<script src="assets/js/src/ace.submenu-hover.js"></script>

<!--
<script src="assets/js/src/ace.settings.js"></script>
-->

<!-- 自己写的JS，请放在这里 -->
<script type="text/javascript">
    function resizeIframe() {
        var browser=navigator.appName;
        var trim_Version=navigator.appVersion.split(";")[1].replace(/[ ]/g,"");
        var height = document.documentElement.clientHeight;
        if(browser=="Microsoft Internet Explorer"&& (trim_Version=="MSIE6.0" || trim_Version=="MSIE7.0"))
        {
            //不支持此IE版本，请升级浏览器
            //TODO: 提示用户升级
        }
        else
        {
            height -= document.getElementById('main-content-inner').offsetTop;
        }

        height -= 5;//边框的高度之和
        /* whatever you set your body bottom margin/padding to be */
        document.getElementById('main-content-iframe').style.height = height + "px";
    }

    document.getElementById('main-content-iframe').onload = resizeIframe;
    window.onresize = resizeIframe;


    jQuery(function ($) {
        /*
         //隐藏/显示左菜单
         $("#sidebar").removeClass("sidebar").hide();
         window.setTimeout(function () {
         $("#sidebar").addClass("sidebar").show();
         },3000);
         */
    });
    
function changePassword(){
	$('#myModal').modal();
}

$("#submitChange").click(function(){
	var oldPwd = $("#oldPwd").val();
	var newPwd = $("#newPwd").val();
	var newPwd1 = $("#newPwd1").val();
	if(oldPwd=="" || newPwd=="" || newPwd1==""){
		alert("不能为空");
		return;
	}
	if(newPwd != newPwd1){
		alert("两次密码不一样！");
		return;
	}
	 $.ajax({
		url : 'changePassword.do',// 跳转到 action  
		data : {
			"oldPwd" :oldPwd,
			"newPwd" : newPwd
		},
		type : "post",
		cache : false,
		async: false,
		dataType : "json",
		success : function(data) {
			if(data.result){
				alert(data.msg);
				window.location.reload();
			}
			else{
				alert(data.msg);
			}
		},
		error : function() {
			// view("异常！");  
			alert("异常！");
		}
	}); 
});

</script>


</body>
</html>
