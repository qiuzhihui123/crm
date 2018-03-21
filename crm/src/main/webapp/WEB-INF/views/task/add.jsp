<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛软件CRM-首页</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <%@ include file="../include/css.jsp" %>
    <link rel="stylesheet" href="/static/plugins/datepicker/datepicker3.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <!-- 顶部导航栏部分 -->
    <%@ include file="../include/header.jsp" %>

    <!-- =============================================== -->

    <!-- 左侧菜单栏 -->
    <jsp:include page="../include/sider.jsp">
  		<jsp:param value="task_add" name="menu"/>
  	</jsp:include>
    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">新增待办任务</h3>

                    <div class="box-tools pull-right">
                        <a href="/task/list"><button type="button" class="btn btn-box-tool">
                            <i class="fa fa-plus"></i> 返回列表
                        </button></a>
                    </div>
                </div>
                <div class="box-body">
                    <form action="" id="saveForm">
                        <div class="form-group">
                            <label>任务名称</label>
                            <input type="text" name="taskName" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>完成日期</label>
                            <input type="text" class="form-control" name="finishTime" id="datepicker">
                        </div>
                        
                    </form>
                </div>
                <!-- /.box-body -->
                <div class="box-footer">
                    <button class="btn btn-primary" id="saveBtn">保存</button>
                </div>
            </div>
            <!-- /.box -->

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- 底部 -->
    <%@ include file="../include/footer.jsp" %>
</div>
<!-- ./wrapper -->

<!-- jQuery 2.2.3 -->
<%@ include file="../include/js.jsp" %>
<script src="/static/plugins/moment/moment.js"></script>
<script src="/static/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="/static/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script>
    $(function () {
    	var picker = $('#datepicker').datepicker({
            format: "yyyy-mm-dd",//value格式
            language: "zh-CN", //汉化需要有相应js
            autoclose: true,  //选择后自动关闭
            todayHighlight: true, //今日高亮显示
            startDate:moment().format("yyyy-MM-dd") //设置显示的起始日期
        });
    	
    	$("#saveBtn").click(function(){
    		$("#saveForm").submit();
    	});
    	
    	$("#saveForm").validate({
    		errorClass : 'text-danger',
			errorElement : 'span',
			rules : {
				taskName :{
					"required" : true
				},
				finishTime : {
					"required" : true,
				}
			},
			messages :{
				taskName :{
					"required" : "请输入任务名称"
				},
				finishTime : {
					"required" : "请输入结束时间",
				}
			},
			submitHandler : function(form){
				$.ajax({
					url:'/task/add',
					type:'post',
					data:$("#saveForm").serialize(),
					beforeSend : function(){
						$("#saveBtn").text("保存中...").attr("disabled","disabled");
					},
					success : function(data){
						window.location.href="/task/list";
					},
					error : function(){
						alert("系统异常");
					},
					complete : function(){
						$("#saveBtn").text("保存").removeAttr("disabled");
					}
				});
				
			}
    	})
    	
    });
</script>
</body>
</html>
