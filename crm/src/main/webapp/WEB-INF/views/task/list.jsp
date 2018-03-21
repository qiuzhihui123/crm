<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛软件CRM-首页</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <%@ include file="../include/css.jsp" %>

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
  		<jsp:param value="task_list" name="menu"/>
  	</jsp:include>
    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">

        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">计划任务</h3>

                    <div class="box-tools pull-right">
                        <a href="/task/add" class="btn btn-success btn-sm"><i class="fa fa-plus"></i> 新增任务</a>
                        
                        <c:choose>
                        	<c:when test="${param.show == 'undone' }">
                        <a href="/task/list" class="btn btn-primary btn-sm"><i class="fa fa-eye"></i> 显示所有任务</a>
                        	</c:when>
                        	
                        	<c:otherwise>
                        <a href="/task/list?show=undone" class="btn btn-primary btn-sm"><i class="fa fa-eye"></i> 显示未完成任务</a>
                        	</c:otherwise>
                        	
                        </c:choose> 
                    </div>
                </div>
                <div class="box-body">

                    <ul class="todo-list">
                    <c:forEach items="${taskList}" var="task">
                        <li class="${task.status == 1 ? 'done' : '' }">
                            <input type="checkbox" class="taskStatus" rel="${task.id }" ${task.status == 1 ? 'checked' : '' }>
                            <span class="text">${task.title}</span>
                            <small class="label ${task.overTime ? 'label-danger' : 'label-success' }"><i class="fa fa-clock-o"></i> ${task.finishTime}</small>
                            <div class="tools">
                                <i class="fa fa-edit edit_task" rel="${task.id }" xyz="${task.status }" ></i>
                                <i class="fa fa-trash-o del_task" rel="${task.id }"></i>
                            </div>
                        </li>
                    </c:forEach>
                        
                    </ul>
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
		
		$(".edit_task").click(function(){
			var taskId = $(this).attr("rel");
			var status = $(this).attr("xyz");
			
			if(status == 0){
				window.location.href="/task/edit?taskId=" + taskId;
			}
		});
		
		
		$(".del_task").click(function(){
			var taskId = $(this).attr("rel");
			layer.confirm("确定要删除吗",function(){
				window.location.href="/task/del?taskId=" + taskId;
			});
		});
		
		
		$(".taskStatus").click(function(){
			var taskId = $(this).attr("rel");
			var checked = $(this)[0].checked;//判断是否√
			var status = 0;		
			
			if(checked){
				status = 1
			} 
			
			$.get("/task/status",{"taskId":taskId,"status":status},function(data){
				if(data.state == 0){
					layer.msg("修改成功");
					history.go(0);
				}
			});
			
		});
	})
</script>
</body>
</html>
