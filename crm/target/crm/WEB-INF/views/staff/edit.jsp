<%@page import="com.qiuhui.entity.Staff"%>
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
                    <h3 class="box-title">编辑员工</h3>
                   
                </div>
                <div class="box-body">
                    <form action="/staff/edit/save" method="post" id="saveForm">
                        <div class="form-group" id="oldPass">
                            <input type="hidden"  name = "staffId" value="${staff.id}" >
                            <label>姓名</label>
                            <input type="text"  name = "staffName"class="form-control" value="${staff.staffname} " placeholder="请输入姓名">
                            
                        </div>
                        <div class="form-group">
                            <label>手机号</label>
                            <input type="text" id="editMobile" name = "mobile" value="${staff.mobile} " class="form-control" placeholder="请输入手机号">
                        </div>
                        <div class="form-group">
                            <label>密码</label>
                            <input type="password" id="editPass" name = "password"  class="form-control" placeholder="请输入密码">
                        </div>
                        <div id ="checkBoxes" class="form-group">
                            <label >所属部门</label>
                            <div id="checkboxList">
                         </div>
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
		var nameList = new Array();
		<%
			Staff staff = (Staff)request.getAttribute("staff");
			String[] staffDept = staff.getDeptList();
			for(int i = 0; i<staffDept.length;i++){
		%>
		nameList[<%= i%>] = '<%= staffDept[i]%>';		
		<%}%>
		
		$.get("/dept/list").done(function(data){
			 for(var i = 0; i < data.length; i++) {
	    			var dept = data[i];
	    			
	    			if(dept.pId == 1) {
	    				 
		    			 var html = '<label class="checkbox-inline"><input type="checkbox" id ="'+dept.deptName+'" name="deptId" value="'+ dept.id +'">'+dept.deptName+'</label>'
		    			 $("#checkboxList").append(html);
	    			} 
	    		}
			 
			 for(var i = 0; i < nameList.length; i++){
				 $("#"+nameList[i])[0].checked = true;
				 }
		});
		
		$("#saveForm").validate({
			errorClass:'text-danger',
			errorElement:'span',
			
			rules:{
				 staffName: {required:true},
				 mobile: {required:true},
				 password: {required:true},
				 deptId: {required:true}
			  },
			  
			  messages :{
				  	 staffName : {required:"请填写员工姓名"},
					 mobile : {required:"请填写员工电话"},
					 password : {required:"请填写员工密码"},
					 deptId : {required:"请填写员工部门"}
				  }
			  
		});
		
		$("#formBtn").click(function(){
		
			$("#saveForm").submit();
		
		});
		/* $("#formBtn").click(function(){
					
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
					
				  }); */
				
		
		
	})
	
</script>
</body>
</html>
