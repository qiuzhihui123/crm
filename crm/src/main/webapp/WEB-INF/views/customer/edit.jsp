<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>凯盛软件CRM-首页</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
   <%@ include file="../include/css.jsp"%>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@ include file="../include/header.jsp"%>
    <jsp:include page="../include/sider.jsp">
    	<jsp:param value="customer_my" name="param"/>
    </jsp:include>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">修改客户</h3>
                    <div class="box-tools pull-right">
                        <a class="btn btn-primary btn-sm" href="/customer/my/list"><i class="fa fa-arrow-left"></i> 返回列表</a>
                    </div>
                </div>
                <div class="box-body">
                    <form action="" id="editForm">
                        <div class="form-group">
                            <label>姓名</label>
                            <input type="hidden" name="custId" value="${cust.id}"/>
                            <input type="text" class="form-control" value="${cust.custName}" name="custname">
                        </div>
						<div class="form-group">
                            <label>性别</label>
                            <div>
                                <label class="radio-inline">
                                    <input type="radio" name="sex" value="先生" ${cust.sex == '先生' ? 'checked' : '' }> 先生
                                </label>
                                <label class="radio-inline">
                                    <input type="radio" name="sex" value="女士" ${cust.sex == '女士' ? 'checked' : '' }> 女士
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>职位</label>
                            <input type="text" class="form-control" value="${cust.jobTitle}"  name="jobtitle">
                        </div>
                        <div class="form-group">
                            <label>联系方式</label>
                            <input type="text" class="form-control" value="${cust. mobile}"  name="mobile">
                        </div>
                        <div class="form-group">
                            <label>地址</label>
                            <input type="text" class="form-control" value="${cust.address}"  name="address">
                        </div>
                        <div class="form-group">
                            <label>所属行业</label>
                            <select class="form-control"  name="trade">
                                <option value=""></option>
                                <c:forEach items="${trades}" var="trade">
                                	 <option value="${trade}" ${trade == cust.trade ? 'selected':''}>${trade}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>客户来源</label>
                            <select class="form-control"  name="source">
                                <option value=""></option>
                                <c:forEach items="${sources}" var="source">
                                	 <option value="${source}" ${source == cust.source ? 'selected':''}>${source}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>级别</label>
                            <select class="form-control"  name="level">
                                <option value=""></option>
                                <option value="★" ${cust.level == '★' ? 'selected':''}>★</option>
                                <option value="★★" ${cust.level == '★★' ? 'selected':''}>★★</option>
                                <option value="★★★" ${cust.level == '★★★' ? 'selected':''}>★★★</option>
                                <option value="★★★★" ${cust.level == '★★★★' ? 'selected':''}>★★★★</option>
                                <option value="★★★★★" ${cust.level == '★★★★★' ? 'selected':''}>★★★★★</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>备注</label>
                            <input type="text" class="form-control" name="mark" value="${cust.mark}">
                        </div>
                    </form>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary" id="editBtn">保存</button>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- 底部 -->
    <%@ include file="../include/footer.jsp"%>

</div>
<!-- ./wrapper -->
    <%@ include file="../include/js.jsp"%>

	<script>
	$(function(){
		var custId = ${cust.id};
		
 		$("#editBtn").click(function(){
 			$("#editForm").submit();
 		})
 		$("#editForm").validate({
 			errorClass : 'text-danger',
			errorElement : 'span',
			rules : {
				custname :{
					"required" : true
				},
				mobile : {
					"required" : true
				}
			},
			messages :{
				custname :{
					"required" : "请输入客户姓名"
				},
				mobile : {
					"required" : "请输入客户联系方式"
				}
			},
			submitHandler : function(form){
				$.ajax({
					url:'/customer/my/edit',
					type:'post',
					data:$("#editForm").serialize(),
					beforeSend : function(){
						$("#editBtn").text("保存中...").attr("disabled","disabled");
					},
					success : function(data){
						window.location.href="/customer/my/detail?custId=" + custId;
					},
					error : function(){
						alert("系统异常");
					},
					complete : function(){
						$("#editBtn").text("保存").removeAttr("disabled");
					}
				});
				
			}
 		
 		});
 		
 	})
	
	</script>

</body>
</html>
    