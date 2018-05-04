<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    <title></title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="../../assets/css/bootstrap.css"/>
    <link rel="stylesheet" href="../../components/font-awesome/css/font-awesome.css"/>

    <!-- page plugin css -->
    <link rel="stylesheet" href="../../components/_mod/jQuery-Validation-Engine/validationEngine.jquery.css"/>
    <link rel="stylesheet" href="../../components/_mod/jQuery-Validation-Engine/template.css"/>

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
        <div class="main-content-inner fixed-page-header fixed-82">
            <div id="breadcrumbs" class="breadcrumbs">
                <ul class="breadcrumb">
                    <li class="active"><i class="fa fa-arrow-right"></i>库存统计</li>
                </ul><!-- /.breadcrumb -->
            </div>
        </div>
        <div class="main-content-inner padding-page-content">
            <div class="page-content">
                <div class="space-4"></div>
	                <div class="row">
			    		<div class="col-sm-6">
			    			<!-- <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
			    			<div id="main" style="width: 550px;height:350px;"></div>
			    		</div>

			    		<div class="col-sm-6" >
			    			<div id="main1" style="width: 550px;height:350px;"></div>
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
    if ('ontouchstart' in document.documentElement) document.write("<script src='../../components/_mod/jquery.mobile.custom/jquery.mobile.custom.js'>" + "<" + "/script>");
</script>
<script src="../../components/bootstrap/dist/js/bootstrap.js"></script>

<!-- page specific plugin scripts -->
<!--轻量级HTMl文本编辑器wysiwyg-->
<script src="../../components/jquery.hotkeys/index.js"></script>
<script src="../../components/_mod/bootstrap-wysiwyg/bootstrap-wysiwyg.js"></script>

<!--表单验证jquery.validationEngine-->
<script src="../../components/_mod/jQuery-Validation-Engine/jquery.validationEngine-zh_CN.js" type="text/javascript"
        charset="utf-8"></script>
<script src="../../components/_mod/jQuery-Validation-Engine/jquery.validationEngine.js" type="text/javascript"
        charset="utf-8"></script>
<!--日期控件-->
<script src="../../components/My97DatePicker/WdatePicker.js"></script>
<script src="../../components/echarts/echarts.min.js"></script>

<!--ace script-->
<script src="../../assets/js/src/ace.js"></script>
<script src="../../assets/js/ace-elements.js"></script>



<!-- 自己写的JS，请放在这里 -->
<script type="text/javascript">
      // 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));
var myChart1 = echarts.init(document.getElementById('main1'));

//折线图     指定图表的配置项和数据
myChart.showLoading();
myChart1.showLoading();
$.get('../../stockLog/censusByMonth').done(function (data) {
   myChart.hideLoading();
  option = {
    title: {
        text: '全仓库出入库按月份统计',
    },
    tooltip: {
        trigger: 'axis'
    },
      legend: {
          data:['入库量','出库量']
      },
    toolbox: {
        show: true,
        feature: {
            
            magicType: {type: ['line', 'bar']},
            restore: {},
            saveAsImage: {}
        }
    },
    xAxis:  {
        type: 'category',
        boundaryGap: false,
        data:  ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
    },
    yAxis: {
        type: 'value',
    },
    series: [
        {
            name:'入库量',
            type:'line',
            data:data.resultJson.content.inCount,
            markPoint: {
                data: [
                    {type: 'max', name: '最大值'},
                    {type: 'min', name: '最小值'}
                ]
            },
            markLine: {
                data: [
                    {type: 'average', name: '平均'}
                ]
            }
        },
        {
            name:'出库量',
            type:'line',
            data:data.resultJson.content.outCount,
            markPoint: {
                data: [
                    {type: 'max', name: '最大值'},
                    {type: 'min', name: '最小值'}
                ]
            },
            markLine: {
                data: [
                    {type: 'average', name: '平均'}
                ]
            }
        }
    ]
};
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
});

      $.get('../../stockLog/censusByMonth').done(function (data) {
          myChart1.hideLoading();
          option1 = {
              title: {
                  text: '全仓库盈利按月份统计',
              },
              tooltip: {
                  trigger: 'axis'
              },
              toolbox: {
                  show: true,
                  feature: {
                      magicType: {type: ['line', 'bar']},
                      restore: {},
                      saveAsImage: {}
                  }
              },
              xAxis:  {
                  type: 'category',
                  boundaryGap: false,
                  data:  ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
              },
              yAxis: {
                  type: 'value',
              },
              series: [
                  {
                      name:'盈利',
                      type:'line',
                      data:data.resultJson.content.profit,
                      markPoint: {
                          data: [
                              {type: 'max', name: '最大值'},
                              {type: 'min', name: '最小值'}
                          ]
                      },
                      markLine: {
                          data: [
                              {type: 'average', name: '平均'}
                          ]
                      }
                  }
              ]
          };
          // 使用刚指定的配置项和数据显示图表。
          myChart1.setOption(option1);
      });

        
 
        
        
</script>
</body>
</html>
