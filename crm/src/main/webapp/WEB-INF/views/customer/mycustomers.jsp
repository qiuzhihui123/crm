<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


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
    <style>
        .name-avatar {
            display: inline-block;
            width: 50px;
            height: 50px;
            background-color: #ccc;
            border-radius: 50%;
            text-align: center;
            line-height: 50px;
            font-size: 24px;
            color: #FFF;
        }
        .table>tbody>tr:hover {
            cursor: pointer;
        }
        .table>tbody>tr>td {
            vertical-align: middle;
        }
        .star {
            font-size: 20px;
            color: #ff7400;
        }
        
        .BlueViolet {
        	 background-color: BlueViolet ;
        }
    </style>
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

  <%@ include file="../include/header.jsp" %>
  <%-- <%@ include file="../include/sider.jsp" %> --%>
  <jsp:include page="../include/sider.jsp">
  		<jsp:param value="customersmy" name="customer"/>
  </jsp:include> 
  
  <%-- <jsp:include page="../include/sider.jsp">
	<jsp:param value="customer_my" name="param"/>
	</jsp:include> --%>

    <!-- =============================================== -->

    <!-- 右侧内容部分 -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">

            <!-- Default box -->
            <div class="box">
                <div class="box-header with-border">
                    <h3 class="box-title">我的客户</h3>
                    <div class="box-tools pull-right">
                        <button class="btn btn-success btn-sm" id="addBtn"><i class="fa fa-plus"></i> 新增客户</button>
                    </div>
                </div>
                <div class="box-body no-padding">
                    <table class="table table-hover">
                        <tbody>
                            <tr>
                                <th width="80"></th>
                                <th>姓名</th>
                                <th>职位</th>
                                <th>跟进时间</th>
                                <th>级别</th>
                                <th>联系方式</th>
                            </tr>
                            <c:forEach items="${page.items }" var = "cust">
                            	<tr class="todetail" rel="${cust.id}">
                                <td><span class="name-avatar ${cust.sex == '女士' ? 'BlueViolet ' : '' }">${fn:substring(cust.custName,0,1)}</span></td>
                                <td>${cust.custName }</td>
                                <td>${cust.jobTitle }</td>
                                <td><fmt:formatDate value="${cust.lastContactTime }" pattern="yyyy年MM月dd日"/></td>
                                <td class="star">${cust.level}</td>
                                <td><i class="fa fa-phone"></i>${cust.mobile} <br></td>
                            </tr>
                            
                            </c:forEach>
                            
                        </tbody>
                    </table>
                        <ul id="pagination" class="pagination pagination-sm pull-right"></ul>
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

<%@ include file="../include/js.jsp" %>
<script src="/static/plugins/jQuery/jquery.twbsPagination.js"></script>
<script src="/static/plugins/jQuery/jquery.twbsPagination.min.js"></script>

<script>
	$(function(){
		
		$(".todetail").click(function(){
			location.href="/customer/my/detail?custId=" + $(this).attr("rel");
		});
		
		$("#addBtn").click(function(){
			window.location.href="/customer/add";
		});
			
		$("#pagination").twbsPagination({
 	         totalPages:"${page.totalPage}",
 	         visiblePages:3,
 	         href:"/customer/my/list?p={{number}}",
 	         first: "首页",
 	         prev: "上一页",
 	         next:"下一页",
 	         last:"末页"
 	       });
	 });
</script>

</body>
</html>
