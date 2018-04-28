<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>库存管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="assets/css/bootstrap.css" />
    <link rel="stylesheet" href="components/font-awesome/css/font-awesome.css" />

    <!-- text fonts -->
    <link rel="stylesheet" href="assets/css/ace-fonts.css" />

    <!-- ace styles -->
    <link rel="stylesheet" href="assets/css/ace.css" />

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="assets/css/ace-part2.css" />
    <![endif]-->

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="assets/css/ace-ie.css" />
    <![endif]-->

    <!--THS CSS 插件-->
    <link rel="stylesheet" href="assets/css/ths.css"/>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="components/html5shiv/dist/html5shiv.min.js"></script>
    <script src="components/respond/dest/respond.min.js"></script>
    <![endif]-->
</head>

<body class="login-layout light-login">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1>
                            <i class="ace-icon fa fa-th-large green"></i>
                            <span class="red"></span>
                            <span class="grey" id="id-text2">库存管理系统</span>
                        </h1>
                        
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="ace-icon fa fa-coffee green"></i>
                                        登录
                                    </h4>

                                    <div class="space-6"></div>

                                    <form id="login" name="login">
                                        <fieldset>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input id="username" name="user.userName" type="text" class="form-control" placeholder="用户名" required autofocus oninvalid="setCustomValidity('请输入用户名')" oninput="setCustomValidity('')"/>
															<i class="ace-icon fa fa-user"></i>
														</span>
                                            </label>

                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" name="user.password" class="form-control" placeholder="密码" required/>
															<i class="ace-icon fa fa-lock"></i>
														</span>
                                            </label>

                                            <div class="space"></div>

                                            <div class="clearfix">
                                               
                                                <button id="btnLogin" type="button" class="width-35 pull-right btn btn-sm btn-primary btn-xs-ths line-height-150">
                                                    <i class="ace-icon fa fa-key"></i>
                                                    <span class="bigger-110">登录</span>
                                                </button>
                                            </div>

                                            <div class="space-4"></div>
                                        </fieldset>


                                        <div id="dError"  style="display:none;" class="alert alert-block alert-danger width-100">
                                            <i class="ace-icon fa fa-times-circle red2"></i>
                                            用户名或密码错误
                                        </div>
                                    </form>

                                    <div class="social-or-login center">
                                        <span class="bigger-110"></span>
                                    </div>
                                    <div class="space-6"></div>
                                </div><!-- /.widget-main -->               
                            </div><!-- /.widget-body -->
                        </div><!-- /.login-box -->
                    </div><!-- /.position-relative -->

                </div>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.main-content -->
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script src="components/jquery/dist/jquery.js"></script>

<!-- <![endif]-->

<!--[if IE]>
<script src="components/jquery.1x/dist/jquery.js"></script>
<![endif]-->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='components/_mod/jquery.mobile.custom/jquery.mobile.custom.js'>"+"<"+"/script>");
</script>
<script src="assets/js/src/ace.js"></script>
<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function($) {
        $(document).on(ace.click_event, '.toolbar a[data-target]', function(e) {
            e.preventDefault();
            var target = $(this).data('target');
            $('.widget-box.visible').removeClass('visible');//hide others
            $(target).addClass('visible');//show target
        });

        $("#btnLogin").on(ace.click_event,function (e) {
        	var param =  $("#login").serialize()
        	$.post("user/login",param,function(data){
        		if(data.resultJson.result == 'SUCCESS'){
        			window.location.href="index.jsp";
        		}
        		else{
        			$("#dError").show();	
        		}
        	});
            
        });

        $("#txtUserName").on("focus",function(e){
            $("#dError").hide();
        });
    });

</script>
</body>
</html>
