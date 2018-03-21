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
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <style>
    	#error{
    		color:red;
    	}
    </style>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <!-- 顶部导航栏部分 -->
    <%@ include file="../include/header.jsp" %>

    <!-- =============================================== -->

    <!-- 左侧菜单栏 -->
    <%@ include file="../include/sider.jsp" %>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">修改密码</h3>
                   
                </div>
                <div class="box-body">
                    <form action="/staff/changepassword" id="changeForm">
                        <div class="form-group" id="oldPass">
                            <label>原始密码</label>
                            <input type="password"  name = "oldPass"class="form-control" placeholder="请输入当前密码">
                            
                        </div>
                        <div class="form-group">
                            <label>新密码</label>
                            <input type="password" id="newPass" name = "newPass" class="form-control" placeholder="请输入新密码">
                        </div>
                        <div class="form-group">
                            <label>确认密码</label>
                            <input type="password" id="changePass" name = "changePass" class="form-control" placeholder="请输入确认密码">
                        </div>
                        
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary" id="formBtn">保存</button>
                </div>
                <!-- /.box-body -->
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

<script>
	$(function(){
		
		$("#formBtn").click(function(){
					
			$("#changeForm").submit();			
			
		});
		
		
		$(document).keyup(function(event){
			if(event.keyCode == 13){
				$("#changeForm").submit();
			}
		});
		
		
		$("#changeForm").validate({
					
					errorClass:'text-danger',
					
					errorElement:'span',
					
					rules:{
						oldPass:{
							'required':true
						},
						newPass:{
							'required':true
						},
						changePass:{
							'required':true,
							'equalTo': newPass
						}
						
					},
					
					messages:{
						oldPass:{
							'required':'请输入当前密码'
						},
						newPass:{
							"required":'请输入新密码'
						},
						changePass:{
							"required":'请输入确认密码',
							"equalTo": '两次输入不一致'
						}
					},
					
					submitHandler:function(form){
						$.ajax({
							url:'/staff/changePassword',
							type:'post',
							data:$("#changeForm").serialize(),
							beforeSend:function(){
								$("#formBtn").text("提交中...").attr('disabled','disabled');
							},
							success:function(data){
								if(data.state == '0'){
								layer.msg('修改成功');
								location.href="/login";
								}
								if(data.state =='40'){
									var message = data.message;
									$("#oldPass").children("#error").remove();
									$("#oldPass").append('<span id = "error">'+message+'</span>');
								}
							},
							complete:function(){
								$("#formBtn").text("保存").removeAttr('disabled');
							}
						});
					}
					
				  });
				
		
		
	})
	
</script>
</body>
</html>
